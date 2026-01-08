package com.axiom.axiom_pain.util

import com.axiom.axiom_pain.AxiomPain.LOGGER
import net.minecraft.server.level.ServerPlayer
import org.joml.Vector3d
import org.valkyrienskies.core.api.ships.LoadedShip
import org.valkyrienskies.core.api.ships.Ship
import org.valkyrienskies.mod.api.vsApi
import org.valkyrienskies.mod.common.getShipManaging
import org.valkyrienskies.mod.common.shipObjectWorld
import org.valkyrienskies.mod.common.util.IEntityDraggingInformationProvider

interface ITransformMixin {

    companion object {
        fun transformPlayer(player: ServerPlayer): Vector3d? {

            val dragInfo = (player as IEntityDraggingInformationProvider).draggingInformation
            val ship: Ship
            if (vsApi.getShipMountedTo(player) != null) ship = vsApi.getShipMountedTo(player)!!
            else if (dragInfo.isEntityBeingDraggedByAShip()) {

                val id: Long? = dragInfo.lastShipStoodOn
                if (id == null) return null
                ship = player.level().shipObjectWorld.loadedShips.getById(id) ?: return null

            } else return null



            return ship.transform.toModel.transformPosition(
                Vector3d(
                    player.x,
                    player.y,
                    player.z
                )
            )
        }
    }
}