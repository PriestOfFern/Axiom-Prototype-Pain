@file:Suppress("HasPlatformType", "unused")

package com.axiom.axiom_pain.init

import com.axiom.axiom_pain.AxiomPain.MODID
import net.minecraft.world.level.block.Block
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object BlockRegistry {

    private val BLOCKS: DeferredRegister<Block> = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID)

    fun register(bus: IEventBus) = BLOCKS.register(bus)


}