# English

This mod adds some effects to enrich the player's experience with the **Thirst Was Taken** mod.

## Contents

- [Hydratoxin](#hydratoxin)
- [Appendix](#appendix)

## Hydratoxin

The Hydratoxin effect has a chance to be applied when the player drinks water with a non-purified level. The probabilities are as follows:

- **0**: Dirty - Default `30%` chance to trigger Hydratoxin.
- **1**: Slightly Dirty - Default `18%` chance to trigger Hydratoxin.
- **2**: Acceptable - Default `5%` chance to trigger Hydratoxin.
- **3**: Purified - Safe, `0%` chance to trigger Hydratoxin.

The probability of obtaining Hydratoxin for purity levels 0-2 can be modified in `thirst/thirst_was_tweaked.toml`, while the probability for purity level 3 is always 0%.

The duration and amplifier for obtaining Hydratoxin are always the same: the duration is `60 seconds`, and the amplifier is `0`.

During the period when the player has the Hydratoxin effect, thirst and quenched values will be consumed faster (similar to vanilla food logic, prioritizing quenched consumption), and a nausea effect lasting `10 seconds` will be applied every `12 seconds`.

## Appendix

[ThirstTweakEffects.java](https://github.com/Dark2932/Thirst-Was-Tweaked/blob/main/src/main/java/com/dark2932/thirst_was_tweaked/content/registry/ThirstTweakEffects.java)

[HydratoxinEffect.java](https://github.com/Dark2932/Thirst-Was-Tweaked/blob/main/src/main/java/com/dark2932/thirst_was_tweaked/content/effect/HydratoxinEffect.java)

---

# 简体中文

本模组添加了一些药水效果，用于丰富玩家对 **Thirst Was Taken** 模组的体验。

## 目录

- [水毒](#水毒)
- [附录](#附录)

## 水毒

水毒效果在玩家喝了非纯净等级的水时有概率被附加，概率如下：

- **0**: 脏的 (Dirty) - 默认`30%`概率触发水毒。
- **1**: 有点脏的 (Slightly Dirty) - 默认`18%`概率触发水毒。
- **2**: 可接受的 (Acceptable) - 默认`5%`概率触发水毒。
- **3**: 纯净的 (Purified) - 安全，`0%`概率触发水毒。

其中0-2纯净等级是可以在`thirst/thirst_was_tweaked.toml`中修改获取水毒的概率的，而纯净等级为3的概率永远为0%。

每次获得水毒的时长和强度是一样的：时长均为60秒，强度为0（amplifier）。

在玩家带有水毒效果期间，口渴值和饱水度会加速消耗（同原版食物逻辑一样，优先消耗饱水度），且每12秒会得到一个持续10秒的反胃效果。

## 附录

[ThirstTweakEffects.java](https://github.com/Dark2932/Thirst-Was-Tweaked/blob/main/src/main/java/com/dark2932/thirst_was_tweaked/content/registry/ThirstTweakEffects.java)

[HydratoxinEffect.java](https://github.com/Dark2932/Thirst-Was-Tweaked/blob/main/src/main/java/com/dark2932/thirst_was_tweaked/content/effect/HydratoxinEffect.java)