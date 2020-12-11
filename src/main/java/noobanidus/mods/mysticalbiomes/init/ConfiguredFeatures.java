package noobanidus.mods.mysticalbiomes.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.block.Blocks;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.ColumnBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;
import noobanidus.libs.noobutil.types.LazyStateSupplier;
import noobanidus.libs.noobutil.world.gen.config.BlockStateRadiusFeatureConfig;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;
import noobanidus.mods.mysticalbiomes.config.SupplierBlockClusterFeatureConfig;
import noobanidus.mods.mysticalbiomes.config.SupplierSphereReplaceConfig;
import noobanidus.mods.mysticalbiomes.placer.ColumnBaseBlockPlacer;

import java.util.Arrays;

public class ConfiguredFeatures {
  public static final ConfiguredFeature<?, ?> TALL_RED_MUSHROOM = register("tall_red_mushroom", ModFeatures.TALL_RED_MUSHROOM.withConfiguration(new BigMushroomFeatureConfig(new SimpleBlockStateProvider(Blocks.RED_MUSHROOM_BLOCK.getDefaultState().with(HugeMushroomBlock.DOWN, false)), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.getDefaultState()), 2)));

  public static final ConfiguredFeature<?, ?> WIDE_TALL_RED_MUSHROOM = register("wide_tall_red_mushroom", ModFeatures.TALL_RED_MUSHROOM.withConfiguration(new BigMushroomFeatureConfig(new SimpleBlockStateProvider(Blocks.RED_MUSHROOM_BLOCK.getDefaultState().with(HugeMushroomBlock.DOWN, false)), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.getDefaultState()), 3)));

  public static final ConfiguredFeature<?, ?> TALL_RED_MUSHROOMS = register("tall_red_mushrooms", Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(WIDE_TALL_RED_MUSHROOM.withChance(0.3f)), TALL_RED_MUSHROOM)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.1f, 1))));

  public static final ConfiguredFeature<?, ?> SPREAD_OAK_TREES = register("spread_oak_trees", Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(Features.FANCY_OAK.withChance(0.5f), Features.FANCY_OAK_BEES_0002.withChance(0.05f), Features.OAK_BEES_0002.withChance(0.3f)), Features.OAK)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));

  public static final ConfiguredFeature<?, ?> PATCH_WILD_AUBERGINE = register("patch_wild_aubergine", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SupplierBlockStateProvider("mysticalworld", "wild_aubergine_crop"), SimpleBlockPlacer.PLACER)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).replaceable().func_227317_b_().build()).withPlacement(Features.Placements.PATCH_PLACEMENT));

  public static final ConfiguredFeature<?, ?> DISK_GRAVEL = register("supplier_disk_gravel", ModFeatures.SUPPLIER_DISK.withConfiguration(new SupplierSphereReplaceConfig(Blocks.GRAVEL.getDefaultState(), FeatureSpread.func_242253_a(1, 4), 1, ImmutableList.of(new LazyStateSupplier("mysticalworld", "soft_stone")))).func_242732_c(3).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));

  public static final ConfiguredFeature<?, ?> DISK_ANDESITE = register("supplier_disk_andesite", ModFeatures.SUPPLIER_DISK.withConfiguration(new SupplierSphereReplaceConfig(Blocks.ANDESITE.getDefaultState(), FeatureSpread.func_242253_a(1, 4), 1, ImmutableList.of(new LazyStateSupplier("mysticalworld", "soft_stone")))).func_242732_c(1).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));

  public static final ConfiguredFeature<?, ?> COBBLE_TREE = register("cobble_tree", ModFeatures.SUPPLIER_RANDOM_PATCH.withConfiguration(new SupplierBlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.COBBLESTONE.getDefaultState()), new ColumnBaseBlockPlacer(5, 20, 3, 7, Arrays.asList(new LazyStateSupplier("mysticalworld", "soft_stone"), new LazyStateSupplier(Blocks.GRAVEL.getDefaultState()), new LazyStateSupplier(Blocks.ANDESITE.getDefaultState())), 6)).tries(15).noProjection().whitelist(Sets.newHashSet(new LazyStateSupplier("mysticalworld", "soft_stone"), new LazyStateSupplier(Blocks.GRAVEL.getDefaultState()))).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));

  public static final ConfiguredFeature<?, ?> SOFT_STONE_TREE = register("soft_stone_tree", ModFeatures.SUPPLIER_RANDOM_PATCH.withConfiguration((new SupplierBlockClusterFeatureConfig.Builder(new SupplierBlockStateProvider("mysticalworld", "soft_stone"), new ColumnBlockPlacer(5, 18))).tries(20).noProjection().whitelist(Sets.newHashSet(new LazyStateSupplier("mysticalworld", "soft_stone"))).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.6F, 1))));

  public static final ConfiguredFeature<?, ?> REGULAR_LAVA = register("regular_lake_lava", Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(Blocks.LAVA.getDefaultState())).withPlacement(Placement.LAVA_LAKE.configure(new ChanceConfig(20))));

  public static final ConfiguredFeature<?, ?> PATCH_PETRIFIED_BUSH = register("patch_petrified_bush", ModFeatures.SUPPLIER_RANDOM_PATCH.withConfiguration(new SupplierBlockClusterFeatureConfig.Builder(new SupplierBlockStateProvider("mysticalworld", "petrified_bush"), SimpleBlockPlacer.PLACER).tries(8).whitelist(ImmutableSet.of(new LazyStateSupplier("mysticalworld", "soft_stone"), new LazyStateSupplier(Blocks.GRAVEL.getDefaultState()), new LazyStateSupplier(Blocks.ANDESITE.getDefaultState()))).replaceable().noProjection().build()).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(10));

  public static final ConfiguredFeature<?, ?> PATCH_PETRIFIED_GRASS = register("patch_petrified_grass", ModFeatures.SUPPLIER_RANDOM_PATCH.withConfiguration(new SupplierBlockClusterFeatureConfig.Builder(new SupplierBlockStateProvider("mysticalworld", "petrified_grass"), SimpleBlockPlacer.PLACER).tries(128).whitelist(ImmutableSet.of(new LazyStateSupplier("mysticalworld", "soft_stone"), new LazyStateSupplier(Blocks.GRAVEL.getDefaultState()), new LazyStateSupplier(Blocks.ANDESITE.getDefaultState()))).replaceable().noProjection().build()).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(80));

  private static ConfiguredFeature<?, ?> register(String id, ConfiguredFeature<?, ?> feature) {
    return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(MysticalBiomes.MODID, id), feature);
  }

  public static void load() {
  }
}
