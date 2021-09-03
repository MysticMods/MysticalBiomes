package noobanidus.mods.mysticalbiomes.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;
import noobanidus.mods.mysticalbiomes.world.SupplierBlockStateProvider;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MysticalBiomes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTypes {
  private static final Set<BlockStateProviderType<?>> TYPES = new HashSet<>();

  public static BlockStateProviderType<?> SUPPLIER_BLOCK_STATE_PROVIDER = register("supplier_block_state_provider", new BlockStateProviderType<>(SupplierBlockStateProvider.CODEC));

  private static BlockStateProviderType<?> register(String name, BlockStateProviderType<?> type) {
    ResourceLocation rl = new ResourceLocation(MysticalBiomes.MODID, name);
    type.setRegistryName(rl);
    TYPES.add(type);
    return type;
  }

  @SubscribeEvent
  public static void register(RegistryEvent.Register<BlockStateProviderType<?>> event) {
    event.getRegistry().registerAll(TYPES.toArray(new BlockStateProviderType[0]));
  }

}
