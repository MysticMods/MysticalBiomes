package noobanidus.mods.mysticalbiomes.world.placement;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.Random;
import java.util.stream.Stream;

public class SeparatedPlacement extends Placement<NoPlacementConfig> {
  public SeparatedPlacement(Codec<NoPlacementConfig> codec) {
    super(codec);
  }

  @Override
  public Stream<BlockPos> getPositions(WorldDecoratingHelper helper, Random rand, NoPlacementConfig config, BlockPos pos) {
    for (int x = -1; x <= 1; x++) {
      for (int z = -1; z <= 1; z++) {
        if (helper.func_242894_a(pos.add(x, 0, z)).isSolid()) {
          return Stream.empty();
        }
      }
    }
    return Stream.of(pos);
  }
}
