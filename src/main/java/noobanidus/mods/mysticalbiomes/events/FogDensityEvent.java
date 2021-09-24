package noobanidus.mods.mysticalbiomes.events;

import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;
import noobanidus.mods.mysticalbiomes.init.ModBiomes;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MysticalBiomes.MODID)
public class FogDensityEvent {
  @SubscribeEvent
  public static void onFog(EntityViewRenderEvent.FogDensity event) {
    if (event.getType() == FogRenderer.FogType.FOG_TERRAIN) {
      ActiveRenderInfo info = event.getInfo();
      if (info.getEntity().getY() < 60) {
        return;
      }
      if (info.getEntity().isInWater()) {
        return;
      }
      Biome biome = info.getEntity().level.getBiome(info.getBlockPosition());
      ResourceLocation rl = biome.getRegistryName();
      if (rl != null && (rl.equals(ModBiomes.UNCANNY_BEACH.getRegistryName()) || rl.equals(ModBiomes.UNCANNY_FOREST.getRegistryName()) || rl.equals(ModBiomes.UNCANNY_RIVER.getRegistryName()))) {
        event.setDensity(0.6f);
        event.setCanceled(true);
      }
    }
  }
}
