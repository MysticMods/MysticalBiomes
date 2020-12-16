package noobanidus.mods.mysticalbiomes.structures;

import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;

public class MushroomHutStructure extends Structure<NoFeatureConfig> {
  public MushroomHutStructure(Codec<NoFeatureConfig> codec) {
    super(codec);
  }

  @Override
  public IStartFactory<NoFeatureConfig> getStartFactory() {
    return MushroomHutStructure.Start::new;
  }

  @Override
  public GenerationStage.Decoration getDecorationStage() {
    return GenerationStage.Decoration.SURFACE_STRUCTURES;
  }

  public static class Start extends StructureStart<NoFeatureConfig> {
    public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
      super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
    }

    @Override
    public void func_230364_a_(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {

      int x = (chunkX << 4) + 7;
      int z = (chunkZ << 4) + 7;

      BlockPos blockpos = new BlockPos(x, 0, z);

      JigsawManager.func_242837_a(
          dynamicRegistryManager,
          new VillageConfig(() -> dynamicRegistryManager.getRegistry(Registry.JIGSAW_POOL_KEY)
              .getOrDefault(new ResourceLocation(MysticalBiomes.MODID, "house_pool/start_pool")),
              50),
          AbstractVillagePiece::new,
          chunkGenerator,
          templateManagerIn,
          blockpos,
          this.components,
          this.rand,
          true,
          true);

      this.recalculateStructureSize();
    }
  }
}