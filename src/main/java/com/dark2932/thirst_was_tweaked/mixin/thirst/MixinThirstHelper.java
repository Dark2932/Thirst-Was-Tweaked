package com.dark2932.thirst_was_tweaked.mixin.thirst;

import com.dark2932.thirst_was_tweaked.content.item.DrinkItem;
import dev.ghen.thirst.api.ThirstHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;

/**
 * @author Dark2932
 */
@Mixin(value = ThirstHelper.class, remap = false)
public class MixinThirstHelper {

    @Shadow public static Map<Item, Number[]> VALID_DRINKS;
    @Shadow public static Map<Item, Number[]> VALID_FOODS;

    @Redirect(method = "isDrink", at = @At(value = "INVOKE", target = "Ljava/util/Map;containsKey(Ljava/lang/Object;)Z"))
    private static boolean mixin$isDrink(Map<Item, Number[]> map, Object o) {
        Item item = (Item) o;
        return (VALID_DRINKS.containsKey(item) || (item instanceof DrinkItem drinkItem && drinkItem.hasManager()));
    }

    @Redirect(method = "isFood", at = @At(value = "INVOKE", target = "Ljava/util/Map;containsKey(Ljava/lang/Object;)Z"))
    private static boolean mixin$isFood(Map<Item, Number[]> map, Object o) {
        Item item = (Item) o;
        return (VALID_FOODS.containsKey(item) || (item instanceof DrinkItem drinkItem && drinkItem.hasManager() && drinkItem.isEdible()));
    }

    /**
     * @author Dark2932
     * @reason 兼容DrinkItem，并修复一定问题
     */
    @Overwrite
    public static int getThirst(ItemStack stack) {
        Item item = stack.getItem();
        if (VALID_DRINKS.containsKey(item)) {
            return (VALID_DRINKS.get(item))[0].intValue();

        } else if (VALID_FOODS.containsKey(item)) {
            return (VALID_FOODS.get(item))[0].intValue();

        } else if (item instanceof DrinkItem drinkItem && drinkItem.hasManager()) {
            return drinkItem.manager.getThirst();

        } else {
            return 0;
        }
    }

    /**
     * @author Dark2932
     * @reason 兼容DrinkItem，并修复一定问题
     */
    @Overwrite
    public static int getQuenched(ItemStack stack) {
        Item item = stack.getItem();
        if (VALID_DRINKS.containsKey(item)) {
            return (VALID_DRINKS.get(item))[1].intValue();

        } else if (VALID_FOODS.containsKey(item)) {
            return (VALID_FOODS.get(item))[1].intValue();

        } else if (item instanceof DrinkItem drinkItem && drinkItem.hasManager()) {
            return drinkItem.manager.getQuenched();

        } else {
            return 0;
        }
    }

}
