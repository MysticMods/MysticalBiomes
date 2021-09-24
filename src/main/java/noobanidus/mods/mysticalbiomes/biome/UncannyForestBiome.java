package noobanidus.mods.mysticalbiomes.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import noobanidus.libs.noobutil.world.gen.BiomeBuilder;
import noobanidus.mods.mysticalbiomes.init.ConfiguredFeatures;
import noobanidus.mods.mysticalbiomes.init.ConfiguredStructures;
import noobanidus.mods.mysticalbiomes.init.ConfiguredSurfaceBuilders;

public class UncannyForestBiome {

  public static void withUncannyMobs(MobSpawnInfo.Builder builder) {
    int zombieWeight = 95;
    int zombieVillagerWeight = 5;
    int skeletonWeight = 100;
    builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 4, 4));
    builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE, zombieWeight, 4, 4));
    builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE_VILLAGER, zombieVillagerWeight, 1, 1));
    builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, skeletonWeight, 4, 4));
    builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
    builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 4));
    builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 1));
  }

  public static BiomeBuilder.BiomeTemplate UNCANNY_TEMPLATE = new BiomeBuilder.BiomeTemplate(BiomeBuilder.BIOME_TEMPLATE.builder()
      .surfaceBuilder(ConfiguredSurfaceBuilders.CONFIGURED_UNCANNY)
      .precipitation(Biome.RainType.RAIN)
      .temperature(0.2F)
      .downfall(0.8F)
      .effects(BiomeBuilder.createDefaultBiomeAmbience()
          .grassColorOverride(0x6c36e0)
          .foliageColorOverride(0x3a50cf)
          .waterColor(0x382bed)
          .waterFogColor(0x817ce6)
          .fogColor(0x5f42ff)
      )
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.LANTERNS)
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_GRASS_PLAIN)
      .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ConfiguredFeatures.DISK_UNCANNY_SAND)
      .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.DISK_CLAY)
      .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ConfiguredFeatures.DISK_UNCANNY_GRAVEL)
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.UNCANNY_MUSHROOMS_BROWN)
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.UNCANNY_MUSHROOMS_RED)
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.UNCANNY_MUSHROOMS)
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.HUGE_UNCANNY_MUSHROOM)
      .addDefaultFeatureFunctions(
          DefaultBiomeFeatures::addDefaultCarvers,
          DefaultBiomeFeatures::addDefaultOverworldLandStructures, // part of addStructures
          DefaultBiomeFeatures::addDefaultMonsterRoom, // addMonsterRooms
          DefaultBiomeFeatures::addDefaultUndergroundVariety, // addStoneVariants
          DefaultBiomeFeatures::addDefaultOres, // addOres
          DefaultBiomeFeatures::addShatteredSavannaGrass,
          DefaultBiomeFeatures::addPlainGrass,
          DefaultBiomeFeatures::addFossilDecoration,
          DefaultBiomeFeatures::addSurfaceFreezing
      )
      .addFeature(GenerationStage.Decoration.LAKES, ConfiguredFeatures.CENTERED_LAKE)
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.DARK_TREE)
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.MEGA_DARK)
      .addStructureFeature(StructureFeatures.RUINED_PORTAL_STANDARD)
      .addSpawnFunction(DefaultBiomeFeatures::ambientSpawns)
      .addSpawnFunction(UncannyForestBiome::withUncannyMobs));

  public static Biome UNCANNY_FOREST = UNCANNY_TEMPLATE.builder()
      .category(Biome.Category.FOREST)
      .addStructureFeature(ConfiguredStructures.CONFIGURED_HENGE)
      .addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ConfiguredFeatures.TOTEM)
      .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_WILD_WART)
      .depth(0.05F)
      .scale(-0.09F)
      .build();

  public static Biome UNCANNY_BEACH = UNCANNY_TEMPLATE.builder()
      .category(Biome.Category.BEACH)
      .depth(0.05F)
      .scale(-0.09F)
      .build();

  public static Biome UNCANNY_RIVER = UNCANNY_TEMPLATE.builder()
      .category(Biome.Category.RIVER)
      .addSpawnFunctions((o) ->
          o.addSpawn(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(EntityType.SQUID, 2, 1, 4)).addSpawn(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(EntityType.SALMON, 5, 1, 5)).addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.DROWNED, 100, 1, 1))
      )
      .depth(-0.5f)
      .scale(-0.10f)
      .build();
}