# English

`ThirstAPI` is the core API class provided by the **Thirst Was Tweaked** mod. This class encapsulates methods related to modifying <u>**Hydration**</u> or **<u>Thirst</u>**, **Quenched** and **<u>Purity</u>**, allowing developers to make centralized calls.

## Contents

- [Drinking Operations](#drinking-operations)
- [Status Adjustment](#status-adjustment)
- [Item Setting Checks](#item-setting-checks)
- [Value Getters](#value-getters)
- [Appendix](#appendix)

## Drinking Operations

### Drink ItemStack

Makes the player drink a specific item stack.

**Note:** This method does not consume the item by default. You can call the `ItemStack#shrink` method to handle item consumption.

```java
public static void drink(Player player, ItemStack stack)
```

### Add Specific Values

Increases the player's thirst and quenched values. The default purity is 3 (Purified).

```java
public static void drink(Player player, int thirst, int quenched)
```

### Add Specific Values with Purity

Increases the player's thirst and quenched values, and determines the probability of applying negative effects based on the purity level.

```java
public static void drink(Player player, int thirst, int quenched, int purity)
```

### Force Add Values (Bypass Config)

Regardless of the `quenchThirstWhenDebuffed` option in `common.toml` or whether the player has "Hydratoxin", this method forces the addition of thirst values and will not cancel drinking due to negative effects.

```java
public static void drinkBypassEffect(Player player, int thirst, int quenched, int purity)
```

## Status Adjustment

### Add Exhaustion

Adds thirst exhaustion to the player. When this value reaches its peak, it causes the thirst level to decrease.

You can install the **AppleSkin** mod to visualize this value.

```java
public static void addExhaustion(Player player, float value)
```

## Item Setting Checks

### Is Pure Drink

Checks if the item is defined as a drink and **does not** have food properties (e.g. Water Bottle).

```java
public static boolean isDrink(ItemStack stack)
```

### Is Drinkable Food

Checks if the item is defined as a drink with food properties (e.g. Apple).

```java
public static boolean isDrinkableFood(ItemStack stack)
```

### Can Drink

Checks if the item can restore thirst and has a use duration.

```java
public static boolean canDrink(ItemStack stack)
```

### Has Purity

Checks if the item has a defined water purity level.

```java
public static boolean hasPurity(ItemStack stack)
```

## Value Getters

### Get Thirst Restoration Value

Retrieves the thirst value that the item stack can restore.

```java
public static int getThirst(ItemStack stack)
```

### Get Quenched Restoration Value

Retrieves the quenched (saturation) value that the item stack can restore.

```java
public static int getQuenched(ItemStack stack)
```

### Get Purity Level

Retrieves the purity level of the item stack. If undefined, it returns the default purity from `common.toml`.

```java
public static int getPurity(ItemStack stack)
```

## Appendix

[ThirstAPI.java](https://github.com/Dark2932/Thirst-Was-Tweaked/blob/main/src/main/java/com/dark2932/thirst_was_tweaked/ThirstAPI.java)

---

# 简体中文

`ThirstAPI` 是 **Thirst Was Tweaked** 模组提供的API类，此类对修改口渴值（Hydration | Thirst）、饱水度（Quenched）以及纯净度（Purity）的相关方法进行了封装，以便开发者进行集中调用。

## 目录

- [饮用操作](#饮用操作)
- [状态调整](#状态调整)
- [物品属性检查](#物品属性检查)
- [数值获取](#数值获取)
- [附录](#附录)

## 饮用操作

### 饮用ItemStack

使玩家饮用指定的物品堆。

**注：** 此方法默认不会消耗物品，你可以调用`ItemStack$shrink`方法来实现物品消耗。

```java
public static void drink(Player player, ItemStack stack)
```

### 增加指定数值

为玩家增加指定的口渴值和饱水度，默认纯净度为 3（纯净）。

```java
public static void drink(Player player, int thirst, int quenched)
```

### 增加指定数值与纯净度

为玩家增加指定的口渴值、饱水度，并根据纯净度决定给予玩家负面效果的概率。

```java
public static void drink(Player player, int thirst, int quenched, int purity)
```

### 强制增加数值 (绕过配置)

无论 `common.toml` 中的 `quenchThirstWhenDebuffed` 选项如何设置，或者玩家是否患有“水毒”，都会强制增加相应的口渴数值，不会因负面效果而取消饮用。

```java
public static void drinkBypassEffect(Player player, int thirst, int quenched, int purity)
```

## 状态调整

### 增加消耗值

为玩家增加口渴消耗值（Exhaustion），该值达到峰值时会导致口渴度下降。

你可以装载 AppleSkin 模组来显示这个数值。

```java
public static void addExhaustion(Player player, float value)
```

## 物品属性检查

### 是否为纯饮品

检查物品是否定义为饮品且**不具有**食物属性（如：水瓶）。

```java
public static boolean isDrink(ItemStack stack)
```

### 是否为可解渴的食物

检查物品是否定义为具有食物属性的饮品（如：苹果）。

```java
public static boolean isDrinkableFood(ItemStack stack)
```

### 是否可以饮用

检查物品是否能够恢复口渴值且具有使用时长。

```java
public static boolean canDrink(ItemStack stack)
```

### 是否具有纯净度

检查该物品是否定义了水质纯净度。

```java
public static boolean hasPurity(ItemStack stack)
```

## 数值获取

### 获取口渴恢复值

获取物品堆能够恢复的口渴值。

```java
public static int getThirst(ItemStack stack)
```

### 获取饱水度恢复值

获取物品堆能够恢复的饱水度。

```java
public static int getQuenched(ItemStack stack)
```

### 获取纯净度等级

获取物品堆的纯净度等级。如果未定义，则返回`common.toml`中的默认纯净度。

```java
public static int getPurity(ItemStack stack)
```

## 附录

[ThirstAPI.java](https://github.com/Dark2932/Thirst-Was-Tweaked/blob/main/src/main/java/com/dark2932/thirst_was_tweaked/ThirstAPI.java)
