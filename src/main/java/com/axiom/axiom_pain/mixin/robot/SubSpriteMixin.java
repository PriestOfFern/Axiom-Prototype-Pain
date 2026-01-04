package com.axiom.axiom_pain.mixin.robot;

//This is the worst thing i have ever written

import com.axiom.axiom_pain.AxiomPain;
import com.axiom.axiom_pain.AxiomPainConfig;
import com.axiom.axiom_pain.mixin.temperature.HealthScreenMixin;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.adinvas.prototype_pain.client.gui.HealthScreen;
import net.adinvas.prototype_pain.client.gui.StatusSprites;
import net.adinvas.prototype_pain.client.gui.SubSprite;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SubSprite.class)
public class SubSpriteMixin {


    @Final
    @Shadow
    private ResourceLocation txt;



    @WrapOperation(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V"),
            remap = false
    )
    public void blit(GuiGraphics instance, ResourceLocation p_283272_, int p_283605_, int p_281879_, float p_282809_, float p_282942_, int p_281922_, int p_282385_, int p_282596_, int p_281699_, Operation<Void> original) {
        AxiomPain.INSTANCE.getLOGGER().debug(this.txt + " " + StatusSprites.BLEED.getResourceLocation());

        if (!this.txt.equals(StatusSprites.BLEED.getResourceLocation())) original.call(instance, p_283272_, p_283605_, p_281879_, p_282809_, p_282942_, p_281922_, p_282385_, p_282596_, p_281699_);
        else {
            Screen currentScreen = Minecraft.getInstance().screen;
            if (currentScreen instanceof HealthScreen healthScreen) {
                Player target = ((HealthScreenAccessor) healthScreen).getPlayer();
                if (AxiomPainConfig.INSTANCE.isRobot(target)) {
                    original.call(instance, ResourceLocation.fromNamespaceAndPath("axiom_pain",  "textures/gui/icons/oil.png"), p_283605_, p_281879_, p_282809_, p_282942_, p_281922_, p_282385_, p_282596_, p_281699_);
                } else original.call(instance, p_283272_, p_283605_, p_281879_, p_282809_, p_282942_, p_281922_, p_282385_, p_282596_, p_281699_);
            } else original.call(instance, p_283272_, p_283605_, p_281879_, p_282809_, p_282942_, p_281922_, p_282385_, p_282596_, p_281699_);
        }
    }
}
