package com.dark2932.thirst_was_tweaked.mixin;

import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.List;

/**
 * @author Dark2932
 */
public class ThirstTweakMixinCanceller implements MixinCanceller {

    @Override
    public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
        if (mixinClassName.equals("dev.ghen.thirst.foundation.mixin.MixinItemStack")) {
            return true;
        }
        return false;
    }

}
