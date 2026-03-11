package com.dark2932.thirst_was_tweaked.content.network;

import com.dark2932.thirst_was_tweaked.ThirstWasTweaked;
import com.dark2932.thirst_was_tweaked.content.network.msg.RightClickEmptyPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

/**
 * @author Dark2932
 */
public class ThirstTweakNetworkHandler {

    private static int ID = 0;
    private static final String VERSION = "1.0";
    private static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(ThirstWasTweaked.MODID, "network"),
            () -> VERSION,
            VERSION::equals,
            VERSION::equals
    );

    public static void register() {
        CHANNEL.registerMessage(ID++, RightClickEmptyPacket.class, RightClickEmptyPacket::encode, RightClickEmptyPacket::decode, RightClickEmptyPacket::handle);
    }

    public static <M> void sendToPlayerClient(M msg, ServerPlayer player) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }

    public static <M> void sendToAllClient(M msg) {
        CHANNEL.send(PacketDistributor.ALL.noArg(), msg);
    }

    public static <M> void sendToServer(M msg) {
        CHANNEL.sendToServer(msg);
    }

}