@file:Suppress("HasPlatformType", "unused")

package com.axiom.axiom_pain.init

import com.axiom.axiom_pain.AxiomPain.MODID
import com.axiom.axiom_pain.items.BloodBagItem
import com.axiom.axiom_pain.items.OilBagItem
import net.adinvas.prototype_pain.item.ModCreativeTab
import net.minecraft.world.item.Item
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object ItemRegistry {

    // for register
    val ITEMS: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, MODID)

    fun register(bus: IEventBus) = ITEMS.register(bus)


    // ==================== //
    //     Normal Items     //
    // ==================== //

    val BLOOD_BAG = ITEMS.register("blood_bag") { BloodBagItem() }
    val OIL_BAG = ITEMS.register("oil_bag") { OilBagItem() }
}