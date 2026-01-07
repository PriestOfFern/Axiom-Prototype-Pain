package com.axiom.axiom_pain.mixin.temperature;

import com.axiom.axiom_pain.AxiomPain;
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

        EntityDraggingInformation dragInfo = ((IEntityDraggingInformationProvider) sp).getDraggingInformation();
        if (!dragInfo.isEntityBeingDraggedByAShip()) return original.call(sp, vPos);

        Long id = dragInfo.getLastShipStoodOn();
        AxiomPain.INSTANCE.getLOGGER().debug(id);
        if (id == null) return original.call(sp, vPos);
        LoadedShip ship = VSGameUtilsKt.getShipObjectWorld(sp.level()).getLoadedShips().getById(id);
        Vector3d newPos = ship.getTransform().getToModel().transformPosition(new Vector3d(sp.getX(), sp.getY(), sp.getZ()));


        double x = Math.max((double)0.0F, Math.abs(newPos.x - vPos.x) - (double)(sp.getBbWidth() / 2.0F));
        double y = Math.max((double)0.0F, Math.abs(newPos.y + (double)(sp.getBbHeight() / 2.0F) - vPos.y) - (double)(sp.getBbHeight() / 2.0F));
        double z = Math.max((double)0.0F, Math.abs(newPos.z - vPos.z) - (double)(sp.getBbWidth() / 2.0F));
        return Math.sqrt(x * x + y * y + z * z);
    }

    @WrapOperation(method = "isBlockObscured",
            at = @At(value = "NEW", target = "net/minecraft/world/phys/Vec3"),
            remap = false)
    private static Vec3 transformPos(double p_82484_, double p_82485_, double p_82486_, Operation<Vec3> original, ServerPlayer sp) {
        Vec3 pos = new Vec3(sp.getX(), sp.getEyeY(), sp.getZ());

        EntityDraggingInformation dragInfo = ((IEntityDraggingInformationProvider) sp).getDraggingInformation();
        if (!dragInfo.isEntityBeingDraggedByAShip()) return pos;

        Long id = dragInfo.getLastShipStoodOn();
        AxiomPain.INSTANCE.getLOGGER().debug(id);
        if (id == null) return pos;
        LoadedShip ship = VSGameUtilsKt.getShipObjectWorld(sp.level()).getLoadedShips().getById(id);
        Vector3d newPos = ship.getTransform().getToModel().transformPosition(new Vector3d(pos.x(), pos.y(), pos.z()));
        return new Vec3(newPos.x, newPos.y, newPos.z);
    }
}
