package com.dark2932.thirst_was_tweaked;

import com.dark2932.thirst_was_tweaked.content.config.ThirstTweakConfig;
import com.dark2932.thirst_was_tweaked.content.network.ThirstTweakNetworkHandler;
import com.dark2932.thirst_was_tweaked.content.registry.ThirstTweakEffects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ThirstWasTweaked.MODID)
public class ThirstWasTweaked {

    public static final String MODID = "thirst_was_tweaked";

    public ThirstWasTweaked() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::onFMLCommonSetup);

        ThirstTweakConfig.init();

        ThirstTweakEffects.init(bus);
    }

    private void onFMLCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(ThirstTweakNetworkHandler::register);
    }

}