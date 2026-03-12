package com.dark2932.thirst_was_tweaked.content.config;

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
    public static final ForgeConfigSpec.ConfigValue<Integer> DIRTY_HYDROTOXIN_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Integer> SLIGHTLY_DIRTY_HYDROTOXIN_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Integer> ACCEPTABLE_HYDROTOXIN_CHANCE;

    private static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Thirst Was Tweaked");

        RETAIN_THIRST_VALUE = BUILDER
                .comment("Whether the thirst values will retain on death. (Default: true)")
                .define("retainThirstValue", true);

        RETAIN_FOOD_VALUE = BUILDER
                .comment("Whether the food values will retain on death. (Default: true)")
                .define("retainFoodValue", true);

        DIRTY_HYDROTOXIN_CHANCE = BUILDER
                .comment("The chance of giving the player the 'Hydrotoxin' effect after drinking dirty water. The value should be between 0 and 100. (Default: 30)")
                .define("dirtyHydrotoxinChance", 30);

        SLIGHTLY_DIRTY_HYDROTOXIN_CHANCE = BUILDER
                .comment("The chance of giving the player the 'Hydrotoxin' effect after drinking slightly dirty water. The value should be between 0 and 100. (Default: 18)")
                .define("slightlyDirtyHydrotoxinChance", 18);

        ACCEPTABLE_HYDROTOXIN_CHANCE = BUILDER
                .comment("The chance of giving the player the 'Hydrotoxin' effect after drinking acceptable water. The value should be between 0 and 100. (Default: 5)")
                .define("acceptableHydrotoxinChance", 5);

        SPEC = BUILDER.pop().build();
    }

    public static void init() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPEC, "thirst/thirst-was-tweaked.toml");
    }

}
