# Thirst Was Tweaked

## Features

1.  Removed the original mechanism where drinking dirty water grants hunger or poison effects.Now, players drinking dirty water will receive a brand new effect: **Hydratoxin** — which increases the player's thirst rate and intermittently causes nausea.
    
2.  Retain thirst and food levels after death (disabled by default, can be enabled via configuration).
    
3.  **AppleSkin** compatibility: the Hydratoxin effect will turn the thirst bar into purple.
    
4.  Player can now drink rainwater by crouching and right-clicking the sky with an empty hand, instead of simply looking up to hydrate as before (both the amount of water and purity from rainwater are configurable).
    
5.  Disabled some unnecessary or useless configurations from **Thirst Was Taken** to prevent them from taking effect.
    

## Developer Content

This mod fixes some logical issues in the **Thirst Was Taken** code and provides some API for developers. Here are some available features:

1.  You can now register pure drinks or food drinks through the `DrinkItem` subclass of `Item`, allowing simultaneous setting of item data and water data. A simple `Supplier<? extends Item>` example:

```
() -> new DrinkItem(
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
            .container(Items.STICK)
)
```

You can use `DrinkItemManager` to set the item's hydration amount, use duration, animation, and items returned after use. For more details, please refer to the [Wiki](https://github.com/Dark2932/Thirst-Was-Tweaked/wiki) or browse the source code.

2. Modified classes like `ThirstHelper` and `WaterPurity` via Mixin to make their code logic more aligned with practical development needs and expected results. Common methods can be called through the `ThirstAPI` class.

3. Fixed some meaningless code that could lead to bugs, such as the modification of the `getMaxStackSize` method in `MixinItemStack` (which was essentially "Burying one's head in the sand" — changing the getter because the stack size couldn't be changed :P).

## Future Plans

1.  Add a "Thirsty" effect, causing faster thirst in high-temperature biomes, with integration for **Cold Sweat**.
    
2.  **KubeJS** compatibility, making it easier for modpack authors to create drinks.
    
3.  Add compatibility and customization for water-holding containers.
    
4.  Fix more bugs from **Thirst Was Taken** and provide more API for developers.
    
5.  Gradually improve the mod's Wiki.
