package com.axiom.axiom_pain.mixin.robot;

import net.adinvas.prototype_pain.client.gui.HealthScreen;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(HealthScreen.class)
public interface HealthScreenAccessor {

    @Accessor("target")
    Player getPlayer();
}
