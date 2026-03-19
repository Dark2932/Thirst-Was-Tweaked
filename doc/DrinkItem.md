# English

`DrinkItem` is a subclass of `Item` that allows developers to register pure drinks or items with food properties. Combined with `DrinkItemManager`, it provides flexibility in configuring various attributes for drinks (purity level is not yet supported).

## Contents

- [Code Examples](#code-examples)
- [DrinkItemManager](#drinkitemmanager)
- [Appendix](#appendix)

## Code Examples

You can create a drink by extending `DrinkItem` or by instantiating it directly. Below is a simple `Supplier<? extends Item>` example demonstrating how to register a drink with container return, thirst restoration, and quenched restoration:

```java
() -> new DrinkItem(
    new Item.Properties()
        .food(new FoodProperties.Builder()
            .nutrition(2) // Restores 2 hunger points
            .saturationMod(1.0f) // Saturation modifier
            .alwaysEat() // Can be consumed even when hunger bar is full
            .build()
        ),
    new DrinkItemManager()
        .thirst(4) // Restores 4 thirst units
        .quenched(2) // Restores 2 quenched units
        .container(Items.STICK) // Returns a stick after consumption
        .burp() // Causes the player to burp after drinking
)
```

A complete example of creating a `RegistryObject<Item>` via direct instantiation:

```java
public static final RegistryObject<Item> COLA = ITEMS.register("cola", () -> new DrinkItem(
        new Item.Properties().stacksTo(16),
        new DrinkItemManager()
                .thirst(6)
                .quenched(3)
    			.burp()
    			.effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 30 * 20, 0, false, false, false), 0.5f)
));
```

## DrinkItemManager

`DrinkItemManager` utilizes a chainable calling pattern similar to Vanilla, with the primary methods listed below:

### Restoration Values

```java
public DrinkItemManager thirst(int thirst) // Sets the thirst restoration value
public DrinkItemManager quenched(int quenched) // Sets the quenched (hydration) restoration value
```

### Usage Experience

```java
public DrinkItemManager useDuration(int tick) // Sets the duration required to drink (default is 32 ticks, matching Vanilla)
public DrinkItemManager foodAnim() // Sets to play the eating animation (default is the drinking animation)
public DrinkItemManager burp() // Causes the player to burp after drinking
```

### Return Container

```java
public DrinkItemManager container(Item container) // Sets the item returned to the player after drinking (e.g., empty bottles or cans)
```

The parameter here is an `Item`, not an `ItemStack`. If you only have an `ItemStack`, you can retrieve the item using the `ItemStack#getItem()` method.

### Give Player Effects

```java
public DrinkItemManager effect(MobEffectInstance effectIn, float probability) // Sets the mob effect granted to the player after drinking
```

If the item also has food properties with effects defined in its `FoodProperties`, both will be applied simultaneously.

- `effectIn`: A `MobEffectInstance` instance
- `probability`: Trigger probability (0.0f - 1.0f)

## Appendix

[DrinkItem.java](https://github.com/Dark2932/Thirst-Was-Tweaked/blob/main/src/main/java/com/dark2932/thirst_was_tweaked/content/item/DrinkItem.java)

[DrinkItemManager.java](https://github.com/Dark2932/Thirst-Was-Tweaked/blob/main/src/main/java/com/dark2932/thirst_was_tweaked/content/item/DrinkItemManager.java)

---

# 简体中文

`DrinkItem` 是Item的子类，允许开发者注册纯饮品或兼具食物属性的饮品。配合 `DrinkItemManager`，可以灵活配置饮品的各项属性（纯净度暂不支持）。

## 目录

- [代码示例](#代码示例)
- [DrinkItemManager](#drinkitemmanager-1)
- [附录](#附录)

## 代码示例

你可以通过继承 `DrinkItem` 或直接实例化它来创建饮品。以下是一个简单的 `Supplier<? extends Item>` 示例，展示了如何注册一个带容器返还、口渴恢复及饱水度恢复的饮品：

```java
() -> new DrinkItem(
    new Item.Properties()
        .food(new FoodProperties.Builder()
            .nutrition(2) // 恢复 2 点饱食度
            .saturationMod(1.0f) // 饱和度
            .alwaysEat() // 饱食度满时也能食用
            .build()
        ),
    new DrinkItemManager()
        .thirst(4) // 恢复 4 点口渴值
        .quenched(2) // 恢复 2 点饱水度
        .container(Items.STICK) // 喝完后返还木棍
        .burp() // 喝完后打嗝
)
```

一个完整的通过直接实例化创建`RegistryObject<Item>`的例子：

```java
public static final RegistryObject<Item> COLA = ITEMS.register("cola", () -> new DrinkItem(
        new Item.Properties().stacksTo(16),
        new DrinkItemManager()
                .thirst(6)
                .quenched(3)
    			.brup()
    			.effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 30 * 20, 0, false, false, false), 0.5f)
));
```

## DrinkItemManager

`DrinkItemManager` 采用了与原版相似的链式调用模式，主要方法如下：

### 设置恢复数值

```java
public DrinkItemManager thirst(int thirst) // 设置可恢复的口渴值
public DrinkItemManager quenched(int quenched) // 设置可恢复的饱水度
```

### 设置使用体验

```java
public DrinkItemManager useDuration(int tick) // 设置饮用所需的时长（默认32tick，即原版时长）
public DrinkItemManager foodAnim() // 设置为播放吃东西的动画（默认播放喝东西动画）
public DrinkItemManager burp() // 设置喝完后让玩家打嗝
```

### 设置返还容器

```java
public DrinkItemManager container(Item container) // 设置喝完后返还给玩家的物品（如空瓶、空罐之类的玩意）
```

这里传入的参数并非 `ItemStack`，而是 `Item`。如果手头上只有 `ItemStack`，可以用 `ItemStack#getItem()` 方法获取。

### 设置药水效果

```java
public DrinkItemManager effect(MobEffectInstance effectIn, float probability) // 设置喝完后赋予玩家的药水效果
```

如果该饮品具有食物属性且其 `FoodProperties` 里也设置了药水效果，二者可同时生效。

- `effectIn`: 一个 `MobEffectInstance` 实例
- `probability`: 触发概率（0.0f - 1.0f）

## 附录

[DrinkItem.java](https://github.com/Dark2932/Thirst-Was-Tweaked/blob/main/src/main/java/com/dark2932/thirst_was_tweaked/content/item/DrinkItem.java)

[DrinkItemManager.java](https://github.com/Dark2932/Thirst-Was-Tweaked/blob/main/src/main/java/com/dark2932/thirst_was_tweaked/content/item/DrinkItemManager.java)
