package noobanidus.mods.mysticalbiomes.world;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import javax.annotation.Nullable;

public class Reference {
  public static DynamicRegistries dynamicRegistry;

  @Nullable
  private static MutableRegistry<Biome> getBiomeRegistry() {
    if (dynamicRegistry == null) {
      dynamicRegistry = ServerLifecycleHooks.getCurrentServer().registryAccess();
    }
    return dynamicRegistry.registryOrThrow(Registry.BIOME_REGISTRY);
  }

  public static int getBiomeID(RegistryKey<Biome> key) {
    MutableRegistry<Biome> reg = getBiomeRegistry();
    if (reg == null) {
      return -1;
    }

    Biome biome = reg.get(key);
    if (biome == null) {
      return -1;
    }

    return reg.getId(biome);
  }
}
