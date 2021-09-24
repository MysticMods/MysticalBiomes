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
  private static final BiomeBuilder.BiomeTemplate PETRIFIED_TEMPLATE = new BiomeBuilder.BiomeTemplate(BiomeBuilder.BIOME_TEMPLATE.builder()
      .surfaceBuilder(ConfiguredSurfaceBuilders.CONFIGURED_SOFT_STONE)
      .precipitation(Biome.RainType.NONE)
      .temperature(10.0F)
      .downfall(0.0F)
      .effects(BiomeBuilder.createDefaultBiomeAmbience()
          .grassColorOverride(9470285)
          .foliageColorOverride(9470285)
          .waterColor(0x809678)
          .waterFogColor(0x809678)
          .fogColor(0x809678)
          .skyColor(0x809678)
      )
      .addDefaultFeatureFunctions(
          DefaultBiomeFeatures::addDefaultOverworldLandStructures,
          DefaultBiomeFeatures::addDefaultCarvers,
          DefaultBiomeFeatures::addFossilDecoration,
          DefaultBiomeFeatures::addDefaultMonsterRoom // addMonsterRooms
      )
      .addStructureFeature(StructureFeatures.RUINED_PORTAL_MOUNTAIN)
      .addSpawnFunction(DefaultBiomeFeatures::ambientSpawns)
      .addSpawnFunction(builder -> {
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.HUSK, 95, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON_HORSE, 5, 1, 1));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, 50, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 1));
      })
      .addDefaultFeatureFunctions(
          DefaultBiomeFeatures::addDefaultOres, // addOres
          DefaultBiomeFeatures::addFossilDecoration,
          DefaultBiomeFeatures::addSurfaceFreezing
      ));

  public static BiomeBuilder.BiomeTemplate PETRIFIED_MAIN_TEMPLATE = new BiomeBuilder.BiomeTemplate(PETRIFIED_TEMPLATE.builder()
      .category(Biome.Category.DESERT)
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_STONEPETAL)
      .addFeature(GenerationStage.Decoration.LAKES, ConfiguredFeatures.CORAL_LAKE)
      .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ConfiguredFeatures.DISK_ANDESITE)
      .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ConfiguredFeatures.DISK_GRAVEL)
      .addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, ConfiguredFeatures.SURFACE_FOSSIL)
      .depth(0.125F)
      .scale(0.0F));

  public static Biome PETRIFIED_WASTES = PETRIFIED_MAIN_TEMPLATE.builder()
      .addFeature(GenerationStage.Decoration.LAKES, ConfiguredFeatures.REGULAR_LAVA)
      .addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ConfiguredFeatures.COBBLE_TREE)
      .addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ConfiguredFeatures.SOFT_STONE_TREE)
      .addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, ConfiguredFeatures.BOULDER_FEATURE)
      .build();

  public static Biome PETRIFIED_CENTER = PETRIFIED_MAIN_TEMPLATE.builder()
      .addFeature(GenerationStage.Decoration.LAKES, ConfiguredFeatures.REGULAR_LAVA)
      .addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ConfiguredFeatures.LARGE_COBBLE_TREE)
      .addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ConfiguredFeatures.LARGE_SOFT_STONE_TREE)
      .addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, ConfiguredFeatures.ORE_WEIGHTED_BOULDER_FEATURE)
      .build();

  public static Biome PETRIFIED_EDGE = PETRIFIED_MAIN_TEMPLATE.builder()
      .addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, ConfiguredFeatures.BOULDER_FEATURE)
      .addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ConfiguredFeatures.SMALL_COBBLE_TREE)
      .addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ConfiguredFeatures.SMALL_SOFT_STONE_TREE)
      .build();

  public static Biome PETRIFIED_BEACH = PETRIFIED_TEMPLATE.builder()
      .category(Biome.Category.BEACH)
      .depth(-0.5F)
      .scale(0.2F)
      .build();

  public static Biome PETRIFIED_RIVER = PETRIFIED_TEMPLATE.builder()
      .category(Biome.Category.RIVER)
      .addSpawnFunctions((o) ->
          o.addSpawn(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(EntityType.SQUID, 2, 1, 4)).addSpawn(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(EntityType.SALMON, 5, 1, 5)).addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.DROWNED, 100, 1, 1))
      )
      .depth(0.1f)
      .scale(0.0f)
      .build();
}
