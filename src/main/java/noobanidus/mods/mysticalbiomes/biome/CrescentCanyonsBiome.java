/*
package noobanidus.mods.mysticalbiomes.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import noobanidus.libs.noobutil.world.gen.BiomeBuilder;
import noobanidus.mods.mysticalbiomes.init.ConfiguredCarvers;
import noobanidus.mods.mysticalbiomes.init.ConfiguredSurfaceBuilders;

public class CrescentCanyonsBiome {
  private static final BiomeBuilder.BiomeTemplate CANYONS_TEMPLATE = new BiomeBuilder.BiomeTemplate(BiomeBuilder.BIOME_TEMPLATE.builder()
      .surfaceBuilder(ConfiguredSurfaceBuilders.CONFIGURED_CANYONS)
      .precipitation(Biome.RainType.NONE)
      .temperature(10.0F)
      .downfall(0.0F)
      .effects(BiomeBuilder.createDefaultBiomeAmbience()
*/
/*          .grassColorOverride(9470285)
          .foliageColorOverride(9470285)
          .waterColor(0x809678)
          .waterFogColor(0x809678)
          .fogColor(0x809678)
          .skyColor(0x809678)*//*

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

  public static BiomeBuilder.BiomeTemplate CANYON_MAIN_TEMPLATE = new BiomeBuilder.BiomeTemplate(CANYONS_TEMPLATE.builder()
      .category(Biome.Category.DESERT)
      .depth(1.2F)
      .scale(-0.11f));

  public static Biome CRESCENT_CANYONS = CANYON_MAIN_TEMPLATE.builder()
      .addDefaultFeatureFunctions(o -> o.addCarver(GenerationStage.Carving.AIR, ConfiguredCarvers.CRESCENT_CANYON))
      .build();

  public static Biome CRESCENT_EDGE = CANYON_MAIN_TEMPLATE.builder()
      .depth(0.3F)
      .scale(-0.11F)
      .build();

*/
/*  public static Biome CRESCENT_BEACH = CANYONS_TEMPLATE.builder()
      .category(Biome.Category.BEACH)
      .depth(-0.5F)
      .scale(0.2F)
      .build();*//*


*/
/*  public static Biome PETRIFIED_RIVER = CANYONS_TEMPLATE.builder()
      .category(Biome.Category.RIVER)
      .addSpawnFunctions((o) ->
          o.addSpawn(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(EntityType.SQUID, 2, 1, 4)).addSpawn(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(EntityType.SALMON, 5, 1, 5)).addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.DROWNED, 100, 1, 1))
      )
      .depth(0.1f)
      .scale(0.0f)
      .build();*//*

}
*/
