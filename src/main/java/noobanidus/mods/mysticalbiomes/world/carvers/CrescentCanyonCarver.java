package noobanidus.mods.mysticalbiomes.world.carvers;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class CrescentCanyonCarver extends WorldCarver<ProbabilityConfig> {
  private final float[] heightToStretch = new float[1024];

  public CrescentCanyonCarver(Codec<ProbabilityConfig> codec) {
    super(codec, 256);
    this.replaceableBlocks = ImmutableSet.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.TERRACOTTA, Blocks.WHITE_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.YELLOW_TERRACOTTA, Blocks.LIME_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.BLACK_TERRACOTTA, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.MYCELIUM, Blocks.SNOW, Blocks.PACKED_ICE, Blocks.SMOOTH_SANDSTONE);
  }

  @Override
  public boolean isStartChunk(Random random, int chunkX, int chunkZ, ProbabilityConfig config) {
    return random.nextFloat() <= 0.15f; //config.probability;
  }

  @Override
  public boolean carve(IChunk chunk, Function<BlockPos, Biome> biomeGetter, Random random, int unknown, int chunkX, int chunkZ, int mainChunkX, int mainChunkZ, BitSet bitSet, ProbabilityConfig config) {
    int ravineLength = (this.getRange() * 2 - 1) * 8;
    double x = chunkX * 16 + random.nextInt(16);
    double y = 86; //random.nextInt(random.nextInt(40) + 8) + 20;
    double z = chunkZ * 16 + random.nextInt(16);
    float yaw = random.nextFloat() * ((float) Math.PI * 2F);
    float pitch = (random.nextFloat() - 0.5F) * 3.0F / 8.0F;
    float width = 2.5f; //Math.max(3f, random.nextFloat() * 3.5F);
    int maxLength = ravineLength - random.nextInt(ravineLength / 4);
    this.genCanyon(chunk, biomeGetter, random.nextLong(), unknown, mainChunkX, mainChunkZ, x, y, z, width, yaw, pitch, maxLength, bitSet);
    return true;
  }

  private void genCanyon(IChunk chunk, Function<BlockPos, Biome> biomeGetter, long seed, int unknown, int mainChunkX, int mainChunkZ, double x, double y, double z, float width, float yaw, float pitch, int branchCount, BitSet bitSet) {
    Random random = new Random(seed);
    float stretch = 1.0F;

    for (int y1 = 0; y1 < 256; ++y1) {
      if (y1 == 0 || random.nextInt(3) == 0) {
        stretch = 1.0F + random.nextFloat() * random.nextFloat();
      }

      this.heightToStretch[y1] = stretch * stretch;
    }

    float yawChange = 0.0F;
    float pitchChange = 0.0F;

    for (int j = 0; j < branchCount; ++j) {
      double scaledYaw = 1.5D + (double) (MathHelper.sin((float) j * (float) Math.PI / (float) branchCount) * width);
      double scaledPitch = scaledYaw * 3.0;
      scaledYaw = scaledYaw * ((double) random.nextFloat() * 0.25D + 0.75D);
      scaledPitch = scaledPitch * ((double) random.nextFloat() * 0.25D + 0.75D);
      float deltaXZ = MathHelper.cos(pitch);
      float deltaY = MathHelper.sin(pitch);
      x += MathHelper.cos(yaw) * deltaXZ;
      y += deltaY;
      y = Math.max(y, 80);
      z += MathHelper.sin(yaw) * deltaXZ;
      pitch = pitch * 0.1F;
      pitch = pitch + pitchChange * 0.05F;
      yaw += yawChange * 0.15F;
      pitchChange = pitchChange * 0.05F;
      yawChange = yawChange * 0.4f;
      pitchChange = pitchChange + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
      yawChange = yawChange + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
      if (random.nextInt(4) != 0) {
        if (!this.canReach(mainChunkX, mainChunkZ, x, z, j, branchCount, width)) {
          return;
        }

        this.carveSphere(chunk, biomeGetter, seed, unknown, mainChunkX, mainChunkZ, x, y, z, scaledYaw, scaledPitch, bitSet);
      }
    }

  }

  @Override
  protected boolean skip(double scaleX, double scaleY, double scaleZ, int y) {
    return false; //return (scaleX * scaleX + scaleZ * scaleZ) * (double) this.heightToStretch[y - 1] + scaleY * scaleY / 6.0D >= 1.0D;
  }
}
