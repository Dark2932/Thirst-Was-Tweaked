package com.dark2932.thirst_was_tweaked;

import dev.ghen.thirst.api.ThirstHelper;
import dev.ghen.thirst.content.purity.WaterPurity;
import dev.ghen.thirst.content.thirst.PlayerThirst;
import dev.ghen.thirst.foundation.common.capability.ModCapabilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * @author Dark2932
 */
public class ThirstAPI {

    /**
     * Drink sth or specific value
     */
    public static void drink(Player player, ItemStack stack) {
        PlayerThirst.drink(stack, player);
    }

    public static void drink(Player player, int thirst, int quenched) {
        drink(player, thirst, quenched, 3);
    }

    public static void drink(Player player, int thirst, int quenched, int purity) {
        player.getCapability(ModCapabilities.PLAYER_THIRST).ifPresent(cap -> {
            if (WaterPurity.givePurityEffects(player, purity)) {
                cap.drink(player, thirst, quenched);
            }
        });
    }

    /**
     * Drink bypass the 'quenchThirstWhenDebuffed' option in 'common.toml'
     * Whether the player has Hydratoxin or not, they must be taken the corresponding thirst value.
     */
    public static void drinkBypassEffect(Player player, int thirst, int quenched, int purity) {
        player.getCapability(ModCapabilities.PLAYER_THIRST).ifPresent(cap -> {
            cap.drink(player, thirst, quenched);
            WaterPurity.givePurityEffects(player, purity);
        });
    }

    /**
     * add exhaustion to player, this will display by AppleSkin mod
     */
    public static void addExhaustion(Player player, float value) {
        player.getCapability(ModCapabilities.PLAYER_THIRST).ifPresent(cap -> {
            cap.addExhaustion(player, value);
        });
    }

    /**
     * Check whether the item is drink without food properties
     */
    public static boolean isDrink(ItemStack stack) {
        return ThirstHelper.isDrink(stack);
    }

    /**
     * Check whether the item is drink with food properties
     */
    public static boolean isDrinkableFood(ItemStack stack) {
        return ThirstHelper.isFood(stack);
    }

    /**
     * Check whether the item could drink
     */
    public static boolean canDrink(ItemStack stack) {
        return stack.getUseDuration() > 0 && ThirstHelper.itemRestoresThirst(stack);
    }

    /**
     * Check whether the item has purity
     */
    public static boolean hasPurity(ItemStack stack) {
        return WaterPurity.hasPurity(stack);
    }

    /**
     * Get the thirst value of the item
     */
    public static int getThirst(ItemStack stack) {
        return ThirstHelper.getThirst(stack);
    }

    /**
     * Get the quenched value of the item
     */
    public static int getQuenched(ItemStack stack) {
        return ThirstHelper.getQuenched(stack);
    }

    /**
     * Get the purity level of the item
     * If the item does not have purity, it will return the default purity set in 'thirst/common.toml'.
     */
    public static int getPurity(ItemStack stack) {
        return ThirstHelper.getPurity(stack);
    }

}
