package noobanidus.mods.mysticalbiomes.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;

import javax.annotation.Nullable;

public class ConfiguredStructures {
  public static StructureFeature<?, ?> CONFIGURED_MUSHROOM_HOUSE = register("mushroom_house", ModStructures.MUSHROOM_HUT, ModStructures.MUSHROOM_HUT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));

  private static StructureFeature<?, ?> register(String id, @Nullable Structure<?> structure, StructureFeature<?, ?> feature) {
    ResourceLocation rl = new ResourceLocation(MysticalBiomes.MODID, id);

    if (structure != null) {
      FlatGenerationSettings.STRUCTURES.put(structure, feature);
    }
    return Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, rl, feature);
  }
}
