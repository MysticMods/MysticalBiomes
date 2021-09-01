package noobanidus.mods.mysticalbiomes.setup;

import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;
import noobanidus.mods.mysticalbiomes.init.ModStructures;

import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = MysticalBiomes.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WorldSetup {
  private static MethodHandle GETCODEC_METHOD = null;

  private static boolean getGetCodecMethod() {
    if (GETCODEC_METHOD == null) {
      // TODO: Beware that updating to mojmap using the standard gradle task *can and will* replace `func_230347_a_` with the actual mojmap name (if one is found). This will not cause a crash in a development environment, but will crash normal, obfuscated clients.
      Method codec = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
      MethodHandles.Lookup l = MethodHandles.lookup();
      try {
        GETCODEC_METHOD = l.unreflect(codec);
      } catch (IllegalAccessException e) {
        MysticalBiomes.LOG.error("Unable to unreflect ChunkGenerator codec getter.", e);
        return false;
      }
    }

    return true;
  }

  @Nullable
  private static ResourceLocation getChunkGenerator(ServerWorld serverWorld) {
    if (!getGetCodecMethod()) {
      return null;
    }

    ResourceLocation chunkGen;
    try {
      //noinspection unchecked
      chunkGen = Registry.CHUNK_GENERATOR_CODEC.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invokeExact(serverWorld.getChunkProvider().generator));
    } catch (Throwable throwable) {
      MysticalBiomes.LOG.error("Unable to look up " + serverWorld + "'s chunk provider's generator resource location.", throwable);
      return null;
    }
    return chunkGen;
  }

  @SubscribeEvent
  public static void addDimensionalSpacing(WorldEvent.Load event) {
    if (event.getWorld() instanceof ServerWorld) {
      ServerWorld serverWorld = (ServerWorld) event.getWorld();

      if (serverWorld.getChunkProvider().getChunkGenerator() instanceof FlatChunkGenerator && serverWorld.getDimensionKey().equals(World.OVERWORLD)) {
        return;
      }

      ResourceLocation chunkGen = getChunkGenerator(serverWorld);
      if (chunkGen != null && chunkGen.getNamespace().equals("terraforrged")) {
        return;
      }

      Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkProvider().generator.func_235957_b_().func_236195_a_());
      if (serverWorld.getDimensionKey().equals(World.OVERWORLD)) {
        tempMap.put(ModStructures.MUSHROOM_HUT, DimensionStructuresSettings.field_236191_b_.get(ModStructures.MUSHROOM_HUT));
        tempMap.put(ModStructures.HENGE, DimensionStructuresSettings.field_236191_b_.get(ModStructures.HENGE));
      } else {
        tempMap.remove(ModStructures.MUSHROOM_HUT);
        tempMap.remove(ModStructures.HENGE);
      }
      serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = tempMap;
    }
  }
}
