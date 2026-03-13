package com.dark2932.thirst_was_tweaked.content.item;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dark2932
 */
public class DrinkItemManager {

    private int thirst; //可恢复的口渴值
    private int quenched; //可恢复的水合度（饱水度）
    private int useDuration = 32; //使用时间，原版默认32tick
    private Item container; //喝完后返还的物品，默认为null，即不返还
    private boolean foodAnim; //true播放吃东西的动画，默认为false，即播放喝东西的动画
    private boolean ifBurp; //true让玩家喝完后打嗝，默认false。但如果DrinkItem含有食物属性，则此项会被设置为true
    private List<Pair<MobEffectInstance, Float>> effects; //喝完后给予玩家的药水效果，默认没有

    public int getThirst() {
        return thirst;
    }

    public int getQuenched() {
        return quenched;
    }

    public int getUseDuration() {
        return useDuration;
    }

    public Item getContainer() {
        return container;
    }

    public boolean isFoodAnim() {
        return foodAnim;
    }

    public boolean ifBurp() {
        return ifBurp;
    }

    public List<Pair<MobEffectInstance, Float>> getEffects() {
        return effects;
    }

    public DrinkItemManager thirst(int thirst) {
        this.thirst = thirst;
        return this;
    }

    public DrinkItemManager quenched(int quenched) {
        this.quenched = quenched;
        return this;
    }

    public DrinkItemManager useDuration(int tick) {
        this.useDuration = tick;
        return this;
    }

    public DrinkItemManager container(Item container) {
        this.container = container;
        return this;
    }

    public DrinkItemManager foodAnim() {
        this.foodAnim = true;
        return this;
    }

    public DrinkItemManager burp() {
        this.ifBurp = true;
        return this;
    }

    public DrinkItemManager effect(MobEffectInstance effectIn, float probability) {
        this.effects = new ArrayList<>();
        this.effects.add(Pair.of(effectIn, probability));
        return this;
    }

}