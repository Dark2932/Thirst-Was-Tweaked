package com.dark2932.thirst_was_tweaked.impl;

import net.minecraft.network.FriendlyByteBuf;

/**
 * @author Dark2932
 */
public interface IBufStorager {

    Object read(FriendlyByteBuf buf);

    void write(FriendlyByteBuf buf);

}