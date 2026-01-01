package com.axiom.axiom_pain.mixin.robot;

import com.axiom.axiom_pain.AxiomPainConfig;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalDoubleRef;
import net.adinvas.prototype_pain.limbs.Limb;
import net.adinvas.prototype_pain.limbs.LimbStatistics;
import net.adinvas.prototype_pain.limbs.PlayerHealthData;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(PlayerHealthData.class)
public abstract class PlayerHealthDataMixin {

    @Shadow(remap = false)
    private Map<Limb, LimbStatistics> limbStats;

    @Shadow(remap = false)
    private double totalPain;
    @Shadow(remap = false)
    private float Opioids;
    @Shadow(remap = false)
    private float adrenaline;
    @Shadow(remap = false)
    private float drug_addition;
    @Shadow(remap = false)
    private float dirtyness;

    @Unique
    private boolean isRobot = false;

    @Shadow(remap = false)
    public abstract void setLimbSkinHeal(Limb limb, boolean value);

    @Shadow(remap = false)
    public abstract void setLimbMuscleHeal(Limb limb, boolean value);

    @Inject(method = "applyPenalties", at = @At("HEAD"), remap = false)
    void applyPenalties(ServerPlayer player, CallbackInfo ci) {
        if (isRobot)  {
            this.totalPain = 0f;
            this.Opioids = 0f;
            this.adrenaline = 0f;
            this.drug_addition = 0f;
        }
    }

    @Inject(method = "applyPenalties", at = @At(value = "INVOKE", target = "Lnet/adinvas/prototype_pain/limbs/Limb;getFromHand(Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/entity/HumanoidArm;"), remap = false)
    void applyGunk(ServerPlayer player, CallbackInfo ci, @Local(name = "moveReduction") LocalDoubleRef moveReduction) {
        if (!isRobot) return;
        System.out.println("GUNK!!!");

        if (dirtyness > 80) moveReduction.set(moveReduction.get() + 0.2);
        else if (dirtyness > 60) moveReduction.set(moveReduction.get() + 0.1);
        else if (dirtyness > 40) moveReduction.set(moveReduction.get() + 0.05);
        else if (dirtyness > 20) moveReduction.set(moveReduction.get() + 0.025);
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

    @WrapMethod(method = "setLimbDesinfected", remap = false)
    public void setLimbDesinfected(Limb limb, float desinfection, Operation<Void> original) {
        if (desinfection > 0) dirtyness = 0f;
        if (!isRobot) original.call(limb, desinfection);
    }
}
