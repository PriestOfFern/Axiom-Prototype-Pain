package com.axiom.axiom_pain.init

import com.axiom.axiom_pain.AxiomPain.MODID
import net.minecraft.core.particles.SimpleParticleType
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object AxiomParticleTypes {
    val REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MODID)

    fun register(bus: IEventBus) = REGISTRY.register(bus)

    val OILSPASH = REGISTRY.register("oilsplash", java.util.function.Supplier { SimpleParticleType(false) })
}