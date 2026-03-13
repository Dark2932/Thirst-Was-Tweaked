package com.dark2932.thirst_was_tweaked.content.event;

import com.dark2932.thirst_was_tweaked.ThirstWasTweaked;
import com.dark2932.thirst_was_tweaked.content.config.ThirstTweakConfig;
import dev.ghen.thirst.foundation.common.capability.ModCapabilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author Dark2932
 */
@Mod.EventBusSubscriber(modid = ThirstWasTweaked.MODID)
public class RetainF$TValueEvent {

    @SubscribeEvent
    public static void onRetain(PlayerEvent.Clone event) {
        if (!event.getEntity().level().isClientSide && event.isWasDeath()) {
            Player oldPlayer = event.getOriginal();
            Player newPlayer = event.getEntity();

            //保留玩家死前的食物数据
            if (ThirstTweakConfig.RETAIN_FOOD_VALUE.get()) {
                FoodData oldFoodData = oldPlayer.getFoodData();
                FoodData newFoodData = newPlayer.getFoodData();
                newFoodData.setFoodLevel(oldFoodData.getFoodLevel());
                newFoodData.setSaturation(oldFoodData.getSaturationLevel());
                newFoodData.setExhaustion(oldFoodData.getExhaustionLevel());
            }

            //保留玩家死前的口渴数据
            if (ThirstTweakConfig.RETAIN_THIRST_VALUE.get()) {
                oldPlayer.reviveCaps();
                oldPlayer.getCapability(ModCapabilities.PLAYER_THIRST).ifPresent(oldCap -> {
                    newPlayer.getCapability(ModCapabilities.PLAYER_THIRST).ifPresent(newCap -> {
                        newCap.copy(oldCap);
                    });
                });
                oldPlayer.invalidateCaps();
            }

        }

    }

}
