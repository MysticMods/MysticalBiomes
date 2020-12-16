package noobanidus.mods.mysticalbiomes.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import noobanidus.libs.noobutil.world.gen.BiomeBuilder;
import noobanidus.mods.mysticalbiomes.init.ConfiguredFeatures;
import noobanidus.mods.mysticalbiomes.init.ConfiguredSurfaceBuilders;

public class PetrifiedWastesBiome {
  private static BiomeBuilder.BiomeTemplate PETRIFIED_TEMPLATE = new BiomeBuilder.BiomeTemplate(BiomeBuilder.BIOME_TEMPLATE.builder()
      .surfaceBuilder(ConfiguredSurfaceBuilders.CONFIGURED_SOFT_STONE)
      .precipitation(Biome.RainType.NONE)
      .temperature(10.0F)
      .downfall(0.0F)
      .effects(BiomeBuilder.createDefaultBiomeAmbience()
          .withGrassColor(9470285)
          .withFoliageColor(9470285)
          .setWaterColor(0xd1d9ad)
          .setWaterFogColor(0xd1d9ad)
          .setFogColor(0xd1d9ad)
          .withSkyColor(0xd1d9ad)
      )
      .addDefaultFeatureFunctions(
          DefaultBiomeFeatures::withStrongholdAndMineshaft,
          DefaultBiomeFeatures::withCavesAndCanyons,
          DefaultBiomeFeatures::withFossils,
          DefaultBiomeFeatures::withLavaLakes, // addLakes
          DefaultBiomeFeatures::withMonsterRoom // addMonsterRooms
      )
      .addStructureFeature(StructureFeatures.RUINED_PORTAL_MOUNTAIN)
      .addSpawnFunction(DefaultBiomeFeatures::withBats)
      .addSpawnFunction(builder -> {
        builder.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.HUSK, 95, 4, 4));
        builder.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON_HORSE, 5, 1, 1));
        builder.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, 50, 4, 4));
        builder.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
        builder.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 4));
        builder.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 1));
      })
      .addDefaultFeatureFunctions(
          DefaultBiomeFeatures::withOverworldOres, // addOres
          DefaultBiomeFeatures::withFossils,
          DefaultBiomeFeatures::withFrozenTopLayer
      ));

  public static Biome PETRIFIED_WASTES = PETRIFIED_TEMPLATE.builder()
      .category(Biome.Category.DESERT)
      .depth(0.125F)
      .scale(0.0F)
      .addFeature(GenerationStage.Decoration.LAKES, ConfiguredFeatures.REGULAR_LAVA)
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.COBBLE_TREE)
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.SOFT_STONE_TREE)
      .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ConfiguredFeatures.DISK_ANDESITE)
      .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ConfiguredFeatures.DISK_GRAVEL)
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_PETRIFIED_GRASS)
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_PETRIFIED_BUSH)
      .build();

  public static Biome PETRIFIED_BEACH = PETRIFIED_TEMPLATE.builder()
      .category(Biome.Category.BEACH)
      .depth(0.1F)
      .scale(0.2F)
      .build();

  public static Biome PETRIFIED_RIVER = PETRIFIED_TEMPLATE.builder()
      .category(Biome.Category.RIVER)
      .addSpawnFunctions((o) ->
          o.withSpawner(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(EntityType.SQUID, 2, 1, 4)).withSpawner(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(EntityType.SALMON, 5, 1, 5)).withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.DROWNED, 100, 1, 1))
      )
      .depth(-0.5f)
      .scale(0.0f)
      .build();
}
