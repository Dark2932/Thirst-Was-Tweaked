package com.dark2932.thirst_was_tweaked.mixin.thirst;

import com.dark2932.thirst_was_tweaked.content.item.DrinkItem;
import dev.ghen.thirst.api.ThirstHelper;
import dev.ghen.thirst.foundation.config.ItemSettingsConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Dark2932
 */
@Mixin(value = ThirstHelper.class, remap = false)
public class MixinThirstHelper {

    @Shadow public static Map<Item, Number[]> VALID_DRINKS;
    @Shadow public static Map<Item, Number[]> VALID_FOODS;

    /**
     * @author Dark2932
     * @reason 兼容DrinkItem，并修复黑名单无法获取的Bug
     */
    @Overwrite
    public static boolean isDrink(ItemStack stack) {
        Item item = stack.getItem();
        String id = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString();
        return (
            !(ItemSettingsConfig.ITEMS_BLACKLIST.get()).contains(id) &&
            (VALID_DRINKS.containsKey(item) || (item instanceof DrinkItem drinkItem && drinkItem.hasManager()))
        );
    }

    /**
     * @author Dark2932
     * @reason 兼容DrinkItem，并修复黑名单无法获取的Bug
     */
    @Overwrite
    public static boolean isFood(ItemStack stack) {
        Item item = stack.getItem();
        String id = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString();
        return (
            !(ItemSettingsConfig.ITEMS_BLACKLIST.get()).contains(id) &&
            (VALID_FOODS.containsKey(item) || (item instanceof DrinkItem drinkItem && drinkItem.hasManager() && drinkItem.isEdible()))
        );
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
