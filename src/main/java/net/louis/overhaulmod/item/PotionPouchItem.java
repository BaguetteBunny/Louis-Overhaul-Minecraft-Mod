package net.louis.overhaulmod.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;

public class PotionPouchItem extends BundleItem {
    public PotionPouchItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (!otherStack.isOf(Items.POTION)) {
            return false;
        }

        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
    }

}


