package com.axiom.axiom_pain.mixin.robot;

import com.axiom.axiom_pain.AxiomPainConfig;
import com.axiom.axiom_pain.AxiomTemperatureHelper;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.adinvas.prototype_pain.config.ServerConfig;
import net.adinvas.prototype_pain.limbs.Limb;
import net.adinvas.prototype_pain.limbs.LimbStatistics;
import net.adinvas.prototype_pain.limbs.PlayerHealthData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toughasnails.api.temperature.TemperatureHelper;

import java.util.EnumMap;
import java.util.Map;

@Mixin(PlayerHealthData.class)
public abstract class PlayerHealthDataMixin {

    @Shadow
    private Map<Limb, LimbStatistics> limbStats;

    @Shadow
    private double totalPain;


    @Unique
    private boolean isRobot = false;

    @Shadow
    public abstract void setLimbSkinHeal(Limb limb, boolean value);

    @Shadow
    public abstract void setLimbMuscleHeal(Limb limb, boolean value);

    @Inject(method = "applyPenalties", at = @At("HEAD"), remap = false)
    void applyPenalties(ServerPlayer player, CallbackInfo ci) {
        if (AxiomPainConfig.INSTANCE.isRobot(player)) this.totalPain = 0f;
    }

    @WrapMethod(method = "tickUpdate", remap = false)
    void tickUpdate(ServerPlayer player, Operation<Void> original) {
        original.call(player);
        isRobot = AxiomPainConfig.INSTANCE.isRobot(player);
    }

    @WrapMethod(method = "getNORMAL_LIMB_HEAL_RATE", remap = false)
    public float getNORMAL_LIMB_HEAL_RATE(Operation<Float> original) {
        if (isRobot) return 0f;
        return original.call();
    }

    @WrapMethod(method = "getBOOSTED_LIMB_HEAL_RATE", remap = false)
    public float getBOOSTED_LIMB_HEAL_RATE(Operation<Float> original) {
        if (isRobot) return 0f;
        return original.call();
    }
}
