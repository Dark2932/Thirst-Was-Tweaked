package com.dark2932.thirst_was_tweaked;

import com.dark2932.thirst_was_tweaked.network.TWTNetworkHandler;
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
    }

    private void onFMLCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(TWTNetworkHandler::register);
    }

}