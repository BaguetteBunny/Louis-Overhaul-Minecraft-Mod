package net.louis.overhaulmod.component;

import net.louis.overhaulmod.LouisOverhaulMod;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ModComponents {
    public static void initialize() {
        LouisOverhaulMod.LOGGER.info("Registering {} components", LouisOverhaulMod.MOD_ID);
    }
}