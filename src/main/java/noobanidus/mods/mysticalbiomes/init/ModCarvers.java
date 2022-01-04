package noobanidus.mods.mysticalbiomes.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;
import noobanidus.mods.mysticalbiomes.world.carvers.CrescentCanyonCarver;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MysticalBiomes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCarvers {
  private static final Set<WorldCarver<?>> CARVERS = new HashSet<>();

  public static final WorldCarver<ProbabilityConfig> CRESCENT_CARVER = register("crescent_carver", new CrescentCanyonCarver(ProbabilityConfig.CODEC));

  private static <T extends ICarverConfig> WorldCarver<T> register(String name, WorldCarver<T> feature) {
    ResourceLocation rl = new ResourceLocation(MysticalBiomes.MODID, name);
    feature.setRegistryName(rl);
    CARVERS.add(feature);
    return feature;
  }

  @SubscribeEvent
  public static void register(RegistryEvent.Register<WorldCarver<?>> event) {
    event.getRegistry().registerAll(CARVERS.toArray(new WorldCarver[0]));
  }
}
