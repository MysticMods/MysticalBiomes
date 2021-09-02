package noobanidus.mods.mysticalbiomes.setup;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import noobanidus.mods.mysticalbiomes.init.*;

@SuppressWarnings("deprecation")
public class ModSetup {

  public static void init(FMLCommonSetupEvent event) {
    event.enqueueWork(() -> {
      ConfiguredFeatures.REGISTRY.registration();
      ConfiguredStructures.REGISTRY.registration();
      ConfiguredSurfaceBuilders.REGISTRY.registration();
      ModBiomes.registration();
      ModStructures.setupStructures();
      ConfiguredStructures.registerStructures();
    });
  }
}
