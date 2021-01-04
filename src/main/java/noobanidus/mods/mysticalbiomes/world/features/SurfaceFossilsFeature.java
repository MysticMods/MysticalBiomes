package noobanidus.mods.mysticalbiomes.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.*;

import java.util.Random;

public class SurfaceFossilsFeature extends Feature<NoFeatureConfig> {
  private static final ResourceLocation STRUCTURE_SPINE_01 = new ResourceLocation("fossil/spine_1");
  private static final ResourceLocation STRUCTURE_SPINE_02 = new ResourceLocation("fossil/spine_2");
  private static final ResourceLocation STRUCTURE_SPINE_03 = new ResourceLocation("fossil/spine_3");
  private static final ResourceLocation STRUCTURE_SPINE_04 = new ResourceLocation("fossil/spine_4");
  private static final ResourceLocation STRUCTURE_SKULL_01 = new ResourceLocation("fossil/skull_1");
  private static final ResourceLocation STRUCTURE_SKULL_02 = new ResourceLocation("fossil/skull_2");
  private static final ResourceLocation STRUCTURE_SKULL_03 = new ResourceLocation("fossil/skull_3");
  private static final ResourceLocation STRUCTURE_SKULL_04 = new ResourceLocation("fossil/skull_4");
  private static final ResourceLocation[] FOSSILS = new ResourceLocation[]{STRUCTURE_SPINE_01, STRUCTURE_SPINE_02, STRUCTURE_SPINE_03, STRUCTURE_SPINE_04, STRUCTURE_SKULL_01, STRUCTURE_SKULL_02, STRUCTURE_SKULL_03, STRUCTURE_SKULL_04};

  public SurfaceFossilsFeature(Codec<NoFeatureConfig> p_i231955_1_) {
    super(p_i231955_1_);
  }

  public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
    Rotation rotation = Rotation.randomRotation(rand);
    int i = rand.nextInt(FOSSILS.length);
    TemplateManager templatemanager = reader.getWorld().getServer().getTemplateManager();
    Template template = templatemanager.getTemplateDefaulted(FOSSILS[i]);
    ChunkPos chunkpos = new ChunkPos(pos);
    MutableBoundingBox mutableboundingbox = new MutableBoundingBox(chunkpos.getXStart(), 0, chunkpos.getZStart(), chunkpos.getXEnd(), 256, chunkpos.getZEnd());
    PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setBoundingBox(mutableboundingbox).setRandom(rand).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
    BlockPos blockpos = template.transformedSize(rotation);
    int j = rand.nextInt(16 - blockpos.getX());
    int k = rand.nextInt(16 - blockpos.getZ());
    int l = 256;

    for (int i1 = 0; i1 < blockpos.getX(); ++i1) {
      for (int j1 = 0; j1 < blockpos.getZ(); ++j1) {
        l = Math.min(l, reader.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, pos.getX() + i1 + j, pos.getZ() + j1 + k));
      }
    }

    BlockPos pos2 = new BlockPos(pos.getX() + j, l - 1, pos.getZ() + k);
    BlockPos blockpos1 = template.getZeroPositionWithTransform(pos2, Mirror.NONE, rotation);
    IntegrityProcessor integrityprocessor = new IntegrityProcessor(0.9F);
    placementsettings.clearProcessors().addProcessor(integrityprocessor);
    template.func_237146_a_(reader, blockpos1, blockpos1, placementsettings, rand, 4);
    return true;
  }
}
