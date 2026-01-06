package com.axiom.axiom_pain

import com.axiom.axiom_pain.AxiomPain.MODID
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.client.event.RenderLevelStageEvent
import net.minecraftforge.event.entity.living.LivingHurtEvent
import net.minecraftforge.eventbus.api.EventPriority
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod


@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = [Dist.DEDICATED_SERVER])
object AxiomEvents {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun cancelFreezing(event: LivingHurtEvent) {
        if (event.isCancelable) event.isCanceled = true
    }
}