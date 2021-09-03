package noobanidus.mods.mysticalbiomes.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import noobanidus.libs.noobutil.world.gen.config.SupplierSurfaceBuilderConfig;
import noobanidus.libs.noobutil.world.gen.feature.SupplierDefaultSurfaceBuilder;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MysticalBiomes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSurfaces {
  private static final Set<SurfaceBuilder<?>> SURFACES = new HashSet<>();

  public static SurfaceBuilder<SupplierSurfaceBuilderConfig> SUPPLIER_DEFAULT = register("supplier_default", new SupplierDefaultSurfaceBuilder(SupplierSurfaceBuilderConfig.CODEC));

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
