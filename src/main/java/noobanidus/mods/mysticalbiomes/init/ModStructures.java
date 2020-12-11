package noobanidus.mods.mysticalbiomes.init;

import com.google.common.collect.ImmutableMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;
import noobanidus.mods.mysticalbiomes.structures.MushroomHutStructure;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MysticalBiomes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModStructures {
  private static Set<Structure<?>> STRUCTURES = new HashSet<>();

  public static Structure<NoFeatureConfig> MUSHROOM_HUT = register("mushroom_hut", new MushroomHutStructure(NoFeatureConfig.field_236558_a_));

  private static <T extends IFeatureConfig> Structure<T> register(String name, Structure<T> feature) {
    ResourceLocation rl = new ResourceLocation(MysticalBiomes.MODID, name);
    feature.setRegistryName(rl);
    STRUCTURES.add(feature);
    return feature;
  }

  @SubscribeEvent
  public static void register(RegistryEvent.Register<Structure<?>> event) {
    event.getRegistry().registerAll(STRUCTURES.toArray(new Structure[0]));

    Structure.NAME_STRUCTURE_BIMAP.put(Objects.requireNonNull(MUSHROOM_HUT.getRegistryName()).toString(), MUSHROOM_HUT);
    DimensionStructuresSettings.field_236191_b_ = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
        .putAll(DimensionStructuresSettings.field_236191_b_)
        .put(MUSHROOM_HUT, new StructureSeparationSettings(12, 5, 0x036dea02f))
        .build();
  }
}
