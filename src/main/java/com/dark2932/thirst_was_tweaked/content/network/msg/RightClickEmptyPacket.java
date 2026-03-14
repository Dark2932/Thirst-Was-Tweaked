package com.dark2932.thirst_was_tweaked.content.network.msg;

import com.dark2932.thirst_was_tweaked.content.config.ThirstTweakConfig;
import dev.ghen.thirst.content.purity.WaterPurity;
import dev.ghen.thirst.foundation.common.capability.ModCapabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * @author Dark2932
 */
public class RightClickEmptyPacket {

    private final UUID uuid;

    public RightClickEmptyPacket(UUID uuid) {
        this.uuid = uuid;
    }

    public static void encode(RightClickEmptyPacket msg, FriendlyByteBuf buf) {
        buf.writeUUID(msg.uuid);
    }

    public static RightClickEmptyPacket decode(FriendlyByteBuf buf) {
        return new RightClickEmptyPacket(buf.readUUID());
    }

    public static void handle(RightClickEmptyPacket msg, Supplier<NetworkEvent.Context> sup) {
        NetworkEvent.Context ctx = sup.get();
        if (ctx.getDirection().getReceptionSide().isServer()) {
            ctx.enqueueWork(() -> {
                ServerPlayer player = ctx.getSender();
                if (player != null && player.getUUID().equals(msg.uuid)) {
                    Level level = player.level();
                    BlockPos pos = player.blockPosition();

                    //增加玩家水量，并向周围其他人播放此玩家的挥臂动画、喝水音效
                    if (level.isRainingAt(pos) && level.canSeeSky(pos) &&
                        player.isCrouching() && player.getXRot() <= -75.0f) {
                        player.getCapability(ModCapabilities.PLAYER_THIRST).ifPresent(cap -> {
                            if (cap.getThirst() < 20) {
                                if (WaterPurity.givePurityEffects(player, ThirstTweakConfig.DRINK_RAIN_PURITY.get())) {
                                    cap.drink(player, ThirstTweakConfig.DRINK_RAIN_HYDRATION.get(), ThirstTweakConfig.DRINK_RAIN_QUENCHED.get());
                                }
                                player.swing(InteractionHand.MAIN_HAND);
                                player.playSound(SoundEvents.GENERIC_DRINK);
                            }
                        });
                    }

                }
            });
        }
        ctx.setPacketHandled(true);
    }

}
