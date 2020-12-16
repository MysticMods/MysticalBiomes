package noobanidus.mods.mysticalbiomes.init;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;
import noobanidus.mods.mysticalbiomes.biome.SproutForestBiome;
import noobanidus.mods.mysticalbiomes.biome.PetrifiedWastesBiome;
import noobanidus.mods.mysticalbiomes.config.BiomeConfig;
import noobanidus.mods.mysticalbiomes.config.ConfigManager;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MysticalBiomes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes {
  private static Set<Biome> BIOMES = new HashSet<>();

  public static Biome SPROUT_FOREST = register("sprout_forest", SproutForestBiome.SPROUT_FOREST);
  public static Biome SPROUT_RIVER = register("sprout_river", SproutForestBiome.SPROUT_RIVER);
  public static Biome SPROUT_BEACH = register("sprout_beach", SproutForestBiome.SPROUT_BEACH);
  public static Biome PETRIFIED_WASTES = register("petrified_wastes", PetrifiedWastesBiome.PETRIFIED_WASTES);
  public static Biome PETRIFIED_BEACH = register("petrified_beach", PetrifiedWastesBiome.PETRIFIED_BEACH);
  public static Biome PETRIFIED_RIVER = register("petrified_river", PetrifiedWastesBiome.PETRIFIED_RIVER);

  static {
    BiomeVariants.addReplacement(k(PETRIFIED_WASTES), k(PETRIFIED_RIVER), 1, BiomeVariants.VariantType.RIVER);
    BiomeVariants.addReplacement(k(PETRIFIED_WASTES), k(PETRIFIED_BEACH), 1, BiomeVariants.VariantType.SHORE);
    BiomeVariants.addReplacement(k(SPROUT_FOREST), k(SPROUT_RIVER), 1, BiomeVariants.VariantType.RIVER);
    BiomeVariants.addReplacement(k(SPROUT_FOREST), k(SPROUT_BEACH), 1, BiomeVariants.VariantType.SHORE);
  }

  public static void load () {

  }

  private static Biome register(String name, Biome biome) {
    ResourceLocation rl = new ResourceLocation(MysticalBiomes.MODID, name);
    biome.setRegistryName(rl);
    BIOMES.add(biome);
    return biome;
  }

  private static RegistryKey<Biome> k (Biome b) {
    return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, Objects.requireNonNull(b.getRegistryName()));
  }

  private static void register(Biome b, BiomeConfig config, BiomeManager.BiomeType managerType, BiomeDictionary.Type... dictTypes) {
    RegistryKey<Biome> biome = k(b);
    if (config.shouldSpawn()) {
      BiomeManager.addBiome(managerType, new BiomeManager.BiomeEntry(biome, config.getWeight()));
      BiomeDictionary.addTypes(biome, dictTypes);
    }
  }

  @SubscribeEvent
  public static void register(RegistryEvent.Register<Biome> event) {
    event.getRegistry().registerAll(BIOMES.toArray(new Biome[0]));

    register(ModBiomes.SPROUT_FOREST, ConfigManager.SPROUT_PLAINS, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.LUSH);

    register(ModBiomes.PETRIFIED_WASTES, ConfigManager.STONE_WASTES, BiomeManager.BiomeType.DESERT, BiomeDictionary.Type.OVERWORLD, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.WASTELAND);
  }
}