package com.dark2932.thirst_was_tweaked.mixin.thirst;

import com.dark2932.thirst_was_tweaked.content.item.DrinkItem;
import com.dark2932.thirst_was_tweaked.content.item.DrinkItemManager;
import com.dark2932.thirst_was_tweaked.content.registry.ThirstTweakEffects;
import dev.ghen.thirst.api.ThirstHelper;
import dev.ghen.thirst.content.thirst.PlayerThirst;
import dev.ghen.thirst.foundation.common.capability.ModCapabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Dark2932
 */
@Mixin(value = PlayerThirst.class, remap = false)
public abstract class MixinPlayerThirst {

    @Shadow public abstract int getThirst();
    @Shadow public abstract int getQuenched();
    @Shadow public abstract void setThirst(int value);
    @Shadow public abstract void setQuenched(int value);

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void mixin$tick1(Player player, CallbackInfo ci) {
        if (getThirst() < 0) setThirst(0);
        if (getQuenched() < 0) setQuenched(0);
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;isRainingAt(Lnet/minecraft/core/BlockPos;)Z"), remap = true)
    private boolean mixin$tick2(Level level, BlockPos pos) {
        return false;
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Ldev/ghen/thirst/content/thirst/PlayerThirst;addExhaustion(Lnet/minecraft/world/entity/player/Player;F)V"))
    private void mixin$tick3(PlayerThirst instance, Player player, float amount) {}

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isPassenger()Z", shift = At.Shift.AFTER))
    private void mixin$tick4(Player player, CallbackInfo ci) {
        player.getCapability(ModCapabilities.PLAYER_THIRST).ifPresent(cap -> {
            if (player.hasEffect(ThirstTweakEffects.HYDROTOXIN.get())) {
                int amplifier = player.getEffect(ThirstTweakEffects.HYDROTOXIN.get()).getAmplifier();
                cap.addExhaustion(player, 0.03f + (amplifier * 0.005f));
            }
        });
    }

}
