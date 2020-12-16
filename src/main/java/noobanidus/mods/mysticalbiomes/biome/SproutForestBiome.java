package noobanidus.mods.mysticalbiomes.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import noobanidus.libs.noobutil.world.gen.BiomeBuilder;
import noobanidus.mods.mysticalbiomes.init.ConfiguredFeatures;
import noobanidus.mods.mysticalbiomes.init.ConfiguredStructures;
import noobanidus.mods.mysticalbiomes.init.ConfiguredSurfaceBuilders;

public class SproutForestBiome {
  public static BiomeBuilder.BiomeTemplate SPROUT_TEMPLATE = new BiomeBuilder.BiomeTemplate(BiomeBuilder.BIOME_TEMPLATE.builder()
      .surfaceBuilder(ConfiguredSurfaceBuilders.CONFIGURED_DEFAULT_GRASS)
      .precipitation(Biome.RainType.RAIN)
      .temperature(0.7F)
      .downfall(0.8F)
      .effects(BiomeBuilder.createDefaultBiomeAmbience()
          .withGrassColor(0xa4f22e)
          .withFoliageColor(0x79f22e)
          .setWaterColor(0x2ef2e5)
          .setWaterFogColor(0x99d8e8)
          .setFogColor(0xdbc0ff)
      )
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_WILD_AUBERGINE)
      .addDefaultFeatureFunctions(
          (o) -> o.withCarver(GenerationStage.Carving.AIR, ConfiguredCarvers.field_243767_a),
          DefaultBiomeFeatures::withStrongholdAndMineshaft, // part of addStructures
          DefaultBiomeFeatures::withLavaAndWaterLakes, // addLakes
          DefaultBiomeFeatures::withMonsterRoom, // addMonsterRooms
          DefaultBiomeFeatures::withCommonOverworldBlocks, // addStoneVariants
          DefaultBiomeFeatures::withOverworldOres, // addOres
          DefaultBiomeFeatures::withDisks, // addSedimentDisks
          DefaultBiomeFeatures::withNormalMushroomGeneration,
          DefaultBiomeFeatures::withNormalGrassPatch,
          DefaultBiomeFeatures::withNoiseTallGrass,
          DefaultBiomeFeatures::withPlainGrassVegetation,
          DefaultBiomeFeatures::withAllForestFlowerGeneration,
          DefaultBiomeFeatures::withExtraGoldOre,
          DefaultBiomeFeatures::withFossils,
          DefaultBiomeFeatures::withFrozenTopLayer
      )
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.TALL_RED_MUSHROOMS)
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_MELON)
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_PUMPKIN)
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.SPREAD_OAK_TREES)
      .addStructureFeature(StructureFeatures.RUINED_PORTAL)
      .addSpawnFunction(DefaultBiomeFeatures::withBatsAndHostiles));

  public static Biome SPROUT_FOREST = SPROUT_TEMPLATE.builder()
      .category(Biome.Category.PLAINS)
      .addStructureFeature(ConfiguredStructures.CONFIGURED_MUSHROOM_HOUSE)
      .depth(0.05F)
      .scale(0.01F)
      .build();

  public static Biome SPROUT_BEACH = SPROUT_TEMPLATE.builder()
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
      .build();
}
