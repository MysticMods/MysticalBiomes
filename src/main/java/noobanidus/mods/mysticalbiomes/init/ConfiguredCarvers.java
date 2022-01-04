package noobanidus.mods.mysticalbiomes.init;

import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import noobanidus.libs.noobutil.registry.ConfiguredRegistry;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;

public class ConfiguredCarvers {
  public static final ConfiguredRegistry<ConfiguredCarver<?>> REGISTRY = new ConfiguredRegistry<>(MysticalBiomes.MODID, WorldGenRegistries.CONFIGURED_CARVER);

  public static final ConfiguredCarver<?> CRESCENT_CANYON = register("crescent_carver", ModCarvers.CRESCENT_CARVER.configured(new ProbabilityConfig(0.7f)));

  private static ConfiguredCarver<?> register(String id, ConfiguredCarver<?> feature) {
    return REGISTRY.register(id, feature);
  }

  public static void load() {
  }
}
