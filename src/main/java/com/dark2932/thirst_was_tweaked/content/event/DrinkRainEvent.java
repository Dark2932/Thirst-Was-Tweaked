package com.dark2932.thirst_was_tweaked.content.event;

import com.dark2932.thirst_was_tweaked.ThirstWasTweaked;
import com.dark2932.thirst_was_tweaked.content.network.ThirstTweakNetworkHandler;
import com.dark2932.thirst_was_tweaked.content.network.msg.RightClickEmptyPacket;
import dev.ghen.thirst.foundation.common.capability.ModCapabilities;
import dev.ghen.thirst.foundation.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

/**
 * @author Dark2932
 */
@Mod.EventBusSubscriber(modid = ThirstWasTweaked.MODID)
public class DrinkRainEvent {

    @SubscribeEvent
    public static void onDrinkRain(PlayerInteractEvent.RightClickEmpty event) {
        if (event.getHand().equals(InteractionHand.MAIN_HAND) && CommonConfig.CAN_DRINK_RAIN_WATETR.get()) {
            Player player = event.getEntity();
            Level level = player.level();
            BlockPos pos = player.blockPosition();
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {

                //向服务端发送数据，加水量
                ThirstTweakNetworkHandler.sendToServer(new RightClickEmptyPacket(player.getUUID()));

                // ↓仅为当前玩家播放挥臂动画和播放喝水音效↓
                if (level.isRainingAt(pos) && level.canSeeSky(pos) &&
                    player.isCrouching() && player.getXRot() <= -75.0f) {
                    player.getCapability(ModCapabilities.PLAYER_THIRST).ifPresent(cap -> {
                        if (cap.getThirst() < 20) {
                            player.swing(InteractionHand.MAIN_HAND);
                            player.playSound(SoundEvents.GENERIC_DRINK);
                        }
                    });
                }

            });
        }
    }

}
