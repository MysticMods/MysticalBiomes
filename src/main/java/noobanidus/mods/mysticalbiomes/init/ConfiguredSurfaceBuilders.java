package noobanidus.mods.mysticalbiomes.init;

import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import noobanidus.libs.noobutil.world.gen.config.SupplierSurfaceBuilderConfig;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;

public class ConfiguredSurfaceBuilders {
  public static final ConfiguredSurfaceBuilder<?> CONFIGURED_DEFAULT_GRASS = register("default_grass", new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG));
  public static final ConfiguredSurfaceBuilder<?> CONFIGURED_SOFT_STONE = register("soft_stone", new ConfiguredSurfaceBuilder<>(ModSurfaces.SMOOTH_STONE_SURFACE, new SupplierSurfaceBuilderConfig(new ResourceLocation("mysticalworld", "soft_stone"))));
  public static final ConfiguredSurfaceBuilder<?> CONFIGURED_LAKE = register("lake", new ConfiguredSurfaceBuilder<>(ModSurfaces.PETRIFIED_LAKE_SURFACE, new SupplierSurfaceBuilderConfig(new ResourceLocation("mysticalworld", "soft_stone"))));
  public static final ConfiguredSurfaceBuilder<?> CONFIGURED_GRASSY_SWAMP = register("grassy_swamp", new ConfiguredSurfaceBuilder<>(SurfaceBuilder.SWAMP, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG));
  public static final ConfiguredSurfaceBuilder<?> CONFIGURED_DESERT = register("desert", new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, SurfaceBuilder.SAND_SAND_GRAVEL_CONFIG));
  public static final ConfiguredSurfaceBuilder<?> CONFIGURED_POLAR_DESERT = register("polar_desert", new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.GRAVEL.getDefaultState())));
  public static final ConfiguredSurfaceBuilder<?> CONFIGURED_MOUNTAIN = register("mountain", new ConfiguredSurfaceBuilder<>(SurfaceBuilder.MOUNTAIN, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG));

  private static ConfiguredSurfaceBuilder<?> register(String id, ConfiguredSurfaceBuilder<?> feature) {
    return Registry.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(MysticalBiomes.MODID, id), feature);
  }
}
