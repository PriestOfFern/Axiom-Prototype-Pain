package com.axiom.axiom_pain

import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.world.entity.player.Player
import net.minecraftforge.common.ForgeConfigSpec


object AxiomPainConfig {
    val BUILDER: ForgeConfigSpec.Builder = ForgeConfigSpec.Builder()
    val SPEC: ForgeConfigSpec

    // This will hold the actual list of strings
    val ROBOT_USERNAMES: ForgeConfigSpec.ConfigValue<MutableList<out String>>
    val TEMPERATURE_SHIFT: ForgeConfigSpec.ConfigValue<out Int>

    init {
        BUILDER.push("Robot Settings")

        ROBOT_USERNAMES = BUILDER
            .comment("A list of usernames that the mod will treat as 'Robots'")
            .defineList<String>("robotUsernames", mutableListOf<String>()) { obj: Any? -> obj is String }

        TEMPERATURE_SHIFT = BUILDER
            .comment("Long which is subtracted from the temperature. A value of 10000000 is roughly equivalent to a 0.1 float shift.")
            .define("temperatureShift", 25000000)

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