package noobanidus.mods.mysticalbiomes.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.CoralPlantBlock;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.ColumnBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.MegaPineFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.GiantTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import noobanidus.libs.noobutil.registry.ConfiguredRegistry;
import noobanidus.libs.noobutil.types.LazyStateSupplier;
import noobanidus.libs.noobutil.world.gen.config.*;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;
import noobanidus.mods.mysticalbiomes.world.placer.ColumnBasePlacer;

import java.util.Arrays;

public class ConfiguredFeatures {
  public static final ConfiguredRegistry<ConfiguredFeature<?, ?>> REGISTRY = new ConfiguredRegistry<>(MysticalBiomes.MODID, WorldGenRegistries.CONFIGURED_FEATURE);

  private static final ConfiguredFeature<?, ?> EMPTY = register("empty", ModFeatures.EMPTY.configured(IFeatureConfig.NONE));

  private static final ConfiguredFeature<?, ?> STANDARD_BOULDER = register("standard_boulder", ModFeatures.BIG_ROCK.configured(new BlockStateRadiusFeatureConfig(new SimpleBlockStateProvider(Blocks.COBBLESTONE.defaultBlockState()), 2)));
  private static final ConfiguredFeature<?, ?> ORE_BOULDER = register("ore_boulder", ModFeatures.ROCK_ORE.configured(new TwoBlockStateRadiusFeatureConfig(new SimpleBlockStateProvider(Blocks.COBBLESTONE.defaultBlockState()),
      new WeightedBlockStateProvider()
          .add(Blocks.IRON_ORE.defaultBlockState(), 3)
          .add(Blocks.COAL_ORE.defaultBlockState(), 30)
          .add(Blocks.GOLD_ORE.defaultBlockState(), 1)
          .add(Blocks.COBBLESTONE.defaultBlockState(), 10),
      2)));
  public static final ConfiguredFeature<?, ?> BOULDER_FEATURE = register("boulder", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(ORE_BOULDER.weighted(0.4f), STANDARD_BOULDER.weighted(0.1f)), EMPTY))
      .decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE)); // HEIGHTMAP_PLACEMENT).countRandom(1));

  public static final ConfiguredFeature<?, ?> UNCANNY_MUSHROOMS_BROWN = register("uncanny_mushrooms_brown", ModFeatures.SUPPLIER_RANDOM_PATCH.configured((new SupplierBlockClusterFeatureConfig.Builder(new LazyStateSupplier("mysticalworld", "brown_mushroom"), SimpleBlockPlacer.INSTANCE)).tries(64).noProjection().build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(1));

  public static final ConfiguredFeature<?, ?> UNCANNY_MUSHROOMS_RED = register("uncanny_mushrooms_red", ModFeatures.SUPPLIER_RANDOM_PATCH.configured((new SupplierBlockClusterFeatureConfig.Builder(new LazyStateSupplier("mysticalworld", "red_mushroom"), SimpleBlockPlacer.INSTANCE)).tries(64).noProjection().build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(1));

  public static final ConfiguredFeature<?, ?> UNCANNY_MUSHROOMS = register("uncanny_mushrooms", ModFeatures.SUPPLIER_RANDOM_PATCH.configured((new SupplierBlockClusterFeatureConfig.Builder(new LazyStateSupplier("mysticalworld", "uncanny_mushroom"), SimpleBlockPlacer.INSTANCE)).tries(64).noProjection().build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(1));

  public static final ConfiguredFeature<?, ?> ORE_WEIGHTED_BOULDER_FEATURE = register("ore_weighted_boulder", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(ORE_BOULDER.weighted(0.8f), STANDARD_BOULDER.weighted(0.1f)), EMPTY))
      .decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE)); // HEIGHTMAP_PLACEMENT).countRandom(1));

  public static final ConfiguredFeature<?, ?> TALL_RED_MUSHROOM = register("tall_red_mushroom", ModFeatures.TALL_RED_MUSHROOM.configured(new BiggerMushroomFeatureConfig(new SimpleBlockStateProvider(Blocks.RED_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.DOWN, false)), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.defaultBlockState()), 2)));

  public static final ConfiguredFeature<?, ?> WIDE_TALL_RED_MUSHROOM = register("wide_tall_red_mushroom", ModFeatures.TALL_RED_MUSHROOM.configured(new BiggerMushroomFeatureConfig(new SimpleBlockStateProvider(Blocks.RED_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.DOWN, false)), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.defaultBlockState()), 3)));

  public static final ConfiguredFeature<?, ?> TALL_RED_MUSHROOMS = register("tall_red_mushrooms", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(WIDE_TALL_RED_MUSHROOM.weighted(0.3f)), TALL_RED_MUSHROOM)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1f, 1))));

  public static final ConfiguredFeature<?, ?> HUGE_UNCANNY_MUSHROOM = register("huge_uncanny_mushroom", ModFeatures.TALL_RED_MUSHROOM.configured(new BiggerMushroomFeatureConfig((new mysticmods.mysticalworld.world.SupplierBlockStateProvider("mysticalworld", "uncanny_mushroom_block")).addPair("down", false), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.defaultBlockState()), 2, 5, 3)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.3f, 1))));

  public static final ConfiguredFeature<?, ?> SPREAD_OAK_TREES = register("spread_oak_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(Features.FANCY_OAK.weighted(0.5f), Features.FANCY_OAK_BEES_0002.weighted(0.05f), Features.OAK_BEES_0002.weighted(0.3f)), Features.OAK)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));

  public static final ConfiguredFeature<?, ?> TOTEM = register("totem", ModFeatures.TOTEM.configured(new StructureFeatureConfig(0)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.01f, 1))));

  public static final ConfiguredFeature<?, ?> STUMPS = register("stump", ModFeatures.STUMP.configured(new StructureFeatureConfig(1)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.8f, 1))));

  public static final ConfiguredFeature<?, ?> PATCH_WILD_AUBERGINE = register("patch_wild_aubergine", ModFeatures.SUPPLIER_RANDOM_PATCH.configured((new SupplierBlockClusterFeatureConfig.Builder(new LazyStateSupplier("mysticalworld", "wild_aubergine"), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(new LazyStateSupplier(Blocks.GRASS_BLOCK.defaultBlockState()))).replaceable().noProjection().build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));

  public static final ConfiguredFeature<?, ?> PATCH_WILD_WART = register("patch_wild_wart", ModFeatures.SUPPLIER_RANDOM_PATCH.configured((new SupplierBlockClusterFeatureConfig.Builder(new LazyStateSupplier("mysticalworld", "wild_wart"), SimpleBlockPlacer.INSTANCE)).tries(32).whitelist(ImmutableSet.of(new LazyStateSupplier(Blocks.GRASS_BLOCK.defaultBlockState()))).replaceable().noProjection().build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(3));

  public static final ConfiguredFeature<?, ?> DISK_GRAVEL = register("supplier_disk_gravel", ModFeatures.SUPPLIER_DISK.configured(new SupplierSphereReplaceConfig(Blocks.GRAVEL.defaultBlockState(), FeatureSpread.of(1, 4), 1, ImmutableList.of(new LazyStateSupplier("mysticalworld", "soft_stone")))).countRandom(3).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));

  public static final ConfiguredFeature<?, ?> DISK_UNCANNY_GRAVEL = register("disk_uncanny_gravel", ModFeatures.SUPPLIER_REPLACE_DISK.configured(new SupplierSphereReplaceConfig(new LazyStateSupplier("mysticalworld", "uncanny_gravel"), FeatureSpread.of(2, 3), 2, ImmutableList.of(new LazyStateSupplier(Blocks.DIRT.defaultBlockState()), new LazyStateSupplier(Blocks.GRASS_BLOCK.defaultBlockState())))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));

  public static final ConfiguredFeature<?, ?> DISK_UNCANNY_SAND = register("disk_uncanny_sand", ModFeatures.SUPPLIER_REPLACE_DISK.configured(new SupplierSphereReplaceConfig(new LazyStateSupplier("mysticalworld", "uncanny_sand"), FeatureSpread.of(2, 4), 2, ImmutableList.of(new LazyStateSupplier(Blocks.DIRT.defaultBlockState()), new LazyStateSupplier(Blocks.GRASS_BLOCK.defaultBlockState())))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).count(3));

  public static final ConfiguredFeature<?, ?> DISK_ANDESITE = register("supplier_disk_andesite", ModFeatures.SUPPLIER_DISK.configured(new SupplierSphereReplaceConfig(Blocks.ANDESITE.defaultBlockState(), FeatureSpread.of(1, 4), 1, ImmutableList.of(new LazyStateSupplier("mysticalworld", "soft_stone")))).countRandom(1).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));

  public static final ConfiguredFeature<?, ?> SMALL_COBBLE_TREE = register("small_cobble_tree", ModFeatures.SUPPLIER_RANDOM_PATCH.configured(new SupplierBlockClusterFeatureConfig.Builder(new LazyStateSupplier(Blocks.COBBLESTONE.defaultBlockState()), new ColumnBasePlacer(2, 5, 1, 3, Arrays.asList(new LazyStateSupplier("mysticalworld", "soft_stone"), new LazyStateSupplier(Blocks.GRAVEL.defaultBlockState()), new LazyStateSupplier(Blocks.ANDESITE.defaultBlockState())), 8)).tries(8).noProjection().whitelist(Sets.newHashSet(new LazyStateSupplier("mysticalworld", "soft_stone"), new LazyStateSupplier(Blocks.GRAVEL.defaultBlockState()))).build()).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.9F, 1))));

  public static final ConfiguredFeature<?, ?> SMALL_SOFT_STONE_TREE = register("small_soft_stone_tree", ModFeatures.SUPPLIER_RANDOM_PATCH.configured((new SupplierBlockClusterFeatureConfig.Builder(new LazyStateSupplier("mysticalworld", "soft_stone"), new ColumnBlockPlacer(2, 5))).tries(10).noProjection().whitelist(Sets.newHashSet(new LazyStateSupplier("mysticalworld", "soft_stone"))).build()).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));

  public static final ConfiguredFeature<?, ?> COBBLE_TREE = register("cobble_tree", ModFeatures.SUPPLIER_RANDOM_PATCH.configured(new SupplierBlockClusterFeatureConfig.Builder(new LazyStateSupplier(Blocks.COBBLESTONE.defaultBlockState()), new ColumnBasePlacer(3, 8, 3, 5, Arrays.asList(new LazyStateSupplier("mysticalworld", "soft_stone"), new LazyStateSupplier(Blocks.GRAVEL.defaultBlockState()), new LazyStateSupplier(Blocks.ANDESITE.defaultBlockState())), 8)).tries(10).noProjection().whitelist(Sets.newHashSet(new LazyStateSupplier("mysticalworld", "soft_stone"), new LazyStateSupplier(Blocks.GRAVEL.defaultBlockState()))).build()).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));

  public static final ConfiguredFeature<?, ?> SOFT_STONE_TREE = register("soft_stone_tree", ModFeatures.SUPPLIER_RANDOM_PATCH.configured((new SupplierBlockClusterFeatureConfig.Builder(new LazyStateSupplier("mysticalworld", "soft_stone"), new ColumnBlockPlacer(3, 8))).tries(12).noProjection().whitelist(Sets.newHashSet(new LazyStateSupplier("mysticalworld", "soft_stone"))).build()).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.3F, 1))));

  public static final ConfiguredFeature<?, ?> LARGE_COBBLE_TREE = register("large_cobble_tree", ModFeatures.SUPPLIER_RANDOM_PATCH.configured(new SupplierBlockClusterFeatureConfig.Builder(new LazyStateSupplier(Blocks.COBBLESTONE.defaultBlockState()), new ColumnBasePlacer(5, 15, 3, 6, Arrays.asList(new LazyStateSupplier("mysticalworld", "soft_stone"), new LazyStateSupplier(Blocks.GRAVEL.defaultBlockState()), new LazyStateSupplier(Blocks.ANDESITE.defaultBlockState())), 6)).tries(10).noProjection().whitelist(Sets.newHashSet(new LazyStateSupplier("mysticalworld", "soft_stone"), new LazyStateSupplier(Blocks.GRAVEL.defaultBlockState()))).build()).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.3F, 1))));

  public static final ConfiguredFeature<?, ?> LARGE_SOFT_STONE_TREE = register("large_soft_stone_tree", ModFeatures.SUPPLIER_RANDOM_PATCH.configured((new SupplierBlockClusterFeatureConfig.Builder(new LazyStateSupplier("mysticalworld", "soft_stone"), new ColumnBlockPlacer(5, 15))).tries(12).noProjection().whitelist(Sets.newHashSet(new LazyStateSupplier("mysticalworld", "soft_stone"))).build()).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.6F, 1))));

  public static final ConfiguredFeature<?, ?> REGULAR_LAVA = register("regular_lake_lava", Feature.LAKE.configured(new BlockStateFeatureConfig(Blocks.LAVA.defaultBlockState())).decorated(ModPlacers.LAVA_CENTER.configured(new ChanceConfig(30))));

  public static final ConfiguredFeature<?, ?> CORAL_LAKE = register("coral_lake", ModFeatures.WEIGHTED_LAKE.configured(new WeightedBlockStateFeatureConfig(new WeightedBlockStateProvider()
      .add(Blocks.DEAD_BRAIN_CORAL_BLOCK.defaultBlockState(), 20)
      .add(Blocks.DEAD_BUBBLE_CORAL_BLOCK.defaultBlockState(), 20)
      .add(Blocks.DEAD_FIRE_CORAL_BLOCK.defaultBlockState(), 20)
      .add(Blocks.DEAD_HORN_CORAL_BLOCK.defaultBlockState(), 20)
      .add(Blocks.DEAD_TUBE_CORAL_BLOCK.defaultBlockState(), 20)
      .add(Blocks.DEAD_TUBE_CORAL.defaultBlockState().setValue(CoralPlantBlock.WATERLOGGED, true), 2)
      .add(Blocks.DEAD_BRAIN_CORAL.defaultBlockState().setValue(CoralPlantBlock.WATERLOGGED, true), 2)
      .add(Blocks.DEAD_BUBBLE_CORAL.defaultBlockState().setValue(CoralPlantBlock.WATERLOGGED, true), 2)
      .add(Blocks.DEAD_FIRE_CORAL.defaultBlockState().setValue(CoralPlantBlock.WATERLOGGED, true), 2)
      .add(Blocks.DEAD_HORN_CORAL.defaultBlockState().setValue(CoralPlantBlock.WATERLOGGED, true), 2)
      .add(Blocks.BONE_BLOCK.defaultBlockState(), 8))).decorated(ModPlacers.WATER_CENTER.configured(new ChanceConfig(5))));
  public static final ConfiguredFeature<?, ?> CENTERED_LAKE = register("centered_lake", Feature.LAKE.configured(new BlockStateFeatureConfig(Blocks.WATER.defaultBlockState())).decorated(ModPlacers.WATER_CENTER.configured(new ChanceConfig(4))));

  public static final ConfiguredFeature<?, ?> PATCH_STONEPETAL = register("patch_stonepetal", ModFeatures.SUPPLIER_RANDOM_PATCH.configured((new SupplierBlockClusterFeatureConfig.Builder(new LazyStateSupplier("mysticalworld", "stonepetal"), SimpleBlockPlacer.INSTANCE)).tries(18).build()).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(2));

  public static final ConfiguredFeature<?, ?> SURFACE_FOSSIL = register("surface_fossil", ModFeatures.SURFACE_FOSSIL.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_SQUARE).chance(75));

  public static final ConfiguredFeature<?, ?> SMALL_SURFACE_GOLD = register("small_surface_gold", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GOLD_ORE.defaultBlockState(), 4)).range(48).squared().count(8));

  public static final ConfiguredFeature<?, ?> MEGA_DARK = register("spooky_mega_dark_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.defaultBlockState()), new SimpleBlockStateProvider(Blocks.DARK_OAK_LEAVES.defaultBlockState()), new MegaPineFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), FeatureSpread.of(9, 0)), new GiantTrunkPlacer(12, 2, 4), new TwoLayerFeature(1, 1, 2))).build()).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.4F, 1))));

  public static final ConfiguredFeature<?, ?> DARK_TREE = register("dark_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.defaultBlockState()), new SimpleBlockStateProvider(Blocks.DARK_OAK_LEAVES.defaultBlockState()), new SpruceFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(1, 2), FeatureSpread.of(4, 0)), new StraightTrunkPlacer(6, 3, 0), new TwoLayerFeature(2, 0, 1))).ignoreVines().build()).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(11, 1.0F, 2))));

  private static WeightedBlockStateProvider PUMPKINS = null;

  public static WeightedBlockStateProvider getPumpkins() {
    if (PUMPKINS == null) {
      PUMPKINS = new WeightedBlockStateProvider();
      PUMPKINS.add(Blocks.JACK_O_LANTERN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, Direction.NORTH), 1);
      PUMPKINS.add(Blocks.JACK_O_LANTERN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, Direction.EAST), 1);
      PUMPKINS.add(Blocks.JACK_O_LANTERN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, Direction.WEST), 1);
      PUMPKINS.add(Blocks.JACK_O_LANTERN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, Direction.SOUTH), 1);
    }
    return PUMPKINS;
  }

  public static final ConfiguredFeature<?, ?> LANTERNS = register("lantern", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(getPumpkins(), new SimpleBlockStateProvider(Blocks.AIR.defaultBlockState()), new BlobFoliagePlacer(FeatureSpread.of(1, 1), FeatureSpread.of(1, 1), 1), new StraightTrunkPlacer(1, 0, 0), new TwoLayerFeature(2, 0, 1))).ignoreVines().build()).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.7F, 1))));

  private static ConfiguredFeature<?, ?> register(String id, ConfiguredFeature<?, ?> feature) {
    return REGISTRY.register(id, feature);
  }

  public static void load() {
  }
}
