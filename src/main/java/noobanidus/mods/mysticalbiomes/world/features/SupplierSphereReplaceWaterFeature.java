package noobanidus.mods.mysticalbiomes.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import noobanidus.libs.noobutil.world.gen.config.SupplierSphereReplaceConfig;
import noobanidus.libs.noobutil.world.gen.feature.SupplierSphereReplaceFeature;

import java.util.Random;

public class SupplierSphereReplaceWaterFeature extends SupplierSphereReplaceFeature {
  public SupplierSphereReplaceWaterFeature(Codec<SupplierSphereReplaceConfig> codec) {
    super(codec);
  }

  @Override
  public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, SupplierSphereReplaceConfig config) {
    return reader.getFluidState(pos).isTagged(FluidTags.WATER) && super.generate(reader, generator, rand, pos, config);
  }
}
