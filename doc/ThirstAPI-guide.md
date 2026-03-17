# ThirstAPI 使用文档

`ThirstAPI` 是 **Thirst Was Tweaked** 模组提供的API类，此类对修改口渴值（Hydration | Thirst）、饱水度（Quenched）以及纯净度（Purity）的相关方法进行了封装，以便开发者进行集中调用。

## 目录
- [饮用操作](#饮用操作)
- [状态调整](#状态调整)
- [物品属性检查](#物品属性检查)
- [数值获取](#数值获取)

---

## 饮用操作

### 饮用ItemStack
使玩家饮用指定的物品堆。

**注：**此方法默认不会消耗物品，你可以调用`ItemStack$shrink`方法来实现物品消耗。

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

---

## 状态调整

### 增加消耗值
为玩家增加口渴消耗值（Exhaustion），该值达到峰值时会导致口渴度下降。

你可以装载 AppleSkin 模组来显示这个数值。

```java
public static void addExhaustion(Player player, float value)
```

---

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

---

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

---

## 附录：纯净度等级参考
- **0**: 脏的 (Dirty) - 默认`30%`概率触发水毒。
- **1**: 有点脏的 (Slightly Dirty) - 默认`18%`概率触发水毒。
- **2**: 可接受的 (Acceptable) - 默认`5%`概率触发水毒。
- **3**: 纯净的 (Purified) - 安全，无负面效果。
