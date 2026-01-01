package com.axiom.axiom_pain.mixin.temperature;

import com.axiom.axiom_pain.AxiomTemperatureHelper;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.adinvas.prototype_pain.limbs.PlayerHealthData;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toughasnails.api.temperature.TemperatureHelper;

@Mixin(PlayerHealthData.class)
public class PlayerHealthDataMixin {

    @Shadow
    private float temperature;

    @Inject(method = "applyPenalties", at = @At("HEAD"), remap = false)
    void tickUpdate(ServerPlayer player, CallbackInfo ci) {
        this.temperature = AxiomTemperatureHelper.INSTANCE.levelToTemp(TemperatureHelper.getTemperatureForPlayer(player));
    }
}
