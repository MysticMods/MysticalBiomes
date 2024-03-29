package noobanidus.mods.mysticalbiomes.world.surface;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraftforge.registries.ForgeRegistries;

public class ResourceSurfaceBuilderConfig implements ISurfaceBuilderConfig {
  public static final Codec<ResourceSurfaceBuilderConfig> CODEC = RecordCodecBuilder.create((codec) -> codec.group(ResourceLocation.CODEC.fieldOf("top_material").forGetter((p) -> p.topMaterial), ResourceLocation.CODEC.fieldOf("under_material").forGetter((p) -> p.underMaterial), ResourceLocation.CODEC.fieldOf("underwater_material").forGetter((p) -> p.underWaterMaterial)).apply(codec, ResourceSurfaceBuilderConfig::new));
  private final ResourceLocation topMaterial;
  private final ResourceLocation underMaterial;
  private final ResourceLocation underWaterMaterial;
  private BlockState topMaterialState = null;
  private BlockState underMaterialState = null;
  private BlockState underWaterMaterialState = null;

  public ResourceSurfaceBuilderConfig(ResourceLocation topMaterial, ResourceLocation underMaterial, ResourceLocation underWaterMaterial) {
    this.topMaterial = topMaterial;
    this.underMaterial = underMaterial;
    this.underWaterMaterial = underWaterMaterial;
    resolve();
  }

  private void resolve() {
    Block block = ForgeRegistries.BLOCKS.getValue(topMaterial);
    if (block == null) {
      throw new IllegalArgumentException("Invalid block specified for top material: " + topMaterial);
    }
    topMaterialState = block.defaultBlockState();
    block = ForgeRegistries.BLOCKS.getValue(underMaterial);
    if (block == null) {
      throw new IllegalArgumentException("Invalid block specified for under material: " + underMaterial);
    }
    underMaterialState = block.defaultBlockState();
    block = ForgeRegistries.BLOCKS.getValue(underWaterMaterial);
    if (block == null) {
      throw new IllegalArgumentException("Invalid block specified for underwater material: " + underWaterMaterial);
    }
    underWaterMaterialState = block.defaultBlockState();
  }

  public BlockState getTopMaterial() {
    return this.topMaterialState;
  }

  public BlockState getUnderMaterial() {
    return this.underMaterialState;
  }

  public BlockState getUnderWaterMaterial() {
    return this.underWaterMaterialState;
  }
}