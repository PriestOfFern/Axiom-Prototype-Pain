package com.axiom.axiom_pain.mixin.temperature;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import homeostatic.common.capabilities.Temperature;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Temperature.class)
public class TemperatureMixin {

    @WrapOperation(
            method = "checkTemperatureLevel",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;setTicksFrozen(I)V"),
            remap = false
    )
    void disableFreezing(Player instance, int i, Operation<Void> original) {}
}
