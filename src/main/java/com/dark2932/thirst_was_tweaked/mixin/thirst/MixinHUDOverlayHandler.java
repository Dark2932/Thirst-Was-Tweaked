package com.dark2932.thirst_was_tweaked.mixin.thirst;

import com.dark2932.thirst_was_tweaked.mixin.thirst.accessor.HUDOverlayHandlerAccessor;
import dev.ghen.thirst.api.ThirstHelper;
import dev.ghen.thirst.foundation.common.capability.IThirst;
import dev.ghen.thirst.foundation.config.CommonConfig;
import dev.ghen.thirst.foundation.gui.appleskin.HUDOverlayHandler;
import dev.ghen.thirst.foundation.gui.appleskin.ThirstValues;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

/**
 * @author Dark2932
 */
@OnlyIn(Dist.CLIENT)
@Mixin(value = HUDOverlayHandler.class, remap = false, priority = 1001)
public abstract class MixinHUDOverlayHandler {

    //Correctly render the hydration value
    @Redirect(method = "renderThirstOverlay", at = @At(value = "INVOKE", target = "Ldev/ghen/thirst/foundation/gui/appleskin/HUDOverlayHandler;drawHungerOverlay(IILnet/minecraft/client/gui/GuiGraphics;IIF)V"))
    private static void mixin$renderThirstOverlay1(int thirstRestored, int playerThirst, GuiGraphics guiGraphics, int right, int top, float flashAlpha) {
        LocalPlayer player = Minecraft.getInstance().player;
        assert player != null;
        ItemStack stack = player.getMainHandItem();
        if (stack.isEmpty()) stack = player.getOffhandItem();
        if (ThirstHelper.isDrink(stack) || (ThirstHelper.isFood(stack) && player.canEat(stack.getFoodProperties(player).canAlwaysEat()))) {
            HUDOverlayHandler.drawHungerOverlay(thirstRestored, playerThirst, guiGraphics, right, top, flashAlpha);
        }
    }

    //Correctly render the saturation value
    @Inject(method = "renderThirstOverlay", at = @At(value = "INVOKE", target = "Ldev/ghen/thirst/api/ThirstHelper;isFood(Lnet/minecraft/world/item/ItemStack;)Z"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private static void mixin$renderThirstOverlay2(GuiGraphics guiGraphics, CallbackInfo ci, Minecraft mc, Player player, IThirst thirstData, int top, int right, ItemStack stack, boolean shouldRenderHeldItemValues, ThirstValues thirstValues, int drinkThirst, float thirstQuenchedIncrement) {
        int playerThirst = thirstData.getThirst();
        int playerQuenched = thirstData.getQuenched();
        if ((ThirstHelper.isDrink(stack) || (ThirstHelper.isFood(stack) && player.canEat(stack.getFoodProperties(player).canAlwaysEat()))) && playerQuenched < 20) {
            int extra_quenched = Math.max(playerThirst + thirstValues.thirst - 20, 0);
            if (!(Boolean) CommonConfig.EXTRA_HYDRATION_CONVERT_TO_QUENCHED.get()) extra_quenched = 0;
            HUDOverlayHandler.drawSaturationOverlay(Math.min(thirstValues.quenchedModifier + extra_quenched, playerThirst - playerQuenched + thirstValues.thirst), (float) playerQuenched, guiGraphics, right, top, HUDOverlayHandlerAccessor.getFlashAlpha());
            ci.cancel();
        }
    }

}
