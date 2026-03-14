package com.dark2932.thirst_was_tweaked.mixin.thirst.accessor;

import dev.ghen.thirst.foundation.gui.appleskin.HUDOverlayHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Dark2932
 */
@Mixin(value = HUDOverlayHandler.class, remap = false)
public interface HUDOverlayHandlerAccessor {

    @Accessor("flashAlpha")
    static float getFlashAlpha() {
        throw new AssertionError();
    }

}
