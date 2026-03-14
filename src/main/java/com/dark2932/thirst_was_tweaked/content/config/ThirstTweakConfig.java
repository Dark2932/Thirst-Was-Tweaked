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
    public static final ForgeConfigSpec.ConfigValue<Integer> DRINK_RAIN_PURITY;
    public static final ForgeConfigSpec.ConfigValue<Integer> DRINK_RAIN_HYDRATION;
    public static final ForgeConfigSpec.ConfigValue<Integer> DRINK_RAIN_QUENCHED;

    public static final ForgeConfigSpec.ConfigValue<Integer> DIRTY_HYDRATOXIN_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Integer> SLIGHTLY_DIRTY_HYDRATOXIN_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Integer> ACCEPTABLE_HYDRATOXIN_CHANCE;

    private static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Functional Tweaks");
        RETAIN_THIRST_VALUE = BUILDER
                .comment("Whether the thirst values will retain on death. (Default: false)")
                .define("retainThirstValue", false);

        RETAIN_FOOD_VALUE = BUILDER
                .comment("Whether the food values will retain on death. (Default: false)")
                .define("retainFoodValue", false);

        DRINK_RAIN_HYDRATION = BUILDER
                .comment("The thirst value will drink from rain. (Default: 1)")
                .defineInRange("drinkRainHydration", 1, 0, 20);

        DRINK_RAIN_QUENCHED = BUILDER
                .comment("The saturation value will drink from rain. (Default: 0)")
                .defineInRange("drinkRainQuenched", 0, 0, 20);

        DRINK_RAIN_PURITY = BUILDER
                .comment("The purity level of rain. (Default: 0)")
                .defineInRange("drinkRainPurity", 0, 0, 3);
        BUILDER.pop();

        BUILDER.push("Hydratoxin Effect");
        DIRTY_HYDRATOXIN_CHANCE = BUILDER
                .comment("The chance of giving the player the 'Hydratoxin' effect after drinking dirty water. The value should be between 0 and 100. (Default: 30)")
                .define("dirtyHydratoxinChance", 30);

        SLIGHTLY_DIRTY_HYDRATOXIN_CHANCE = BUILDER
                .comment("The chance of giving the player the 'Hydratoxin' effect after drinking slightly dirty water. The value should be between 0 and 100. (Default: 18)")
                .define("slightlyDirtyHydratoxinChance", 18);

        ACCEPTABLE_HYDRATOXIN_CHANCE = BUILDER
                .comment("The chance of giving the player the 'Hydratoxin' effect after drinking acceptable water. The value should be between 0 and 100. (Default: 5)")
                .define("acceptableHydratoxinChance", 5);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    public static void init() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPEC, "thirst/thirst-was-tweaked.toml");
    }

}
