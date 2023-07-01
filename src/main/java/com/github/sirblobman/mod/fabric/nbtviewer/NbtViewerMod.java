package com.github.sirblobman.mod.fabric.nbtviewer;

import org.jetbrains.annotations.NotNull;

import com.github.sirblobman.mod.fabric.nbtviewer.callback.NbtViewerCallback;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NbtViewerMod implements ClientModInitializer {
    private static final Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger("NBT Viewer Mod");
    }

    public static @NotNull Logger getLogger() {
        return LOGGER;
    }

    public NbtViewerMod() {
        // Empty Constructor
    }

    @Override
    public void onInitializeClient() {
        Logger logger = getLogger();
        logger.info("Initializing...");

        ItemTooltipCallback listener = new NbtViewerCallback();
        ItemTooltipCallback.EVENT.register(listener);
        logger.info("Registered nbt viewer item tooltip callback.");

        logger.info("Finished.");
    }
}
