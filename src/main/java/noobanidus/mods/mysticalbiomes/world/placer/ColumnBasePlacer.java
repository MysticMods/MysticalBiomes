package noobanidus.mods.mysticalbiomes.world.placer;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import noobanidus.libs.noobutil.types.LazyStateSupplier;
import noobanidus.libs.noobutil.world.gen.placer.AbstractColumnBasePlacer;
import noobanidus.mods.mysticalbiomes.init.ModPlacers;

import java.util.List;

public class ColumnBasePlacer extends AbstractColumnBasePlacer {
  public static Codec<ColumnBasePlacer> CODEC = codecBuilder(ColumnBasePlacer::new);

  public ColumnBasePlacer(int minSize, int extraSize, int radius1, int radius2, List<LazyStateSupplier> replace, int peak) {
    super(minSize, extraSize, radius1, radius2, replace, peak);
  }

  @Override
  protected BlockPlacerType<?> type() {
    return ModPlacers.COLUMN_BASE_PLACER;
  }
}
