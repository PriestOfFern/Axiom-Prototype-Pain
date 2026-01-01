@file:Suppress("unused", "HasPlatformType")

package com.axiom.axiom_pain.init


import com.axiom.axiom_pain.AxiomPain.MODID
import net.minecraft.world.item.Item
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object BlockItemRegistry {

    private val BLOCK_ITEMS: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, MODID)

    fun register(bus: IEventBus) = BLOCK_ITEMS.register(bus)

    // ==================== //
    //      Block Items     //
    // ==================== //



}