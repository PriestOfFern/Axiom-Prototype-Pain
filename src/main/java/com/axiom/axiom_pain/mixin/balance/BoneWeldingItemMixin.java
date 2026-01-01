package com.axiom.axiom_pain.mixin.balance;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.adinvas.prototype_pain.PlayerHealthProvider;
import net.adinvas.prototype_pain.item.INbtDrivenDurability;
import net.adinvas.prototype_pain.item.usable.BoneWeldingItem;
import net.adinvas.prototype_pain.limbs.Limb;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BoneWeldingItem.class)
public abstract class BoneWeldingItemMixin implements INbtDrivenDurability {

    @WrapMethod(method = "onMedicalUse", remap = false)
    ItemStack setLimbFracture(Limb limb, ServerPlayer source, ServerPlayer target, ItemStack stack, Operation<ItemStack> original) {
        target.getCapability(PlayerHealthProvider.PLAYER_HEALTH_DATA).ifPresent((h) -> {
            h.setLimbSkinHealth(limb, h.getLimbSkinHealth(limb) - 25.0F);
            h.setLimbMuscleHealth(limb, h.getLimbMuscleHealth(limb) - 26.0F);
            h.setLimbFracture(limb, h.getLimbFracture(limb) * 0.15F);
            h.setLimbBleedRate(limb, h.getLimbBleedRate(limb) + 4E-4F);
            h.setLimbPain(limb, h.getLimbPain(limb) + 30.0F);
            h.setBloodViscosity(h.getBloodViscosity() + 2.0F);
        });
        ItemStack newitemstack = stack;
        this.setNbtDurability(stack, this.getNbtDurability(stack) - 50.0F);
        if (this.getNbtDurability(stack) <= 0.0F) {
            newitemstack = ItemStack.EMPTY;
        }

        return newitemstack;
    }
}
