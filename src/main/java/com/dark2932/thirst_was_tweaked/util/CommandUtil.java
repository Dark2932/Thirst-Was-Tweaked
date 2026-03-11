package com.dark2932.thirst_was_tweaked.util;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

/**
 * @author Dark2932
 */
public class CommandUtil {

    public static void execute(Player player, boolean isByServer, boolean isNoFeedback, String command) {
        if (player instanceof ServerPlayer sPlayer) {
            MinecraftServer server = sPlayer.server;
            CommandSourceStack source = isByServer ? server.createCommandSourceStack().withPermission(4) : sPlayer.createCommandSourceStack();
            if (isNoFeedback) source = source.withSuppressedOutput();
            server.getCommands().performPrefixedCommand(source, command);
        }
    }

}
