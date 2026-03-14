package com.dark2932.thirst_was_tweaked.mixin.thirst;

import com.dark2932.thirst_was_tweaked.content.registry.ThirstTweakEffects;
import com.dark2932.thirst_was_tweaked.mixin.thirst.accessor.HUDOverlayHandlerAccessor;
import com.dark2932.thirst_was_tweaked.util.StringUtil;
import dev.ghen.thirst.api.ThirstHelper;
import dev.ghen.thirst.foundation.common.capability.IThirst;
import dev.ghen.thirst.foundation.config.CommonConfig;
import dev.ghen.thirst.foundation.gui.appleskin.HUDOverlayHandler;
import dev.ghen.thirst.foundation.gui.appleskin.ThirstValues;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

/**
 * @author Dark2932
 */
@OnlyIn(Dist.CLIENT)
@Mixin(value = HUDOverlayHandler.class, remap = false, priority = 1001)
public abstract class MixinHUDOverlayHandler {

    private static final ResourceLocation BAR = StringUtil.locate("textures/gui/hydratoxin.png");
    private static final ResourceLocation SATURATION = StringUtil.locate("textures/gui/appleskin_icons.png");

    @Unique
    private static boolean hasHydratoxin() {
        LocalPlayer player = Minecraft.getInstance().player;
        assert player != null;
        return player.hasEffect(ThirstTweakEffects.HYDRATOXIN.get());
    }

    //Render purple bar when the player has hydratoxin
    @ModifyArg(method = "drawHungerOverlay", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V", ordinal = 0), index = 1, remap = true)
    private static ResourceLocation mixin$drawHungerOverlay1(ResourceLocation id) {
        return hasHydratoxin() ? BAR : id;
    }

    //Render purple bar when the player has hydratoxin
    @ModifyArg(method = "drawHungerOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V"), index = 0, remap = true)
    private static ResourceLocation mixin$drawHungerOverlay2(ResourceLocation id) {
        return hasHydratoxin() ? BAR : id;
    }

    //Render purple bar when the player has hydratoxin
    @ModifyArg(method = "drawSaturationOverlay", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V", ordinal = 0), index = 1, remap = true)
    private static ResourceLocation mixin$drawSaturationOverlay1(ResourceLocation id) {
        return hasHydratoxin() ? SATURATION : id;
    }

    //Render purple bar when the player has hydratoxin
    @ModifyArg(method = "drawSaturationOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"), index = 0, remap = true)
    private static ResourceLocation mixin$drawSaturationOverlay2(ResourceLocation id) {
        return hasHydratoxin() ? SATURATION : id;
    }

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
