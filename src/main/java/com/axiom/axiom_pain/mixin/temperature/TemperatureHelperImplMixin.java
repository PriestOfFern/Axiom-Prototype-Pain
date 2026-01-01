package com.axiom.axiom_pain.mixin.temperature;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import toughasnails.api.temperature.ITemperature;
import toughasnails.api.temperature.TemperatureHelper;

@Mixin(TemperatureHelper.class)
public class TemperatureHelperImplMixin {

    @WrapMethod(method = "getTemperatureData", remap = false)
    private static ITemperature getTemperatureData(Player player, Operation<ITemperature> original) {
        ITemperature temperature = original.call(player);
        temperature.setExtremityDelayTicks(1);

        return temperature;
    }
}
