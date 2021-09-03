package noobanidus.mods.mysticalbiomes.init;

import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import noobanidus.libs.noobutil.types.IntPair;
import noobanidus.libs.noobutil.world.gen.config.*;
import noobanidus.libs.noobutil.world.gen.feature.StructureFeature;
import noobanidus.libs.noobutil.world.gen.feature.*;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;
import noobanidus.mods.mysticalbiomes.world.features.SupplierSphereReplaceWaterFeature;
import noobanidus.mods.mysticalbiomes.world.features.SurfaceFossilsFeature;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Mod.EventBusSubscriber(modid = MysticalBiomes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures {
  private static final Set<Feature<?>> FEATURES = new HashSet<>();

  public static final Feature<NoFeatureConfig> EMPTY = register("empty", new EmptyFeature());

  public static final Feature<NoFeatureConfig> SURFACE_FOSSIL = register("surface_fossil", new SurfaceFossilsFeature(NoFeatureConfig.field_236558_a_));
  public static final Feature<BiggerMushroomFeatureConfig> TALL_RED_MUSHROOM = register("tall_red_mushroom", new ConfigurableHeightBigMushroomFeature(BiggerMushroomFeatureConfig.CODEC));
  public static final Feature<SupplierBlockStateFeatureConfig> SUPPLIER_BLOCK_SPIKE = register("supplier_spike", new SupplierBlockSpikeFeature(SupplierBlockStateFeatureConfig.CODEC));
  public static final Feature<SupplierSphereReplaceConfig> SUPPLIER_DISK = register("supplier_disk", new SupplierSphereReplaceFeature(SupplierSphereReplaceConfig.CODEC));
  public static final Feature<SupplierSphereReplaceConfig> SUPPLIER_REPLACE_DISK = register("supplier_replace_disk", new SupplierSphereReplaceWaterFeature(SupplierSphereReplaceConfig.CODEC));
  public static final Feature<SupplierBlockClusterFeatureConfig> SUPPLIER_RANDOM_PATCH = register("supplier_random_patch", new SupplierRandomPatchFeature(SupplierBlockClusterFeatureConfig.CODEC));
  public static final Feature<BlockStateRadiusFeatureConfig> BIG_ROCK = register("big_rock", new RadiusBlockBlobFeature(BlockStateRadiusFeatureConfig.CODEC));
  public static final Feature<TwoBlockStateRadiusFeatureConfig> ROCK_ORE = register("big_rock_ore", new RadiusTwoBlockBlobFeature(TwoBlockStateRadiusFeatureConfig.CODEC));
  public static final Feature<WeightedBlockStateFeatureConfig> WEIGHTED_LAKE = register("weighted_lake", new WeightedLakesFeature(WeightedBlockStateFeatureConfig.CODEC));
  public static final Feature<StructureFeatureConfig> TOTEM = register("totem", new StructureFeature(StructureFeatureConfig.CODEC, new IntPair<>(16, new ResourceLocation(MysticalBiomes.MODID, "totem")), new IntPair<>(1, new ResourceLocation(MysticalBiomes.MODID, "totem_2"))));

  private static final List<IntPair<ResourceLocation>> STUMPS = IntStream.range(1, 31).mapToObj(o -> new IntPair<>(1, new ResourceLocation(MysticalBiomes.MODID, "stump_" + o))).collect(Collectors.toList());

  public static final Feature<StructureFeatureConfig> STUMP = register("stump", new StructureFeature(StructureFeatureConfig.CODEC, STUMPS));

  private static <T extends IFeatureConfig> Feature<T> register(String name, Feature<T> feature) {
    ResourceLocation rl = new ResourceLocation(MysticalBiomes.MODID, name);
    feature.setRegistryName(rl);
    FEATURES.add(feature);
    return feature;
  }

  @SubscribeEvent
  public static void register(RegistryEvent.Register<Feature<?>> event) {
    event.getRegistry().registerAll(FEATURES.toArray(new Feature[0]));
  }

  public static class Config {
    public static final BaseTreeFeatureConfig COBBLE_TREE = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.COBBLESTONE.getDefaultState()), new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()), new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3), new StraightTrunkPlacer(8, 4, 2), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build();
    public static final BaseTreeFeatureConfig MOSSY_COBBLE_TREE = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.MOSSY_COBBLESTONE.getDefaultState()), new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()), new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3), new StraightTrunkPlacer(8, 4, 2), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build();

    static {
      COBBLE_TREE.forcePlacement();
      MOSSY_COBBLE_TREE.forcePlacement();
    }
  }
}
