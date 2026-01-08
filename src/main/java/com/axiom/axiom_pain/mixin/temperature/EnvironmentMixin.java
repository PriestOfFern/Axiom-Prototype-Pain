package com.axiom.axiom_pain.mixin.temperature;

import com.axiom.axiom_pain.AxiomPain;
import com.axiom.axiom_pain.util.ITransformMixin;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import homeostatic.common.temperature.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.valkyrienskies.core.api.ships.LoadedShip;
import org.valkyrienskies.mod.common.VSGameUtilsKt;
import org.valkyrienskies.mod.common.util.EntityDraggingInformation;
import org.valkyrienskies.mod.common.util.IEntityDraggingInformationProvider;


@Mixin(Environment.class)
public class EnvironmentMixin implements ITransformMixin {

    @WrapOperation(
            method = "get",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;blockPosition()Lnet/minecraft/core/BlockPos;"),
            remap = true
    )
    private static BlockPos getBlockPos(ServerPlayer instance, Operation<BlockPos> original) {
        Vector3d transformed = ITransformMixin.Companion.transformPlayer(instance);
        if (transformed == null) return original.call(instance);

        return new BlockPos((int) transformed.x, (int) transformed.y, (int) transformed.z);
    }

    @WrapOperation(
            method = "get",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;getEyePosition(F)Lnet/minecraft/world/phys/Vec3;")
    )
    private static Vec3 getEyePos(ServerPlayer instance, float v, Operation<Vec3> original) {
        Vector3d transformed = ITransformMixin.Companion.transformPlayer(instance);
        if (transformed == null) return original.call(instance, v);

        return new Vec3(transformed.x, transformed.y, transformed.z);
    }
}
