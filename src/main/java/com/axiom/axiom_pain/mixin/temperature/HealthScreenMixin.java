package com.axiom.axiom_pain.mixin.temperature;

import com.axiom.axiom_pain.AxiomPainConfig;
import com.axiom.axiom_pain.AxiomTemperatureHelper;
import net.adinvas.prototype_pain.client.gui.HealthInfoBoxWidget;
import net.adinvas.prototype_pain.client.gui.HealthScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.temperature.TemperatureHelperImpl;

@Mixin(HealthScreen.class)
public class HealthScreenMixin {
    @Shadow(remap = false)
    private HealthInfoBoxWidget healthbox;

    @Inject(method = "tick", at = @At("RETURN"), remap = false)
    public void tick(CallbackInfo ci) {
        Player viewer = Minecraft.getInstance().player;
        float temperature = AxiomTemperatureHelper.INSTANCE.levelToTemp(TemperatureHelper.getTemperatureForPlayer(viewer));
        healthbox.setTemp(temperature);
    }
}
