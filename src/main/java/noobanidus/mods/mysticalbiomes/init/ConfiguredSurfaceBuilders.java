package noobanidus.mods.mysticalbiomes.init;

import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import noobanidus.libs.noobutil.registry.ConfiguredRegistry;
import noobanidus.libs.noobutil.types.LazyStateSupplier;
import noobanidus.libs.noobutil.world.gen.config.SupplierSurfaceBuilderConfig;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;

public class ConfiguredSurfaceBuilders {
  public static final ConfiguredRegistry<ConfiguredSurfaceBuilder<?>> REGISTRY = new ConfiguredRegistry<>(MysticalBiomes.MODID, WorldGenRegistries.CONFIGURED_SURFACE_BUILDER);
  public static final ConfiguredSurfaceBuilder<?> CONFIGURED_DEFAULT_GRASS = register("default_grass", new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, SurfaceBuilder.CONFIG_GRASS));
  public static final ConfiguredSurfaceBuilder<?> CONFIGURED_SOFT_STONE = register("soft_stone", new ConfiguredSurfaceBuilder<>(ModSurfaces.SUPPLIER_DEFAULT, new SupplierSurfaceBuilderConfig(new ResourceLocation("mysticalworld", "soft_stone"))));
  public static final ConfiguredSurfaceBuilder<?> CONFIGURED_UNCANNY = register("uncanny_beach", new ConfiguredSurfaceBuilder<>(ModSurfaces.SUPPLIER_DEFAULT, new SupplierSurfaceBuilderConfig(new LazyStateSupplier(Blocks.GRASS_BLOCK.defaultBlockState()), new LazyStateSupplier(Blocks.DIRT.defaultBlockState()), new LazyStateSupplier("mysticalworld", "uncanny_gravel"))));

  private static ConfiguredSurfaceBuilder<?> register(String id, ConfiguredSurfaceBuilder<?> feature) {
    return REGISTRY.register(id, feature);
  }
}
