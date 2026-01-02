package com.axiom.axiom_pain.mixin.balance;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Shadow
    public float getSpeed() {
        return 0;
    }

    @WrapMethod(method = "getFrictionInfluencedSpeed")
    private float getFrictionInfluencedSpeed(float p_21331_, Operation<Float> original) {
        if ((Object)this instanceof Player player) {
            if (player.getAbilities().flying) {
                return original.call(p_21331_);
            }
        }

        return (this.onGround()) ? original.call(p_21331_) : this.getSpeed() * 0.07f;
    }
}
