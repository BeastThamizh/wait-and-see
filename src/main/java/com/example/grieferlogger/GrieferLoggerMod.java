package com.example.grieferlogger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrieferLoggerMod implements ModInitializer {
    public static final String MOD_ID = "grieferlogger";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static String trackedPlayer = "";

    @Override
    public void onInitialize() {
        LOGGER.info("Griefer Logger Mod initialized.");
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            LoggerCommands.register(dispatcher);
        });

        ChestInteractionListener.register();
    }
}
