package com.axiom.axiom_pain

import toughasnails.api.temperature.TemperatureLevel

object AxiomTemperatureHelper {

    fun levelToTemp(temperatureLevel: TemperatureLevel): Float {
        return when (temperatureLevel) {
            TemperatureLevel.ICY -> 25f
            TemperatureLevel.COLD -> 30f
            TemperatureLevel.NEUTRAL -> 37f
            TemperatureLevel.WARM ->  40f
            TemperatureLevel.HOT -> 45f
        }
    }
}