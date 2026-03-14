package com.dark2932.thirst_was_tweaked.content.registry;

import com.dark2932.thirst_was_tweaked.ThirstWasTweaked;
import com.dark2932.thirst_was_tweaked.content.item.DrinkItem;
import com.dark2932.thirst_was_tweaked.content.item.DrinkItemManager;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author Dark2932
 */
public class ThirstTweakItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ThirstWasTweaked.MODID);

    public static final RegistryObject<Item> TEST_DRINK = ITEMS.register("test_drink", () -> new DrinkItem(
            new Item.Properties(),
            new DrinkItemManager()
                    .thirst(4)
                    .quenched(2)
                    .container(Items.PAPER)
    ));

    public static final RegistryObject<Item> TEST_DRINK_1 = ITEMS.register("test_drink_1", () -> new DrinkItem(
            new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(1.0f)
                            .alwaysEat()
                            .build()
                    ),
            new DrinkItemManager()
                    .thirst(4)
                    .quenched(2)
                    .container(Items.PAPER)
    ));

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
    }

}
