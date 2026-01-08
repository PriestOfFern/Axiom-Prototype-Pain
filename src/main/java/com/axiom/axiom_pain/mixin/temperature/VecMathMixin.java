package com.axiom.axiom_pain.mixin.temperature;

import com.axiom.axiom_pain.AxiomPain;
import com.axiom.axiom_pain.util.ITransformMixin;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import homeostatic.util.VecMath;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import org.apache.logging.log4j.core.jmx.Server;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.valkyrienskies.core.api.ships.LoadedShip;
import org.valkyrienskies.mod.common.VSGameUtilsKt;
import org.valkyrienskies.mod.common.util.EntityDraggingInformation;
import org.valkyrienskies.mod.common.util.IEntityDraggingInformationProvider;

@Mixin(VecMath.class)
public class VecMathMixin {

    @WrapMethod(method = "getDistance", remap = false)
    private static double getDistance(ServerPlayer sp, Vector3d vPos, Operation<Double> original) {

        Vector3d transformed = ITransformMixin.Companion.transformPlayer(sp);
        if (transformed == null) return original.call(sp, vPos);



        double x = Math.max((double)0.0F, Math.abs(transformed.x - vPos.x) - (double)(sp.getBbWidth() / 2.0F));
        double y = Math.max((double)0.0F, Math.abs(transformed.y + (double)(sp.getBbHeight() / 2.0F) - vPos.y) - (double)(sp.getBbHeight() / 2.0F));
        double z = Math.max((double)0.0F, Math.abs(transformed.z - vPos.z) - (double)(sp.getBbWidth() / 2.0F));
        return Math.sqrt(x * x + y * y + z * z);
    }

    @WrapOperation(method = "isBlockObscured",
            at = @At(value = "NEW", target = "net/minecraft/world/phys/Vec3"),
            remap = false)
    private static Vec3 transformPos(double p_82484_, double p_82485_, double p_82486_, Operation<Vec3> original, ServerPlayer sp) {
        Vector3d transformed = ITransformMixin.Companion.transformPlayer(sp);
        if (transformed == null) return original.call(p_82484_, p_82485_, p_82486_);

        return new Vec3(transformed.x, transformed.y, transformed.z);
    }
}
