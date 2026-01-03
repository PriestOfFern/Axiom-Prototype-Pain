package com.axiom.axiom_pain.mixin.temperature;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(toughasnails.temperature.TemperatureHandler.class)
public class TemperatureHandlerMixin {

    @WrapOperation(
            method = "onPlayerTick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z")
            )
    private static boolean hurt(Player instance, DamageSource p_36154_, float p_36155_, Operation<Boolean> original) {
        return false;
    }

    @WrapOperation(
            method = "onPlayerTick",
            at = @At(value = "INVOKE", target = "Ltoughasnails/temperature/TemperatureHandler;tryAddHeatExhaustion(Lnet/minecraft/world/entity/player/Player;)V")
            , remap = false)
    private static void tryAddHeatExhaustion(Player attributeinstance, Operation<Void> original) {

    }

    @WrapOperation(
            method = "onPlayerTick",
            at = @At(value = "INVOKE", target = "Ltoughasnails/temperature/TemperatureHandler;removeHeatExhaustion(Lnet/minecraft/world/entity/player/Player;)V")
            , remap = false)
    private static void removeHeatExhaustion(Player player, Operation<Void> original) {

    }
}

