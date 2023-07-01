package com.github.sirblobman.mod.fabric.nbtviewer.callback;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import com.google.common.base.Splitter;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;

public final class NbtViewerCallback implements ItemTooltipCallback {
    @Override
    public void getTooltip(ItemStack stack, TooltipFlag context, List<Component> lines) {
        if (!context.isAdvanced()) {
            return;
        }

        if(Screen.hasAltDown() && Screen.hasShiftDown()) {
            lines.clear();

            Component title = getNbtTitle();
            lines.add(title);

            List<Component> nbtLines = getNbtDataLines(stack);
            lines.addAll(nbtLines);
            return;
        }

        Component guide = getGuide();
        lines.add(Component.empty());
        lines.add(guide);
    }

    private @NotNull Component getGuide() {
        Component shift = Component.literal("Shift").withStyle(ChatFormatting.UNDERLINE);
        Component alt = Component.literal("Alt").withStyle(ChatFormatting.UNDERLINE);
        return Component.literal("Press ").append(shift).append(" and ").append(alt)
                .append(" to view item NBT data.").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC);
    }

    private @NotNull Component getNbtTitle() {
        return Component.literal("NBT Data").withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.BOLD);
    }

    private @NotNull List<Component> getNbtDataLines(@NotNull ItemStack stack) {
        String nbtData = getNbtData(stack);
        List<Component> lines = new ArrayList<>();

        Iterable<String> split = Splitter.fixedLength(60).split(nbtData);
        for (String lineString : split) {
            Component line = Component.literal(lineString).withStyle(ChatFormatting.GRAY);
            lines.add(line);
        }

        return lines;
    }

    private @NotNull String getNbtData(@NotNull ItemStack stack) {
        CompoundTag tag = stack.save(new CompoundTag());
        return tag.toString();
    }
}
