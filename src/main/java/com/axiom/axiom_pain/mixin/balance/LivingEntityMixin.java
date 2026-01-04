package com.axiom.axiom_pain.mixin.balance;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Shadow
    private float speed;

    @Inject(method = "getFrictionInfluencedSpeed", at = @At("TAIL"), cancellable = true)
    private void getFrictionInfluencedSpeed(float p_21331_, CallbackInfoReturnable<Float> cir) {
        float original = cir.getReturnValue();
        if ((Object)this instanceof Player player) {
            if (player.getAbilities().flying) {
                return;
            }
        }

        if (this.onGround()) return;

        cir.setReturnValue(this.speed * 0.09f);
    }
}
