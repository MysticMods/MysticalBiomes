package noobanidus.mods.mysticalbiomes.setup;

import net.minecraft.world.World;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;
import noobanidus.mods.mysticalbiomes.init.ModStructures;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = MysticalBiomes.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WorldSetup {
  @SubscribeEvent
  public static void addDimensionalSpacing(WorldEvent.Load event) {
    if (event.getWorld() instanceof ServerWorld) {
      ServerWorld serverWorld = (ServerWorld) event.getWorld();

      if (serverWorld.getChunkProvider().getChunkGenerator() instanceof FlatChunkGenerator && serverWorld.getDimensionKey().equals(World.OVERWORLD)) {
        return;
      }

      Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkProvider().generator.func_235957_b_().func_236195_a_());
      tempMap.put(ModStructures.MUSHROOM_HUT, DimensionStructuresSettings.field_236191_b_.get(ModStructures.MUSHROOM_HUT));
      serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = tempMap;
    }
  }
}
