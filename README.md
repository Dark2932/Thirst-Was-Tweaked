# Thirst Was Tweaked

- ### [English](#english)
- ### [简体中文](#简体中文)

## English

### Features

- Removed the original mechanism where drinking dirty water grants hunger or poison effects. Now, players drinking dirty water will receive a brand new effect: **Hydratoxin** — which increases the player's thirst rate and intermittently causes nausea.

- Retain thirst and food levels after death (disabled by default, can be enabled via configuration).

- **AppleSkin** compatibility: the Hydratoxin effect will turn the thirst bar into purple.

- Player can now drink rainwater by crouching and right-clicking the sky with an empty hand, instead of simply looking up to hydrate as before (both the amount of water and purity from rainwater are configurable).

- Disabled some unnecessary or useless configurations from **Thirst Was Taken** to prevent them from taking effect.

### Developer Content

This mod fixes some logical issues in the **Thirst Was Taken** code and provides some API for developers. Here are some available features:

- You can now register pure drinks or food drinks through the `DrinkItem` subclass of `Item`, allowing simultaneous setting of item data and water data. A simple `Supplier<? extends Item>` example:

```java
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

- Modified classes like `ThirstHelper` and `WaterPurity` via Mixin to make their code logic more aligned with practical development needs and expected results. Common methods can be called through the `ThirstAPI` class.

- Fixed some meaningless code that could lead to bugs, such as the modification of the `getMaxStackSize` method in `MixinItemStack` (which was essentially "Burying one's head in the sand" — changing the getter because the stack size couldn't be changed :P).

### Future Plans

- Add a "Thirsty" effect, causing faster thirst in high-temperature biomes, with integration for **Cold Sweat**.

- **KubeJS** compatibility, making it easier for modpack authors to create drinks.

- Add compatibility and customization for water-holding containers.

- Fix more bugs from **Thirst Was Taken** and provide more API for developers.

- Gradually improve the mod's Wiki.

## 简体中文

### 功能

- 取消了原本喝脏水会给饥饿、中毒等效果的机制，现在玩家喝到脏水会被给予一种全新的效果：水毒 —— 期间会提高玩家口渴的速度，并间歇性的导致玩家反胃

- 玩家死亡后保留其口渴值和饱食度（默认关闭，可通过配置文件开启）

- 兼容AppleSkin，水毒效果会导致口渴状态条变为紫色

- 玩家现在在雨天可以通过空手潜行+右键天空来喝雨水，而不是像原来那样简单地仰个头就能补水（从雨水喝到的水量与纯净度均可配置）

- 禁用了Thirst Was Taken一些没有必要或没用的配置，使其不再生效

### 开发者内容

本模组修复了部分Thirst Was Taken代码的逻辑问题，并提供了一些API供开发者使用。下面列出一些可用的东西：

- 现在可通过`Item`的子类`DrinkItem`来注册纯饮品或食物饮品，且可同时设置物品数据与水量数据。一个简单的`Supplier<? extends Item>`如下：

```java
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

你可以通过`DrinkItemManager`来设置物品的补水量、使用时长、动画以及使用完后返回的物品等内容，具体请查阅[Wiki](https://github.com/Dark2932/Thirst-Was-Tweaked/wiki)或翻阅源代码。

- 通过Mixin修改了`ThirstHelper`、`WaterPurity`等类，使其代码逻辑更符合实际开发过程中的需求与预期结果，且可以通过`ThirstAPI`类来调用它们其中的一些常用方法。

- 修复了一些无任何实际意义、可能导致Bug的代码，例如`MixinItemStack`对`getMaxStackSize`方法的修改（这应该算是掩耳盗铃，改不了物品的堆叠数就改掉它的getter :P）。

### 未来计划

- 添加干渴药水效果，在温度高的群系渴的更快，并与冷汗联动

- 兼容KubeJS，使整合包作者更方便地创建饮品

- 添加对可盛水容器的兼容与自定义功能

- 修复更多Thirst Was Taken的Bug，提供更多的API供开发者使用

- 逐步完善本模组的Wiki