package noobanidus.mods.mysticalbiomes.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;
import noobanidus.libs.noobutil.world.gen.placer.ColumnBaseBlockPlacer;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MysticalBiomes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModPlacers {
  private static Set<BlockPlacerType<?>> PLACERS = new HashSet<>();

  public static final BlockPlacerType<ColumnBaseBlockPlacer> COLUMN_BASE_PLACER = register("column_base_placer", new BlockPlacerType<>(ColumnBaseBlockPlacer.CODEC));

  private static <T extends BlockPlacer> BlockPlacerType<T> register(String name, BlockPlacerType<T> feature) {
    ResourceLocation rl = new ResourceLocation(MysticalBiomes.MODID, name);
    feature.setRegistryName(rl);
    PLACERS.add(feature);
    return feature;
  }

  @SubscribeEvent
  public static void register(RegistryEvent.Register<BlockPlacerType<?>> event) {
    event.getRegistry().registerAll(PLACERS.toArray(new BlockPlacerType[0]));
  }
}
