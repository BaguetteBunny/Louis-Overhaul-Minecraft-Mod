package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.utils.EnchantmentCapRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {

    public AnvilScreenHandlerMixin(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @Inject(method = "updateResult", at = @At("RETURN"))
    private void checkEnchantmentCapOnCombine(CallbackInfo ci) {
        if (this.output.isEmpty()) return;

        int cap = EnchantmentCapRegistry.getCap(this.output.getStack(0).getItem());
        ItemEnchantmentsComponent enchantments = this.output.getStack(0).get(DataComponentTypes.ENCHANTMENTS);

        if (enchantments != null && enchantments.getSize() > cap) {
            this.output.setStack(0, ItemStack.EMPTY);
        }
    }

    @Inject(method = "updateResult", at = @At("TAIL"))
    private void preventEnchantmentCapExceed(CallbackInfo ci) {
        ItemStack outputStack = this.output.getStack(0);

        if (outputStack.isEmpty()) return;

        int cap = EnchantmentCapRegistry.getCap(outputStack.getItem());
        if (cap == Integer.MAX_VALUE) return;

        ItemEnchantmentsComponent enchantments = outputStack.get(DataComponentTypes.ENCHANTMENTS);

        if (enchantments != null && enchantments.getSize() > cap) {
            this.output.setStack(0, ItemStack.EMPTY);
        }
    }
}
