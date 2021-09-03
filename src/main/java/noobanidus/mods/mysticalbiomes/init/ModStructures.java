package noobanidus.mods.mysticalbiomes.init;

import com.google.common.collect.ImmutableList;
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
import noobanidus.mods.mysticalbiomes.world.structures.HengeStructure;
import noobanidus.mods.mysticalbiomes.world.structures.MushroomHutStructure;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MysticalBiomes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModStructures {
  private static final Set<Structure<?>> STRUCTURES = new HashSet<>();

  public static final Structure<NoFeatureConfig> MUSHROOM_HUT = register("mushroom_hut", new MushroomHutStructure(NoFeatureConfig.field_236558_a_));
  public static final Structure<NoFeatureConfig> HENGE = register("henge", new HengeStructure(NoFeatureConfig.field_236558_a_));

  private static <T extends IFeatureConfig> Structure<T> register(String name, Structure<T> feature) {
    ResourceLocation rl = new ResourceLocation(MysticalBiomes.MODID, name);
    feature.setRegistryName(rl);
    STRUCTURES.add(feature);
    return feature;
  }

  @SubscribeEvent
  public static void register(RegistryEvent.Register<Structure<?>> event) {
    event.getRegistry().registerAll(STRUCTURES.toArray(new Structure[0]));
  }

  public static void setupStructures() {
    setupStructure(MUSHROOM_HUT, new StructureSeparationSettings(10, 5, 1234567890), true);
    setupStructure(HENGE, new StructureSeparationSettings(40, 20, 11887642), true);
  }

  public static <F extends Structure<?>> void setupStructure(F structure, StructureSeparationSettings structureSeparationSettings, boolean transformSurroundingLand) {
    Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString(), structure);

    if (transformSurroundingLand) {
      Structure.field_236384_t_ = ImmutableList.<Structure<?>>builder().addAll(Structure.field_236384_t_).add(structure).build();
    }

    DimensionStructuresSettings.field_236191_b_ = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder().putAll(DimensionStructuresSettings.field_236191_b_).put(structure, structureSeparationSettings).build();
  }
}
