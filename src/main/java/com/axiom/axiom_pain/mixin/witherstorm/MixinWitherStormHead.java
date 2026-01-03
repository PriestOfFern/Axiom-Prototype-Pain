package com.axiom.axiom_pain.mixin.witherstorm;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import nonamecrackers2.witherstormmod.common.entity.WitherStormHeadEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(WitherStormHeadEntity.class)
public abstract class MixinWitherStormHead extends LivingEntity {


    protected MixinWitherStormHead(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    getBoundi
}
