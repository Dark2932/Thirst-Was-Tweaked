package com.dark2932.thirst_was_tweaked.content.registry;

import com.dark2932.thirst_was_tweaked.ThirstWasTweaked;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author Dark2932
 */
public class ThirstTweakPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, ThirstWasTweaked.MODID);



    public static void init(IEventBus bus) {
        POTIONS.register(bus);
    }

}
