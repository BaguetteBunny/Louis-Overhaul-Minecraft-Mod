package net.louis.overhaulmod.component;

import net.minecraft.item.tooltip.TooltipData;

public record CustomBundleTooltipData(CustomBundleContentsComponent contents) implements TooltipData {
}

