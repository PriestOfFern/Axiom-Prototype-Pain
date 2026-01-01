package com.axiom.axiom_pain.init

import net.adinvas.prototype_pain.ModMedicalFluids
import net.adinvas.prototype_pain.ModMedicalRegistry
import net.adinvas.prototype_pain.fluid_system.MedicalFluid
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import java.util.function.Supplier

object AxiomMedicalFluids {

    val MEDICAL_FLUIDS = DeferredRegister.create(ModMedicalRegistry.MEDICAL_FLUIDS_KEY, "axiom_pain")

    val BLOOD = ModMedicalFluids.MEDICAL_FLUIDS.register("blood",
        Supplier { MedicalFluid(AxiomMedicalEffects.BLOOD, 8192000) })
    val OIL = ModMedicalFluids.MEDICAL_FLUIDS.register("oil", Supplier { MedicalFluid(AxiomMedicalEffects.OIL, 16) })


    fun register(bus: IEventBus?) {
        ModMedicalFluids.MEDICAL_FLUIDS.register(bus)
    }
}