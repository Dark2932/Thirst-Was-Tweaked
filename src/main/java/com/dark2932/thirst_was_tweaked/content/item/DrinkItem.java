package com.dark2932.thirst_was_tweaked.content.item;

import com.mojang.datafixers.util.Pair;
import dev.ghen.thirst.content.thirst.PlayerThirst;
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

        //进度触发器
        if (player instanceof ServerPlayer sPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(sPlayer, stack);
        }

        //兼容玩家统计中的总物品使用次数
        player.awardStat(Stats.ITEM_USED.get(this));

        //事件发布
        level.gameEvent(player, GameEvent.ITEM_INTERACT_FINISH, player.getEyePosition());

        //创造模式不消耗，其他模式每使用完消耗1个
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        //喝（吃）完后返还空容器，创造模式下不返还
        if ((hasManager() && manager.getContainer() != null) && !player.getAbilities().instabuild) {
            ItemStack container = manager.getContainer();
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

        //如果是食物，执行以下逻辑
        if (stack.isEdible() && hasManager()) {

            //触发补水，如果是通过配置文件添加的饮品，则使用配置文件的配置，反之则使用通过DrinkItemManager创建的各种数据。
            //在mixin/MixinThirstHelper.java中，我对相关方法进行了调整和重写，使DrinkItem得以兼容PlayerThirst.drink方法，即自动通过PlayerThirstManager.java中的事件给玩家补水。
            //但由于DrinkItem有可能具有食物属性，而事件中过滤掉了带有食物属性的物品（即使用该物品后不补水），因此需要在此处手动触发一次补水。
            //为什么不把这句写到外层？因为通过DrinkItem注册的纯饮品不包含食物属性（即不会被事件过滤掉），如果写到外层，就会导致纯饮品物品触发两次补水。
            PlayerThirst.drink(stack, player);

            manager.burp(); //检测到物品有食物属性时必打嗝
            player.getFoodData().eat(stack.getItem(), stack, player);
            for (Pair<MobEffectInstance, Float> pair : stack.getFoodProperties(player).getEffects()) {
                if (!level.isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
                    player.addEffect(pair.getFirst());
                }
            }
        }

        //令玩家喝（吃）完后打嗝
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