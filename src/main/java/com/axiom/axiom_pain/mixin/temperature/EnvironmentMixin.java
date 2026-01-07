package com.axiom.axiom_pain.mixin.temperature;

import com.axiom.axiom_pain.AxiomPain;
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
public class EnvironmentMixin {

    @WrapOperation(
            method = "get",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;blockPosition()Lnet/minecraft/core/BlockPos;"),
            remap = false
    )
    private static BlockPos getBlockPos(ServerPlayer instance, Operation<BlockPos> original) {
        BlockPos pos = original.call(instance);

        EntityDraggingInformation dragInfo = ((IEntityDraggingInformationProvider) instance).getDraggingInformation();

        if (!dragInfo.isEntityBeingDraggedByAShip()) return pos;
        Long id = dragInfo.getLastShipStoodOn();
        AxiomPain.INSTANCE.getLOGGER().debug(id);
        if (id == null) return pos;
        LoadedShip ship = VSGameUtilsKt.getShipObjectWorld(instance.level()).getLoadedShips().getById(id);
        Vector3d newPos = ship.getTransform().getToModel().transformPosition(new Vector3d(pos.getX(), pos.getY(), pos.getZ()));
        AxiomPain.INSTANCE.getLOGGER().debug(newPos);
        return new BlockPos((int) newPos.x, (int) newPos.y, (int) newPos.z);
    }

    @WrapOperation(
            method = "get",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;getEyePosition(F)Lnet/minecraft/world/phys/Vec3;"),
            remap = false
    )
    private static Vec3 getEyePos(ServerPlayer instance, float v, Operation<Vec3> original) {
        Vec3 pos = original.call(instance, v);

        EntityDraggingInformation dragInfo = ((IEntityDraggingInformationProvider) instance).getDraggingInformation();

        if (!dragInfo.isEntityBeingDraggedByAShip()) return pos;
        Long id = dragInfo.getLastShipStoodOn();
        AxiomPain.INSTANCE.getLOGGER().debug(id);
        if (id == null) return pos;
        LoadedShip ship = VSGameUtilsKt.getShipObjectWorld(instance.level()).getLoadedShips().getById(id);
        Vector3d newPos = ship.getTransform().getToModel().transformPosition(new Vector3d(pos.x, pos.y, pos.z));
        AxiomPain.INSTANCE.getLOGGER().debug(newPos);
        return new Vec3((int) newPos.x, (int) newPos.y, (int) newPos.z);
    }
}
