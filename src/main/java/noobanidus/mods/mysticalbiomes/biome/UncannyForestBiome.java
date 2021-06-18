package noobanidus.mods.mysticalbiomes.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import noobanidus.libs.noobutil.world.gen.BiomeBuilder;
import noobanidus.mods.mysticalbiomes.init.ConfiguredFeatures;
import noobanidus.mods.mysticalbiomes.init.ConfiguredSurfaceBuilders;

public class UncannyForestBiome {
  public static BiomeBuilder.BiomeTemplate UNCANNY_TEMPLATE = new BiomeBuilder.BiomeTemplate(BiomeBuilder.BIOME_TEMPLATE.builder()
      .surfaceBuilder(ConfiguredSurfaceBuilders.CONFIGURED_DEFAULT_GRASS)
      .precipitation(Biome.RainType.RAIN)
      .temperature(0.2F)
      .downfall(0.8F)
      .effects(BiomeBuilder.createDefaultBiomeAmbience()
          .withGrassColor(0x4e36e0)
          //.withFoliageColor(0x473ec7)
          .withFoliageColor(0x3a50cf)
          .setWaterColor(0x382bed)
          .setWaterFogColor(0x817ce6)
          .setFogColor(0x5f42ff)
      )
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.LANTERNS)
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_GRASS_PLAIN)
      .addDefaultFeatureFunctions(
          DefaultBiomeFeatures::withCavesAndCanyons,
          DefaultBiomeFeatures::withStrongholdAndMineshaft, // part of addStructures
          // TODO: NO LAVA LAKES
          DefaultBiomeFeatures::withLavaAndWaterLakes, // addLakes
          DefaultBiomeFeatures::withMonsterRoom, // addMonsterRooms
          DefaultBiomeFeatures::withCommonOverworldBlocks, // addStoneVariants
          DefaultBiomeFeatures::withOverworldOres, // addOres
          DefaultBiomeFeatures::withDisks, // addSedimentDisks
          DefaultBiomeFeatures::withNormalMushroomGeneration,
          DefaultBiomeFeatures::withNormalGrassPatch,
          DefaultBiomeFeatures::withNoiseTallGrass,
          DefaultBiomeFeatures::withFossils,
          DefaultBiomeFeatures::withFrozenTopLayer
      )
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.DARK_TREE)
      // MEGA TREES TOO SHORT
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.MEGA_DARK)
      .addStructureFeature(StructureFeatures.RUINED_PORTAL)
      .addSpawnFunction(DefaultBiomeFeatures::withBatsAndHostiles));

  public static Biome UNCANNY_FOREST = UNCANNY_TEMPLATE.builder()
      .category(Biome.Category.FOREST)
      .depth(0.05F)
      .scale(-0.09F)
      .build();

/*  public static Biome SPROUT_BEACH = SPROUT_TEMPLATE.builder()
      .category(Biome.Category.BEACH)
      .depth(0.1F)
      .scale(0.2F)
      .build();

  public static Biome SPROUT_RIVER = SPROUT_TEMPLATE.builder()
      .category(Biome.Category.RIVER)
      .addSpawnFunctions((o) ->
          o.withSpawner(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(EntityType.SQUID, 2, 1, 4)).withSpawner(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(EntityType.SALMON, 5, 1, 5)).withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.DROWNED, 100, 1, 1))
      )
      .depth(-0.5f)
      .scale(0.0f)
      .build();*/
}
