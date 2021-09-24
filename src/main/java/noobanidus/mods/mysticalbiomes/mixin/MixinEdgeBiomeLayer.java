package noobanidus.mods.mysticalbiomes.mixin;

import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.EdgeBiomeLayer;
import noobanidus.mods.mysticalbiomes.biome.BiomeVariants;
import noobanidus.mods.mysticalbiomes.world.Reference;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EdgeBiomeLayer.class)
public class MixinEdgeBiomeLayer {
  @Inject(at = @At("HEAD"), method = "apply(Lnet/minecraft/world/gen/INoiseRandom;IIIII)I", cancellable = true)
  private void apply(INoiseRandom context, int north, int west, int south, int east, int center, CallbackInfoReturnable<Integer> info) {
    RegistryKey<Biome> key = BiomeRegistry.byId(center);

    RegistryKey<Biome> centerKey = BiomeVariants.pickReplacement(context, key, BiomeVariants.VariantType.CENTER);
    if (centerKey != null && mbSurrounded(north, west, south, east, center)) {
      info.setReturnValue(Reference.getBiomeID(centerKey));
    }
  }

  private static boolean mbSurrounded(int neighbor1, int neighbor2, int neighbor3, int neighbor4, int biome) {
    return neighbor1 == biome && neighbor2 == biome && neighbor3 == biome && neighbor4 == biome;
  }
}
