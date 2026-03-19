# English

`ThirstTweakConfig` is the configuration class for the **Thirst Was Tweaked** mod, implemented through the Forge configuration system. The generated configuration file is located at `config/thirst/thirst-was-tweaked.toml`.

## Contents

- [Functional Tweaks](#functional-tweaks)
- [Hydratoxin Effect](#hydratoxin-effect-1)
- [Adjustments to Thirst Was Taken Config](#adjustments-to-thirst-was-taken-config)
- [Appendix](#appendix)

## Functional Tweaks

This section contains toggles and value adjustments for the mod's core mechanisms.

### Retain Thirst Value
Sets whether to retain thirst data after player death.
- **Config Key**: `retainThirstValue`
- **Default Value**: `false`

### Retain Food Value
Sets whether to retain food data (Hunger) after player death.
- **Config Key**: `retainFoodValue`
- **Default Value**: `false`

### Rainwater Hydration
Sets the thirst value restored when players crouch and right-click the sky to drink rainwater.
- **Config Key**: `drinkRainHydration`
- **Range**: `0 - 20`
- **Default Value**: `1`

### Rainwater Quenched Value
Sets the quenched value (saturation) restored when players drink rainwater.
- **Config Key**: `drinkRainQuenched`
- **Range**: `0 - 20`
- **Default Value**: `0`

### Rainwater Purity
Sets the purity level of rainwater.
- **Config Key**: `drinkRainPurity`
- **Range**: `0 - 3` (0: Dirty, 1: Slightly Dirty, 2: Acceptable, 3: Purified)
- **Default Value**: `0`

## Hydratoxin Effect

This section is used to configure the probability (0-100) of triggering the "Hydratoxin" effect for different water purities. The effect will never be triggered when the purity is set to "Purified".

### Dirty Water Hydratoxin Chance
Probability of triggering Hydratoxin when drinking water with a purity of 0.
- **Config Key**: `dirtyHydratoxinChance`
- **Default Value**: `30`

### Slightly Dirty Water Hydratoxin Chance
Probability of triggering Hydratoxin when drinking water with a purity of 1.
- **Config Key**: `slightlyDirtyHydratoxinChance`
- **Default Value**: `18`

### Acceptable Water Hydratoxin Chance
Probability of triggering Hydratoxin when drinking water with a purity of 2.
- **Config Key**: `acceptableHydratoxinChance`
- **Default Value**: `5`

## Adjustments to Thirst Was Taken Config

### Disabled Configurations

Most disabled configuration options from the **Thirst Was Taken** mod are located in `common.toml`. If you see an option labeled `Banned<xxx>`, it means this configuration has been disabled and will no longer take effect in the game.

### Modifications to Existing Configurations

A few configuration options have been modified by the author using Mixins to adapt to this mod. Currently modified options include:

- #### common.toml

  - **DrinkRainWater:** Now used to toggle the drink-rainwater feature provided by this mod. Additionally, hydration and purity can be set in `thirst_was_tweaked.toml`.
  - **handDrinkingHydration:** Default value changed to 2, and a range limit of 0-20 has been added.
  - **handDrinkingQuenched:** Default value changed to 0, and a range limit of 0-20 has been added.

## Appendix

[ThirstTweakConfig.java](https://github.com/Dark2932/Thirst-Was-Tweaked/blob/main/src/main/java/com/dark2932/thirst_was_tweaked/content/config/ThirstTweakConfig.java)

[RetainF$TValueEvent.java](https://github.com/Dark2932/Thirst-Was-Tweaked/blob/main/src/main/java/com/dark2932/thirst_was_tweaked/content/event/RetainF%24TValueEvent.java)

---

# 简体中文

`ThirstTweakConfig` 是 **Thirst Was Tweaked** 模组的配置类，通过 Forge 的配置系统实现。生成的配置文件位于 `config/thirst/thirst-was-tweaked.toml`下。

## 目录

- [功能调整 (Functional Tweaks)](#功能调整-functional-tweaks)
- [水毒效果 (Hydratoxin Effect)](#水毒效果-hydratoxin-effect)
- [对Thirst Was Taken配置的调整](#对thirst-was-taken配置的调整)
- [附录](#附录)

## 功能调整 (Functional Tweaks)

此部分包含模组核心机制的开关与数值的调整。

### 死亡保留口渴值
设置玩家死亡后是否保留口渴数据（Thirst）。
- **配置项**: `retainThirstValue`
- **默认值**: `false`

### 死亡保留饱食度
设置玩家死亡后是否保留食物数据（Hunger）。
- **配置项**: `retainFoodValue`
- **默认值**: `false`

### 雨水的补水量
设置玩家通过潜行右键天空喝雨水时恢复的口渴值。
- **配置项**: `drinkRainHydration`
- **范围**: `0 - 20`
- **默认值**: `1`

### 雨水的饱水度恢复值
设置玩家喝雨水时恢复的饱水度。
- **配置项**: `drinkRainQuenched`
- **范围**: `0 - 20`
- **默认值**: `0`

### 雨水纯净度
设置雨水的纯净度等级。
- **配置项**: `drinkRainPurity`
- **范围**: `0 - 3` (0: 脏水, 1: 有点脏的, 2: 可接受的, 3: 纯净)
- **默认值**: `0`

## 水毒效果 (Hydratoxin Effect)

此部分用于配置不同纯净度的水触发“水毒”效果的概率（0-100），纯净度为纯净时不会触发水毒效果。

### 脏水触发水毒的概率
饮用纯净度为 0 的水时触发水毒的概率。
- **配置项**: `dirtyHydratoxinChance`
- **默认值**: `30`

### 有点脏的水触发水毒的概率
饮用纯净度为 1 的水时触发水毒的概率。
- **配置项**: `slightlyDirtyHydratoxinChance`
- **默认值**: `18`

### 可接受的水触发水毒的概率
饮用纯净度为 2 的水时触发水毒的概率。
- **配置项**: `acceptableHydratoxinChance`
- **默认值**: `5`

## 对Thirst Was Taken配置的调整

### 禁用的配置

Thirst Was Taken模组被禁用的配置选项大部分都位于`common.toml`中，如果你看到了被标注`Banned<xxx>`的选项，那说明这个配置已经被禁用，它不会再对游戏生效。

### 对原有配置的修改

少部分的配置选项因需要适配此模组，故作者使用Mixin进行了更改，目前已受更改的配置选项有：

- #### common.toml

  - **DrinkRainWater:** 现在用于开关此模组提供的喝雨水功能，另外可以在`thirst_was_tweaked.toml`中设置补水量和纯净度。
  - **handDrinkingHydration:** 默认值更改为2，且增加了0~20的范围限制。
  - **handDrinkingQuenched:** 默认值更改为0，且增加了0~20的范围限制。

## 附录

[ThirstTweakConfig.java](https://github.com/Dark2932/Thirst-Was-Tweaked/blob/main/src/main/java/com/dark2932/thirst_was_tweaked/content/config/ThirstTweakConfig.java)

[RetainF$TValueEvent.java](https://github.com/Dark2932/Thirst-Was-Tweaked/blob/main/src/main/java/com/dark2932/thirst_was_tweaked/content/event/RetainF%24TValueEvent.java)
