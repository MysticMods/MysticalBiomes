package noobanidus.mods.mysticalbiomes.mixin;

import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.MixRiverLayer;
import noobanidus.mods.mysticalbiomes.biome.BiomeVariants;
import noobanidus.mods.mysticalbiomes.world.DynamicBiomes;
import noobanidus.mods.mysticalbiomes.world.Reference;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MixRiverLayer.class)
public class MixinMixRiverLayer {
  @Inject(at = @At("HEAD"), method = "applyPixel(Lnet/minecraft/world/gen/INoiseRandom;Lnet/minecraft/world/gen/area/IArea;Lnet/minecraft/world/gen/area/IArea;II)I", cancellable = true)
  private void apply(INoiseRandom random, IArea area1, IArea area2, int x, int z, CallbackInfoReturnable<Integer> info) {
    int landId = area1.get(x, z);
    RegistryKey<Biome> key = DynamicBiomes.byId(landId);

    int riverId = area2.get(x, z);
    RegistryKey<Biome> river = DynamicBiomes.byId(riverId);
    if (river == Biomes.RIVER) {
      RegistryKey<Biome> riverReplacement = BiomeVariants.pickReplacement(random, key, BiomeVariants.VariantType.RIVER);
      if (riverReplacement != null) {
        info.setReturnValue(Reference.getBiomeID(riverReplacement));
      }
    }
  }
}
