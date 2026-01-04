package com.axiom.axiom_pain.mixin.robot;

import com.axiom.axiom_pain.AxiomPainConfig;
import com.axiom.axiom_pain.init.AxiomParticleTypes;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.mcreator.bloodbits.configuration.BloodBitsConfigConfiguration;
import net.mcreator.bloodbits.init.BloodbitsModParticleTypes;
import net.mcreator.bloodbits.procedures.ParticleTriggerProcedure;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.eventbus.api.Event;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ParticleTriggerProcedure.class)
public class BloodParticleTriggerProcedureMixin {

    @WrapMethod(method = "execute(Lnet/minecraftforge/eventbus/api/Event;Lnet/minecraft/world/level/LevelAccessor;DDDLnet/minecraft/world/entity/Entity;D)V", remap = false)
    private static void execute(Event event, LevelAccessor world, double x, double y, double z, Entity entity, double amount, Operation<Void> original) {
        if (entity instanceof Player player && AxiomPainConfig.INSTANCE.isRobot(player)) {
            if (world instanceof ServerLevel _level) {
                _level.sendParticles( AxiomParticleTypes.INSTANCE.getOILSPASH().get(), x, y + (double)(entity.getBbHeight() / 2.0F), z, (int)((Double) BloodBitsConfigConfiguration.AMOUNT.get() * amount), (double)(entity.getBbWidth() / 2.0F), (double)(entity.getBbHeight() / 2.0F), (double)(entity.getBbWidth() / 2.0F), 0.2);
            }
        } else original.call(event, world, x, y, z, entity, amount);
    }
}
