package com.dark2932.thirst_was_tweaked.content.registry;

import com.dark2932.thirst_was_tweaked.ThirstWasTweaked;
import com.dark2932.thirst_was_tweaked.content.effect.HydrotoxinEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author Dark2932
 */
public class ThirstTweakEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ThirstWasTweaked.MODID);

    public static final RegistryObject<MobEffect> HYDROTOXIN = EFFECTS.register("hydrotoxin", HydrotoxinEffect::new);

    public static void init(IEventBus bus) {
        EFFECTS.register(bus);
    }

}