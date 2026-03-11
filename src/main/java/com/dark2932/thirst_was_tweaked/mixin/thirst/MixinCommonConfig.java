package com.dark2932.thirst_was_tweaked.mixin.thirst;

import dev.ghen.thirst.foundation.config.CommonConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Dark2932
 */
@Mixin(value = CommonConfig.class, remap = false)
public class MixinCommonConfig {

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;comment(Ljava/lang/String;)Lnet/minecraftforge/common/ForgeConfigSpec$Builder;", ordinal = 4))
    private static ForgeConfigSpec.Builder mixin$DEPLETES_WHEN_NAUSEA$comment(ForgeConfigSpec.Builder builder, String comment) {
        return builder.comment("↓↓↓ This option is banned by [Thirst Was Tweaked] mod. It doesn't do anything. ↓↓↓");
    }
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;define(Ljava/lang/String;Z)Lnet/minecraftforge/common/ForgeConfigSpec$BooleanValue;", ordinal = 1))
    private static ForgeConfigSpec.BooleanValue mixin$DEPLETES_WHEN_NAUSEA$define(ForgeConfigSpec.Builder builder, String path, boolean defaultValue) {
        return builder.define("null", false);
    }



}
