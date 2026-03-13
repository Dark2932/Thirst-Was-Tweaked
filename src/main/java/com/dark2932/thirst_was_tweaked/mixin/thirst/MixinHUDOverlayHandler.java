package com.dark2932.thirst_was_tweaked.mixin.thirst;

import com.dark2932.thirst_was_tweaked.mixin.thirst.accessor.HUDOverlayHandlerAccessor;
import dev.ghen.thirst.api.ThirstHelper;
import dev.ghen.thirst.foundation.common.capability.IThirst;
import dev.ghen.thirst.foundation.gui.appleskin.HUDOverlayHandler;
import dev.ghen.thirst.foundation.gui.appleskin.ThirstValues;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

/**
 * @author Dark2932
 */
@OnlyIn(Dist.CLIENT)
@Mixin(value = HUDOverlayHandler.class, remap = false, priority = 1001)
public class MixinHUDOverlayHandler {

    @Inject(method = "renderThirstOverlay", at = @At(value = "INVOKE", target = "Ldev/ghen/thirst/api/ThirstHelper;isFood(Lnet/minecraft/world/item/ItemStack;)Z"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private static void mixin$renderThirstOverlay(GuiGraphics guiGraphics, CallbackInfo ci, Minecraft mc, Player player, IThirst thirstData, int top, int right, ItemStack stack, boolean shouldRenderHeldItemValues, ThirstValues thirstValues, int drinkThirst, float thirstQuenchedIncrement) {
        if (ThirstHelper.isDrink(stack) || (ThirstHelper.isFood(stack) && player.canEat(stack.getFoodProperties(player).canAlwaysEat()))) {
            HUDOverlayHandler.drawSaturationOverlay(thirstValues.quenchedModifier, (float)thirstData.getQuenched(), guiGraphics, right, top, HUDOverlayHandlerAccessor.getFlashAlpha());
            ci.cancel();
        }
    }

}
