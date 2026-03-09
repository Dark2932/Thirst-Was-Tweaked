package com.dark2932.thirst_was_tweaked.config;

import net.minecraft.network.chat.Component;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

/**
 * @author Dark2932
 */
public class ThirstTweakConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.ConfigValue<Boolean> RETAIN_THIRST_VALUE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> RETAIN_FOOD_VALUE;

    private static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Thirst Was Tweaked");

        RETAIN_THIRST_VALUE = BUILDER
                .comment(Component.translatable("config.thirst_was_tweaked.retain_thirst_value").getString())
                .define("retainThirstValue", true);
        RETAIN_FOOD_VALUE = BUILDER
                .comment(Component.translatable("config.thirst_was_tweaked.retain_food_value").getString())
                .define("retainFoodValue", true);

        SPEC = BUILDER.pop().build();
    }

    public static void init() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPEC, "thirst-was-tweaked.toml");
    }

}
