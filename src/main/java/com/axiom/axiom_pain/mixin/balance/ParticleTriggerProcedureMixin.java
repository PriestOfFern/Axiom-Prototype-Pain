package com.axiom.axiom_pain.mixin.balance;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.mcreator.bloodbits.procedures.ParticleTriggerProcedure;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ParticleTriggerProcedure.class)
public class ParticleTriggerProcedureMixin {

    @WrapMethod(method = "onEntityAttacked", remap = false)
    private static void onEntityAttacked(LivingHurtEvent event, Operation<Void> original) {
        if (event.getSource().is(DamageTypes.IN_WALL) || event.getSource().is(DamageTypes.FREEZE)
                || event.getSource().is(DamageTypes.STARVE) || event.getSource().is(DamageTypes.CRAMMING)) return;
        original.call(event);
    }
}
