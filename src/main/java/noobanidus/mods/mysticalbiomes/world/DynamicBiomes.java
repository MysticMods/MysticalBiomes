package noobanidus.mods.mysticalbiomes.world;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = MysticalBiomes.MODID)
public class DynamicBiomes {
  private static MutableRegistry<Biome> BIOME_REGISTRY = null;

  @SubscribeEvent
  public static void onServerStarting(FMLServerAboutToStartEvent event) {
    BIOME_REGISTRY = event.getServer().registryAccess().registryOrThrow(Registry.BIOME_REGISTRY);
  }

  @Nullable
  public static RegistryKey<Biome> byId(int id) {
    Biome biome = BIOME_REGISTRY.byId(id);
    if (biome == null) {
      return null;
    }
    return BIOME_REGISTRY.getResourceKey(biome).orElse(null);
  }
}
