package noobanidus.mods.mysticalbiomes.world;

import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;
import noobanidus.libs.noobutil.types.AbstractSupplierBockStateProvider;
import noobanidus.mods.mysticalbiomes.init.ModTypes;

public class SupplierBlockStateProvider extends AbstractSupplierBockStateProvider {
  public static final Codec<SupplierBlockStateProvider> CODEC = AbstractSupplierBockStateProvider.codecBuilder(SupplierBlockStateProvider::new);

  public SupplierBlockStateProvider(String namespace, String path) {
    super(namespace, path);
  }

  public SupplierBlockStateProvider(ResourceLocation key) {
    super(key);
  }

  @Override
  protected BlockStateProviderType<?> getProviderType() {
    return ModTypes.SUPPLIER_BLOCK_STATE_PROVIDER;
  }
}
