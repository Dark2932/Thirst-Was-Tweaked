package com.dark2932.thirst_was_tweaked.mixin.thirst;

import com.dark2932.thirst_was_tweaked.content.registry.ThirstTweakEffects;
import com.dark2932.thirst_was_tweaked.util.StringUtil;
import dev.ghen.thirst.foundation.gui.ThirstBarRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * @author Dark2932
 */
@OnlyIn(Dist.CLIENT)
@Mixin(ThirstBarRenderer.class)
public class MixinThirstBarRenderer {

    private static final ResourceLocation BAR = StringUtil.locate("textures/gui/hydratoxin.png");

    @Unique
    private static boolean hasHydratoxin() {
        LocalPlayer player = Minecraft.getInstance().player;
        assert player != null;
        return player.hasEffect(ThirstTweakEffects.HYDRATOXIN.get());
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V", ordinal = 0), index = 1)
    private static ResourceLocation mixin$render$setShaderTexture(ResourceLocation id) {
        return hasHydratoxin() ? BAR : id;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V", ordinal = 0), index = 0)
    private static ResourceLocation mixin$render$blit1(ResourceLocation id) {
        return hasHydratoxin() ? BAR : id;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V", ordinal = 1), index = 0)
    private static ResourceLocation mixin$render$blit2(ResourceLocation id) {
        return hasHydratoxin() ? BAR : id;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V", ordinal = 2), index = 0)
    private static ResourceLocation mixin$render$blit3(ResourceLocation id) {
        return hasHydratoxin() ? BAR : id;
    }

}
