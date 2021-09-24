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
  public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, SupplierSphereReplaceConfig config) {
    return reader.getFluidState(pos).is(FluidTags.WATER) && super.place(reader, generator, rand, pos, config);
  }
}
