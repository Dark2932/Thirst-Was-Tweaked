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

    // DEPLETES_WHEN_NAUSEA
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;comment(Ljava/lang/String;)Lnet/minecraftforge/common/ForgeConfigSpec$Builder;", ordinal = 4))
    private static ForgeConfigSpec.Builder mixin$DEPLETES_WHEN_NAUSEA$comment(ForgeConfigSpec.Builder builder, String comment) {
        return comment(builder);
    }
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;define(Ljava/lang/String;Z)Lnet/minecraftforge/common/ForgeConfigSpec$BooleanValue;", ordinal = 1))
    private static ForgeConfigSpec.BooleanValue mixin$DEPLETES_WHEN_NAUSEA$define(ForgeConfigSpec.Builder builder, String path, boolean defaultValue) {
        return builder.define(str(path), false);
    }

    // DIRTY_POISON_PERCENTAGE
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;comment(Ljava/lang/String;)Lnet/minecraftforge/common/ForgeConfigSpec$Builder;", ordinal = 21))
    private static ForgeConfigSpec.Builder mixin$DIRTY_POISON_PERCENTAGE$comment(ForgeConfigSpec.Builder builder, String comment) {
        return comment(builder);
    }
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;define(Ljava/lang/String;Ljava/lang/Object;)Lnet/minecraftforge/common/ForgeConfigSpec$ConfigValue;", ordinal = 8))
    private static <T> ForgeConfigSpec.ConfigValue<?> mixin$DIRTY_POISON_PERCENTAGE$define(ForgeConfigSpec.Builder builder, String path, T defaultValue) {
        return builder.define(str(path), 0);
    }

    // DIRTY_NAUSEA_PERCENTAGE
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;comment(Ljava/lang/String;)Lnet/minecraftforge/common/ForgeConfigSpec$Builder;", ordinal = 22))
    private static ForgeConfigSpec.Builder mixin$DIRTY_NAUSEA_PERCENTAGE$comment(ForgeConfigSpec.Builder builder, String comment) {
        return comment(builder);
    }
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;define(Ljava/lang/String;Ljava/lang/Object;)Lnet/minecraftforge/common/ForgeConfigSpec$ConfigValue;", ordinal = 9))
    private static <T> ForgeConfigSpec.ConfigValue<?> mixin$DIRTY_NAUSEA_PERCENTAGE$define(ForgeConfigSpec.Builder builder, String path, T defaultValue) {
        return builder.define(str(path), 0);
    }

    // SLIGHTLY_DIRTY_POISON_PERCENTAGE
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;comment(Ljava/lang/String;)Lnet/minecraftforge/common/ForgeConfigSpec$Builder;", ordinal = 23))
    private static ForgeConfigSpec.Builder mixin$SLIGHTLY_DIRTY_POISON_PERCENTAGE$comment(ForgeConfigSpec.Builder builder, String comment) {
        return comment(builder);
    }
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;define(Ljava/lang/String;Ljava/lang/Object;)Lnet/minecraftforge/common/ForgeConfigSpec$ConfigValue;", ordinal = 10))
    private static <T> ForgeConfigSpec.ConfigValue<?> mixin$SLIGHTLY_DIRTY_POISON_PERCENTAGE$define(ForgeConfigSpec.Builder builder, String path, T defaultValue) {
        return builder.define(str(path), 0);
    }

    // SLIGHTLY_DIRTY_NAUSEA_PERCENTAGE
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;comment(Ljava/lang/String;)Lnet/minecraftforge/common/ForgeConfigSpec$Builder;", ordinal = 24))
    private static ForgeConfigSpec.Builder mixin$SLIGHTLY_DIRTY_NAUSEA_PERCENTAGE$comment(ForgeConfigSpec.Builder builder, String comment) {
        return comment(builder);
    }
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;define(Ljava/lang/String;Ljava/lang/Object;)Lnet/minecraftforge/common/ForgeConfigSpec$ConfigValue;", ordinal = 11))
    private static <T> ForgeConfigSpec.ConfigValue<?> mixin$SLIGHTLY_DIRTY_NAUSEA_PERCENTAGE$define(ForgeConfigSpec.Builder builder, String path, T defaultValue) {
        return builder.define(str(path), 0);
    }

    // ACCEPTABLE_POISON_PERCENTAGE
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;comment(Ljava/lang/String;)Lnet/minecraftforge/common/ForgeConfigSpec$Builder;", ordinal = 25))
    private static ForgeConfigSpec.Builder mixin$ACCEPTABLE_POISON_PERCENTAGE$comment(ForgeConfigSpec.Builder builder, String comment) {
        return comment(builder);
    }
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;define(Ljava/lang/String;Ljava/lang/Object;)Lnet/minecraftforge/common/ForgeConfigSpec$ConfigValue;", ordinal = 12))
    private static <T> ForgeConfigSpec.ConfigValue<?> mixin$ACCEPTABLE_POISON_PERCENTAGE$define(ForgeConfigSpec.Builder builder, String path, T defaultValue) {
        return builder.define(str(path), 0);
    }

    // ACCEPTABLE_NAUSEA_PERCENTAGE
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;comment(Ljava/lang/String;)Lnet/minecraftforge/common/ForgeConfigSpec$Builder;", ordinal = 26))
    private static ForgeConfigSpec.Builder mixin$ACCEPTABLE_NAUSEA_PERCENTAGE$comment(ForgeConfigSpec.Builder builder, String comment) {
        return comment(builder);
    }
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;define(Ljava/lang/String;Ljava/lang/Object;)Lnet/minecraftforge/common/ForgeConfigSpec$ConfigValue;", ordinal = 13))
    private static <T> ForgeConfigSpec.ConfigValue<?> mixin$ACCEPTABLE_NAUSEA_PERCENTAGE$define(ForgeConfigSpec.Builder builder, String path, T defaultValue) {
        return builder.define(str(path), 0);
    }

    // PURIFIED_POISON_PERCENTAGE
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;comment(Ljava/lang/String;)Lnet/minecraftforge/common/ForgeConfigSpec$Builder;", ordinal = 27))
    private static ForgeConfigSpec.Builder mixin$PURIFIED_POISON_PERCENTAGE$comment(ForgeConfigSpec.Builder builder, String comment) {
        return comment(builder);
    }
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;define(Ljava/lang/String;Ljava/lang/Object;)Lnet/minecraftforge/common/ForgeConfigSpec$ConfigValue;", ordinal = 14))
    private static <T> ForgeConfigSpec.ConfigValue<?> mixin$PURIFIED_POISON_PERCENTAGE$define(ForgeConfigSpec.Builder builder, String path, T defaultValue) {
        return builder.define(str(path), 0);
    }

    // PURIFIED_NAUSEA_PERCENTAGE
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;comment(Ljava/lang/String;)Lnet/minecraftforge/common/ForgeConfigSpec$Builder;", ordinal = 28))
    private static ForgeConfigSpec.Builder mixin$PURIFIED_NAUSEA_PERCENTAGE$comment(ForgeConfigSpec.Builder builder, String comment) {
        return comment(builder);
    }
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;define(Ljava/lang/String;Ljava/lang/Object;)Lnet/minecraftforge/common/ForgeConfigSpec$ConfigValue;", ordinal = 15))
    private static <T> ForgeConfigSpec.ConfigValue<?> mixin$PURIFIED_NAUSEA_PERCENTAGE$define(ForgeConfigSpec.Builder builder, String path, T defaultValue) {
        return builder.define(str(path) , 0);
    }

    private static ForgeConfigSpec.Builder comment(ForgeConfigSpec.Builder builder) {
        return builder.comment(" ↓↓↓↓↓↓ This option is banned by [Thirst Was Tweaked] mod, it won't do anything.");
    }

    private static String str(String path) {
        return "Banned<" + path + ">";
    }

    //CAN_DRINK_BY_HAND
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;comment(Ljava/lang/String;)Lnet/minecraftforge/common/ForgeConfigSpec$Builder;", ordinal = 6))
    private static ForgeConfigSpec.Builder mixin$CAN_DRINK_BY_HAND$comment(ForgeConfigSpec.Builder builder, String comment) {
        return builder
                .comment("Whether players can drink by shift-right-clicking water with an empty hand. Attention: it needs to be enable on both server and client sides")
                .comment("You can tweak the value drunk in 'thirst_was_tweaked.toml'");
    }

    //HAND_DRINKING_HYDRATION
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;define(Ljava/lang/String;Ljava/lang/Object;)Lnet/minecraftforge/common/ForgeConfigSpec$ConfigValue;", ordinal = 2))
    private static <T> ForgeConfigSpec.ConfigValue<?> mixin$HAND_DRINKING_HYDRATION$define(ForgeConfigSpec.Builder builder, String path, T defaultValue) {
        return builder.defineInRange("handDrinkingHydration", 2, 0, 20);
    }

    //HAND_DRINKING_QUENCHED
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;define(Ljava/lang/String;Ljava/lang/Object;)Lnet/minecraftforge/common/ForgeConfigSpec$ConfigValue;", ordinal = 3))
    private static <T> ForgeConfigSpec.ConfigValue<?> mixin$HAND_DRINKING_$define(ForgeConfigSpec.Builder builder, String path, T defaultValue) {
        return builder.defineInRange("handDrinkingQuenched", 0, 0, 20);
    }

}
