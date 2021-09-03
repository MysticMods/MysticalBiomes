package noobanidus.mods.mysticalbiomes.biome;

import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;

import javax.annotation.Nullable;
import java.util.*;

public class BiomeVariants {
  private static final Map<RegistryKey<Biome>, WeightedEntryList> biomeReplacements = new HashMap<>();

  public enum VariantType {
    HILLS, BIOME, RIVER, EDGE, CENTER, SHORE
  }

  private static final EnumMap<VariantType, Map<RegistryKey<Biome>, Entry>> map = new EnumMap<>(VariantType.class);

  static {
    map.put(VariantType.HILLS, new HashMap<>());
    map.put(VariantType.RIVER, new HashMap<>());
    map.put(VariantType.EDGE, new HashMap<>());
    map.put(VariantType.CENTER, new HashMap<>());
    map.put(VariantType.SHORE, new HashMap<>());
  }

  public static void addReplacement(RegistryKey<Biome> replacing, RegistryKey<Biome> replacement, double chance, VariantType type) {
    if (type != VariantType.BIOME) {
      Map<RegistryKey<Biome>, Entry> current = map.get(type);
      if (current.containsKey(replacing)) {
        throw new IllegalStateException(replacing + " already exists for " + type);
      }
      current.put(replacing, Entry.of(replacement));
    } else {
      biomeReplacements.computeIfAbsent(replacing, (k) -> new WeightedEntryList()).add(replacement, chance);
    }
  }

  @Nullable
  public static RegistryKey<Biome> pickReplacement(INoiseRandom random, RegistryKey<Biome> replacing, VariantType type) {
    if (type != VariantType.BIOME) {
      Map<RegistryKey<Biome>, Entry> biomes = map.get(type);
      if (biomes == null) {
        return null;
      }
      Entry entry = biomes.get(replacing);
      if (entry == null) {
        return null;
      }
      return entry.getReplacement();
    } else {
      WeightedEntryList entries = biomeReplacements.get(replacing);
      if (entries == null) {
        return null;
      }
      return entries.getReplacement(random);
    }
  }

  private interface IEntry {
    RegistryKey<Biome> getReplacement();

    default RegistryKey<Biome> getReplacement(INoiseRandom random) {
      return getReplacement();
    }
  }

  private static class Entry implements IEntry {
    private final RegistryKey<Biome> replacement;

    public Entry(RegistryKey<Biome> replacement) {
      this.replacement = replacement;
    }

    @Override
    public RegistryKey<Biome> getReplacement() {
      return replacement;
    }

    public static Entry of(RegistryKey<Biome> replacement) {
      return new Entry(replacement);
    }
  }

  private static class WeightedEntry implements IEntry {
    private final RegistryKey<Biome> replacement;
    private final double chance;
    private final double currentTotal;

    public WeightedEntry(RegistryKey<Biome> replacement, double chance, double currentTotal) {
      this.replacement = replacement;
      this.chance = chance;
      this.currentTotal = currentTotal;
    }

    @Override
    public RegistryKey<Biome> getReplacement() {
      return replacement;
    }

    public double getChance() {
      return chance;
    }

    public double getCurrentTotal() {
      return currentTotal;
    }
  }

  private static class WeightedEntryList implements IEntry {
    private final List<WeightedEntry> entries;
    private double total;

    public WeightedEntryList() {
      this.entries = new ArrayList<>();
      this.total = 0;
    }

    public void add(RegistryKey<Biome> replacement, double weight) {
      this.total += weight;
      this.entries.add(new WeightedEntry(replacement, weight, this.total));
    }

    public double getTotal() {
      return total;
    }

    @Override
    public RegistryKey<Biome> getReplacement() {
      return null;
    }

    @Override
    public RegistryKey<Biome> getReplacement(INoiseRandom random) {
      if (entries.size() == 1) {
        return entries.get(0).getReplacement();
      }

      return search(random.random(Integer.MAX_VALUE) * getTotal() / Integer.MAX_VALUE).getReplacement();
    }

    private WeightedEntry search(double target) {
      int min = 0;
      int max = entries.size();
      while (min < max) {
        int mid = (max + min) >>> 1;
        if (target < entries.get(mid).getCurrentTotal()) {
          max = mid;
        } else {
          min = mid + 1;
        }
      }
      return entries.get(min);
    }
  }
}
