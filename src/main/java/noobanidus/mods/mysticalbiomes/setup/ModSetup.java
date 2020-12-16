package noobanidus.mods.mysticalbiomes.setup;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import noobanidus.mods.mysticalbiomes.init.ConfiguredStructures;
import noobanidus.mods.mysticalbiomes.init.ModStructures;

@SuppressWarnings("deprecation")
public class ModSetup {

  public static void init(FMLCommonSetupEvent event) {
    event.enqueueWork(() -> {
      ModStructures.setupStructures();
      ConfiguredStructures.registerStructures();
    });
  }
}
