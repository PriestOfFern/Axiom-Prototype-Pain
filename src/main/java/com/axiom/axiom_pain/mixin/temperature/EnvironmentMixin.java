package com.axiom.axiom_pain.mixin.temperature;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import homeostatic.common.temperature.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Environment.class)
public class EnvironmentMixin {

    @WrapOperation(
            method = "get",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;blockPosition()Lnet/minecraft/core/BlockPos;"),
            remap = false
    )
    private static BlockPos getBlockPos(ServerPlayer instance, Operation<BlockPos> original) {
        BlockPos pos = original.call(instance);
        if (V)
    }
}
