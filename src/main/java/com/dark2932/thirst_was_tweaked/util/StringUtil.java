package com.dark2932.thirst_was_tweaked.util;

import com.dark2932.thirst_was_tweaked.ThirstWasTweaked;
import net.minecraft.resources.ResourceLocation;

/**
 * @author Dark2932
 */
public class StringUtil {

    public static ResourceLocation locate(String path) {
        return ResourceLocation.fromNamespaceAndPath(ThirstWasTweaked.MODID, path);
    }

}
