package com.dark2932.thirst_was_tweaked.content.item;

import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dark2932
 */
public class DrinkItem extends Item {

    public final DrinkItemManager manager;

    public DrinkItem(Properties properties, DrinkItemManager manager) {
        super(properties);
        this.manager = manager;
    }

    public boolean hasManager() {
        return manager != null;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity) {
        Player player = (Player) entity;

        //喝（吃）完后返还空容器，创造模式下不返还
        if ((hasManager() && manager.getContainer() != null) && !player.getAbilities().instabuild) {
            ItemStack container = manager.getContainer().getDefaultInstance();
            if (!player.getInventory().add(container)) {
                player.drop(container, false);
            }
        }

        //给予玩家饮品的药水效果
        if (!level.isClientSide && (hasManager() && manager.getEffects() != null)) {
            for (Pair<MobEffectInstance, Float> pair : manager.getEffects()) {
                if (pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
                    player.addEffect(pair.getFirst());
                }
            }
        }

        if (stack.isEdible()) {
            return player.eat(level, stack);
        }

        //以下代码将仅在物品没有食物属性（即注册的物品为纯饮品）时触发，含食物属性物品的逻辑已通过上方的player$eat方法处理

        /**
         * 为什么这里不调用PlayerThirst$drink方法，却把它注释了？
         * 很好，为的就是特别提醒看到这的你，防止你跟thirst模组提供的DrinkableItem的逻辑搞混淆。
         * 在本项目中的mixin/MixinThirstHelper.java中，相关方法已经过调整和重写，DrinkItem已经兼容PlayerThirst$drink方法了。
         * 所以原本thirst模组提供的事件已经自动检测DrinkItem，并调用了PlayerThirst$drink方法。
         * 另外，thirst模组使用mixin修改了Player$eat方法，玩家吃东西后也会调用PlayerThirst$drink方法，因此食物也无需再做单独判定。
         */
        //PlayerThirst.drink(stack, player);

        //创造模式不消耗，其他模式每使用完消耗1个
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        //事件发布
        level.gameEvent(player, GameEvent.ITEM_INTERACT_FINISH, player.getEyePosition());

        //兼容玩家统计中的总物品使用次数
        player.awardStat(Stats.ITEM_USED.get(this));

        //进度触发器
        if (player instanceof ServerPlayer sPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(sPlayer, stack);
        }

        //玩家喝完后打嗝
        if (hasManager() && manager.ifBurp()) {
            level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
        }

        return stack;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.isEdible() && !player.canEat(stack.getFoodProperties(player).canAlwaysEat())) {
            return InteractionResultHolder.fail(stack);
        }
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return manager.getUseDuration();
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        if (hasManager() && manager.isFoodAnim()) {
            return UseAnim.EAT;
        } else {
            return UseAnim.DRINK;
        }
    }

}