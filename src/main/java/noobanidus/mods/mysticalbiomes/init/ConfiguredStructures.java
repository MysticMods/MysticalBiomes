package noobanidus.mods.mysticalbiomes.init;

import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import noobanidus.libs.noobutil.registry.ConfiguredRegistry;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class ConfiguredStructures {
  public static final ConfiguredRegistry<StructureFeature<?, ?>> REGISTRY = new ConfiguredRegistry<>(MysticalBiomes.MODID, WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE);

  public static Map<Structure<?>, StructureFeature<?, ?>> CONFIGURED_STRUCTURES = new HashMap<>();

  public static StructureFeature<?, ?> CONFIGURED_MUSHROOM_HOUSE = register("mushroom_house", ModStructures.MUSHROOM_HUT, ModStructures.MUSHROOM_HUT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
  public static StructureFeature<?, ?> CONFIGURED_HENGE = register("henge", ModStructures.HENGE, ModStructures.HENGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));

  private static StructureFeature<?, ?> register(String id, @Nullable Structure<?> structure, StructureFeature<?, ?> feature) {
    StructureFeature<?, ?> result = REGISTRY.register(id, feature);
    CONFIGURED_STRUCTURES.put(structure, feature);
    return result;
  }

  public static void registerStructures() {
    CONFIGURED_STRUCTURES.forEach(FlatGenerationSettings.STRUCTURES::put);
  }
}
