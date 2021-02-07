package noobanidus.mods.mysticalbiomes.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import noobanidus.libs.noobutil.config.BiomeConfig;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

  public static List<BiomeConfig> BIOMES = new ArrayList<>();

  private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

  public static BiomeConfig.Builder config = new BiomeConfig.Builder(BIOMES);

  public static BiomeConfig SPROUT_PLAINS = config.build("sprout_plains", 5);
  public static BiomeConfig STONE_WASTES = config.build("stone_wastes", 2);
  public static BiomeConfig UNCANNY_FOREST = config.build("uncanny_forest", 1);

  public static ForgeConfigSpec COMMON_CONFIG;

  static {
    BIOMES.forEach(o -> o.apply(COMMON_BUILDER));
    COMMON_CONFIG = COMMON_BUILDER.build();
  }

  public static void loadConfig(ForgeConfigSpec spec, Path path) {
    CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
    configData.load();
    spec.setConfig(configData);
  }
}
