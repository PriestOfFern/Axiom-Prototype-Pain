package com.axiom.axiom_pain.mixin.temperature;

import com.axiom.axiom_pain.AxiomPainConfig;
import com.axiom.axiom_pain.AxiomTemperatureHelper;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.adinvas.prototype_pain.limbs.Limb;
import net.adinvas.prototype_pain.limbs.PlayerHealthData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toughasnails.api.temperature.TemperatureHelper;

@Mixin(PlayerHealthData.class)
public class PlayerHealthDataMixin {

    @Shadow
    private float temperature;

    @Shadow
    private double totalPain;

    @Inject(method = "applyPenalties", at = @At("HEAD"), remap = false)
    void tickUpdate(ServerPlayer player, CallbackInfo ci) {
        this.temperature = AxiomTemperatureHelper.INSTANCE.levelToTemp(TemperatureHelper.getTemperatureForPlayer(player));
    }

    @WrapOperation(
            method = "handleFireDamage",
            at = @At(value = "INVOKE", target = "Lnet/adinvas/prototype_pain/limbs/PlayerHealthData;applyBleedDamage(Lnet/adinvas/prototype_pain/limbs/Limb;FLnet/minecraft/world/entity/player/Player;)V")
            , remap = false)
    void applyBleedDamageFire(PlayerHealthData instance, Limb limb, float damage, Player player, Operation<Void> original) {
        System.out.println(damage);
    }
}
