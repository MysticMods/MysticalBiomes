package noobanidus.mods.mysticalbiomes.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import noobanidus.libs.noobutil.world.gen.placement.CenteredLavaPlacement;
import noobanidus.libs.noobutil.world.gen.placement.CenteredWaterPlacement;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;
import noobanidus.mods.mysticalbiomes.world.placement.SeparatedPlacement;
import noobanidus.mods.mysticalbiomes.world.placer.ColumnBasePlacer;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MysticalBiomes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModPlacers {
  private static Set<BlockPlacerType<?>> PLACERS = new HashSet<>();
  private static Set<Placement<?>> PLACEMENTS = new HashSet<>();

  public static final BlockPlacerType<ColumnBasePlacer> COLUMN_BASE_PLACER = register("column_base_placer", new BlockPlacerType<>(ColumnBasePlacer.CODEC));

  public static final Placement<ChanceConfig> LAVA_CENTER = register("lava_lake_center", new CenteredLavaPlacement(ChanceConfig.CODEC));
  public static final Placement<ChanceConfig> WATER_CENTER = register("water_lake_center", new CenteredWaterPlacement(ChanceConfig.CODEC));

  private static <T extends BlockPlacer> BlockPlacerType<T> register(String name, BlockPlacerType<T> feature) {
    ResourceLocation rl = new ResourceLocation(MysticalBiomes.MODID, name);
    feature.setRegistryName(rl);
    PLACERS.add(feature);
    return feature;
  }

  private static <T extends IPlacementConfig> Placement<T> register (String name, Placement<T> placement) {
    ResourceLocation rl = new ResourceLocation(MysticalBiomes.MODID, name);
    placement.setRegistryName(rl);
    PLACEMENTS.add(placement);
    return placement;
  }

  @SubscribeEvent
  public static void registerPlacers (RegistryEvent.Register<BlockPlacerType<?>> event) {
    event.getRegistry().registerAll(PLACERS.toArray(new BlockPlacerType[0]));
  }

  @SubscribeEvent
  public static void registerPlacements (RegistryEvent.Register<Placement<?>> event) {
    event.getRegistry().registerAll(PLACEMENTS.toArray(new Placement[0]));
  }
}
