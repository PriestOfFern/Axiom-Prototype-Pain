package com.axiom.axiom_pain.init

import net.mcreator.bloodbits.client.particle.BloodParticle
import net.mcreator.bloodbits.init.BloodbitsModParticleTypes
import net.minecraft.client.particle.ParticleEngine.SpriteParticleRegistration
import net.minecraft.client.particle.SpriteSet
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraftforge.client.event.RegisterParticleProvidersEvent

object AxiomParticles {

    fun registerParticles(event: RegisterParticleProvidersEvent) {
        event.registerSpriteSet(AxiomParticleTypes.OILSPASH.get()) { spriteSet: SpriteSet? -> BloodParticle.provider(spriteSet) }
    }
}