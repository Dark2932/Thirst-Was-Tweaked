package com.dark2932.thirst_was_tweaked.mixin;

import com.dark2932.thirst_was_tweaked.api.item.DrinkItem;
import com.dark2932.thirst_was_tweaked.api.item.DrinkItemManager;
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
@Mixin(value = PlayerThirst.class)
public abstract class MixinPlayerThirst {

    @Shadow public abstract int getThirst();
    @Shadow public abstract int getQuenched();
    @Shadow public abstract void setThirst(int value);
    @Shadow public abstract void setQuenched(int value);

    @Inject(method = "drink(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;)V", at = @At(value = "TAIL"), remap = false)
    private static void mixin$drink(ItemStack stack, Player player, CallbackInfo ci) {
        if (!ThirstHelper.itemRestoresThirst(stack) && stack.getItem() instanceof DrinkItem item) {
            DrinkItemManager manager = item.manager;
            player.getCapability(ModCapabilities.PLAYER_THIRST).ifPresent(cap -> {
                cap.drink(player, manager.getThirst(), manager.getQuenched());
            });
        }
    }

    @Inject(method = "tick", at = @At(value = "HEAD"), remap = false)
    private void mixin$tick1(Player player, CallbackInfo ci) {
        if (getThirst() < 0) setThirst(0);
        if (getQuenched() < 0) setQuenched(0);
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;isRainingAt(Lnet/minecraft/core/BlockPos;)Z"))
    private boolean mixin$tick2(Level level, BlockPos pos) {
        return false;
    }

}
