package com.pleahmacaka.examplemod

import com.pleahmacaka.examplemod.keybind.KeyBindHandler.registerKeybindings
import net.minecraftforge.client.event.EntityRenderersEvent
import net.minecraftforge.event.TickEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.forge.MOD_BUS


@Mod("axiom_pain")
object AxiomPain {

    const val MODID = "axiom_pain"
    val LOGGER: Logger = LogManager.getLogger(MODID)

    init {
        LOGGER.log(Level.INFO, "$MODID has started!")

        MOD_BUS.addListener(::onClientSetup)
        FORGE_BUS.addListener(::onClientTick)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client... with Axiom Pain!")
        MOD_BUS.addListener(::registerKeybindings)
    }

    private fun onClientTick(event: TickEvent.ClientTickEvent) {
        BloodParticleRenderer.tick(event)
    }

}