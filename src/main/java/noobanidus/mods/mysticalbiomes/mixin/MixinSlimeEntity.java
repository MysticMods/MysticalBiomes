package noobanidus.mods.mysticalbiomes.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import noobanidus.mods.mysticalbiomes.world.SlimeSpawnBiomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(SlimeEntity.class)
public class MixinSlimeEntity {
  @Inject(method = "checkSlimeSpawnRules(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/IWorld;Lnet/minecraft/entity/SpawnReason;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)Z", at = @At(value = "HEAD"), cancellable = true)
  private static void canSpawnInjection(EntityType<SlimeEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random random, CallbackInfoReturnable<Boolean> info) {
    if (world.getDifficulty() == Difficulty.PEACEFUL) {
      return; // Delegate back to vanilla
    }

    RegistryKey<Biome> biomeKey = world.getBiomeName(pos).orElse(null);

    // Handle spawning for biomes registered as slime-spawnable
    if (SlimeSpawnBiomes.getSlimeSpawnBiomes().contains(biomeKey) && pos.getY() > 50 && pos.getY() < 70 && random.nextFloat() < 0.5F && random.nextFloat() < world.getMoonBrightness() && world.getMaxLocalRawBrightness(pos) <= random.nextInt(8)) {
      info.setReturnValue(SlimeEntity.checkMobSpawnRules(type, world, reason, pos, random));
    }
  }
}
