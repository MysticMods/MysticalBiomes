package noobanidus.mods.mysticalbiomes.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;
import net.minecraft.world.gen.surfacebuilders.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;
import noobanidus.mods.mysticalbiomes.config.SupplierSurfaceBuilderConfig;
import noobanidus.mods.mysticalbiomes.feature.SupplierDefaultSurfaceBuilder;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MysticalBiomes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSurfaces {
  private static Set<SurfaceBuilder<?>> SURFACES = new HashSet<>();

  public static SurfaceBuilder<SupplierSurfaceBuilderConfig> SMOOTH_STONE_SURFACE = register("smooth_stone", new SupplierDefaultSurfaceBuilder(SupplierSurfaceBuilderConfig.CODEC));

  private static <T extends ISurfaceBuilderConfig> SurfaceBuilder<T> register(String name, SurfaceBuilder<T> type) {
    ResourceLocation rl = new ResourceLocation(MysticalBiomes.MODID, name);
    type.setRegistryName(rl);
    SURFACES.add(type);
    return type;
  }

  @SubscribeEvent
  public static void register(RegistryEvent.Register<SurfaceBuilder<?>> event) {
    event.getRegistry().registerAll(SURFACES.toArray(new SurfaceBuilder[0]));
  }

}
