package com.axiom.axiom_pain.mixin.balance;

import com.axiom.axiom_pain.AxiomPainConfig;
import net.minecraft.world.level.biome.Climate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Climate.Sampler.class)
public class ClimateSamplerMixin {
    @Inject(method = "sample", at = @At("RETURN"), cancellable = true)
    private void decreaseGenTemperature(int x, int y, int z, CallbackInfoReturnable<Climate.TargetPoint> cir) {
        Climate.TargetPoint original = cir.getReturnValue();

        // TargetPoint values are 'long' (quantized).
        // Subtracting shifts the climate towards colder biomes.
        // A value of 10000000 is roughly equivalent to a 0.1 float shift.
        long colderTemp = original.temperature() - AxiomPainConfig.INSTANCE.getTEMPERATURE_SHIFT().get();

        // Return a new TargetPoint with the modified temperature
        cir.setReturnValue(new Climate.TargetPoint(
                colderTemp,
                original.humidity(),
                original.continentalness(),
                original.erosion(),
                original.depth(),
                original.weirdness()
        ));
    }
}