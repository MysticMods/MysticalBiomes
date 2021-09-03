package noobanidus.mods.mysticalbiomes;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import noobanidus.libs.noobutil.command.GenDataCommand;
import noobanidus.libs.noobutil.registrate.CustomRegistrate;
import noobanidus.mods.mysticalbiomes.config.ConfigManager;
import noobanidus.mods.mysticalbiomes.gen.LootTableGenerator;
import noobanidus.mods.mysticalbiomes.setup.ModSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("mysticalbiomes")
public class MysticalBiomes {
  public static final String MODID = "mysticalbiomes";
  public static CustomRegistrate REGISTRATE;
  public static Logger LOG = LogManager.getLogger();

  public MysticalBiomes() {
    REGISTRATE = CustomRegistrate.create(MODID);

    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigManager.COMMON_CONFIG);
    IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    bus.addListener(ModSetup::init);
    bus.addListener(this::onDataGenerate);

    MinecraftForge.EVENT_BUS.addListener(this::commandRegisterEvent);
  }

  public void commandRegisterEvent(RegisterCommandsEvent event) {
    GenDataCommand.dataGenCommand(event.getDispatcher());
  }

  public void onDataGenerate(GatherDataEvent event) {
    if (event.includeServer()) {
      event.getGenerator().addProvider(new LootTableGenerator(event.getGenerator()));
    }
  }
}
