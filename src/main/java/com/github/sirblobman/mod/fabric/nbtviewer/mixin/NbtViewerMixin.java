package com.github.sirblobman.mod.fabric.nbtviewer.mixin;

import net.minecraft.client.gui.screens.TitleScreen;

import com.github.sirblobman.mod.fabric.nbtviewer.NbtViewerMod;

import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public final class NbtViewerMixin {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        Logger logger = NbtViewerMod.getLogger();
        logger.info("NBT Viewer Mixin Test");
    }
}
