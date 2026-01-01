package com.axiom.axiom_pain.mixin.temperature;

import com.axiom.axiom_pain.AxiomTemperatureHelper;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.adinvas.prototype_pain.client.moodles.TemperatureMoodle;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import toughasnails.api.temperature.TemperatureHelper;

@Mixin(TemperatureMoodle.class)
public class TemperatureMoodleMixin {

    @ModifyExpressionValue(
            method = "calculateStatus",
            at = @At(value = "INVOKE", target = "Ljava/lang/Float;floatValue()F"),
            remap = false
    )
    float calculateStatus(float original, Player player) {
        return AxiomTemperatureHelper.INSTANCE.levelToTemp(TemperatureHelper.getTemperatureForPlayer(player));
    }
}
