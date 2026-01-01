package com.pleahmacaka.examplemod

import net.adinvas.prototype_pain.PlayerHealthProvider
import net.mcreator.bloodbits.init.BloodbitsModParticleTypes
import net.minecraft.client.Minecraft
import net.minecraftforge.event.TickEvent
import kotlin.random.Random

object BloodParticleRenderer {
    fun tick(event: TickEvent.ClientTickEvent) {
        val level = Minecraft.getInstance().level ?: return
        val players = level.players() ?: return

        for (player in players) {
            val dataCapability = player.getCapability(PlayerHealthProvider.PLAYER_HEALTH_DATA)
            if (!dataCapability.isPresent) continue

            val data = dataCapability.orElseThrow { AssertionError() }
            val bleed = data.combinedBleed

            if (bleed == 0f) continue
            val amount = (bleed / 0.0001).toInt()

            val strength = bleed / 0.005

            for (i in 1..amount) {
                level.addParticle(BloodbitsModParticleTypes.BLOODSPLASH.get(), player.x+Random.nextDouble(-0.15,0.15), player.y+1+Random.nextDouble(-0.15,0.5), player.z+Random.nextDouble(-0.15,0.15), Random.nextDouble(-0.15,0.15)+player.deltaMovement.x, Random.nextDouble(0.5*strength,1*strength)+player.deltaMovement.y, Random.nextDouble(-0.15,0.15)+player.deltaMovement.z)
            }

        }
    }
}