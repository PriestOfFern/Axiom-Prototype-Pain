package com.axiom.axiom_pain

import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.world.entity.player.Player
import net.minecraftforge.common.ForgeConfigSpec


object AxiomPainConfig {
    val BUILDER: ForgeConfigSpec.Builder = ForgeConfigSpec.Builder()
    val SPEC: ForgeConfigSpec

    // This will hold the actual list of strings
    val ROBOT_USERNAMES: ForgeConfigSpec.ConfigValue<MutableList<out String>>

    init {
        BUILDER.push("Robot Settings")

        ROBOT_USERNAMES = BUILDER
            .comment("A list of usernames that the mod will treat as 'Robots'")
            .defineList<String>("robotUsernames", mutableListOf<String>()) { obj: Any? -> obj is String }

        BUILDER.pop()
        SPEC = BUILDER.build()
    }

    fun isRobot(player: Player): Boolean {
        val name = player.gameProfile.name


        // Get the list from the config and check if it contains the name
        val robots: MutableList<out String?> = ROBOT_USERNAMES.get()
        return robots.contains(name)
    }

}