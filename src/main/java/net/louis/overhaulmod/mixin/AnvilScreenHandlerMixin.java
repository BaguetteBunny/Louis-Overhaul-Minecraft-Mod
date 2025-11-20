package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.utils.EnchantmentCapRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.screen.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {
    @Shadow private Property levelCost;

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

    @Inject(method = "updateResult", at = @At("RETURN"))
    private void force30LevelsIfBothItemsEnchanted(CallbackInfo ci) {
        ItemStack first = this.input.getStack(0);
        ItemStack second = this.input.getStack(1);

        if (!first.isEmpty() && !second.isEmpty()
                && first.hasEnchantments() && second.hasEnchantments()
                && !first.isOf(Items.ENCHANTED_BOOK) && !second.isOf(Items.ENCHANTED_BOOK)) {
            levelCost.set(30);
        }
    }

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    private void customAnvilMechanics(CallbackInfo ci) {
        ItemStack leftStack = this.input.getStack(0);
        ItemStack rightStack = this.input.getStack(1);
        if (leftStack.isEmpty()) return;

        // Case 1: Enchantment Book Application
        if (rightStack.getItem() instanceof EnchantedBookItem) {
            ItemStack result = leftStack.copy();
            ItemEnchantmentsComponent bookEnchants = rightStack.get(DataComponentTypes.STORED_ENCHANTMENTS);

            if (bookEnchants != null && !bookEnchants.isEmpty()) {
                ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(
                        result.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT)
                );

                int cost = 0;

                // Calculate cost and apply enchantments
                for (var entry : bookEnchants.getEnchantmentEntries()) {
                    RegistryEntry<Enchantment> enchantment = entry.getKey();
                    if (!enchantment.value().isSupportedItem(leftStack)) {
                        this.output.setStack(0, ItemStack.EMPTY);
                        this.levelCost.set(0);
                        ci.cancel();
                        return;
                    }

                    int level = entry.getIntValue();
                    int weight = enchantment.value().getWeight();

                    int enchantCost = (weight > 5) ? 5 * level : (10 - weight) * level;

                    cost += enchantCost;
                    builder.set(enchantment, level);
                }

                result.set(DataComponentTypes.ENCHANTMENTS, builder.build());

                // Cap at 35
                if (cost > 35) cost = 35;

                int cap = EnchantmentCapRegistry.getCap(result.getItem());
                ItemEnchantmentsComponent finalEnchants = result.get(DataComponentTypes.ENCHANTMENTS);

                if (finalEnchants != null && finalEnchants.getSize() > cap) {
                    this.output.setStack(0, ItemStack.EMPTY);
                    this.levelCost.set(0);
                    ci.cancel();
                    return;
                }

                this.output.setStack(0, result);
                this.levelCost.set(cost);
                ci.cancel();
                return;
            }
        }

        // Case 2: Item Repair with Lapis
        if (rightStack.getItem() == Items.LAPIS_LAZULI) {
            if (!leftStack.isDamaged()) return;

            ItemStack result = leftStack.copy();
            int damage = leftStack.getDamage();

            // Base durability per lapis
            int baseDurability = isToolOrWeapon(leftStack.getItem()) ? 400 : 100;

            ItemEnchantmentsComponent enchantments = leftStack.get(DataComponentTypes.ENCHANTMENTS);
            boolean hasCurse = false;
            int enchantmentPenalty = 0;

            if (enchantments != null && !enchantments.isEmpty()) {
                for (var entry : enchantments.getEnchantmentEntries()) {
                    RegistryEntry<Enchantment> enchantment = entry.getKey();
                    int level = entry.getIntValue();

                    if (enchantment.isIn(EnchantmentTags.CURSE)) {
                        hasCurse = true;
                    } else {
                        int weight = enchantment.value().getWeight();
                        enchantmentPenalty += (10 - weight) * level * (baseDurability/100) * 3 / 4;
                    }
                }
            }

            if (enchantmentPenalty >= 75 && baseDurability == 100) enchantmentPenalty = 75;
            else if (enchantmentPenalty >= 300) enchantmentPenalty = 300;

            // Apply curse multiplier
            if (hasCurse) baseDurability /= 2;

            int finalDurabilityPerLapis = Math.max(1, baseDurability - enchantmentPenalty);
            int lapisCount = rightStack.getCount();
            int totalRepair = finalDurabilityPerLapis * lapisCount;
            int actualRepair = Math.min(totalRepair, damage);

            if (actualRepair > 0) {
                result.setDamage(damage - actualRepair);

                this.output.setStack(0, result);
                this.levelCost.set(0); // No XP cost for repairs
                ci.cancel();
                return;
            }
        }
    }

    @Inject(method = "onTakeOutput", at = @At("HEAD"))
    private void consumeLapisForRepair(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        ItemStack leftStack = this.input.getStack(0);
        ItemStack rightStack = this.input.getStack(1);

        // If repairing with lapis, consume the appropriate amount
        if (rightStack.getItem() == Items.LAPIS_LAZULI && leftStack.isDamaged()) {
            int damage = leftStack.getDamage();
            int baseDurability = isToolOrWeapon(leftStack.getItem()) ? 400 : 100;

            ItemEnchantmentsComponent enchantments = leftStack.get(DataComponentTypes.ENCHANTMENTS);
            boolean hasCurse = false;
            int enchantmentPenalty = 0;

            if (enchantments != null && !enchantments.isEmpty()) {
                for (var entry : enchantments.getEnchantmentEntries()) {
                    RegistryEntry<Enchantment> enchantment = entry.getKey();
                    int level = entry.getIntValue();

                    if (enchantment.isIn(EnchantmentTags.CURSE)) {
                        hasCurse = true;
                    } else {
                        int weight = enchantment.value().getWeight();
                        enchantmentPenalty += (10 - weight) * level / 3;
                    }
                }
            }

            if (hasCurse) {
                baseDurability *= 2;
            }

            int finalDurabilityPerLapis = Math.max(1, baseDurability - enchantmentPenalty);
            int lapisNeeded = (int) Math.ceil((double) damage / finalDurabilityPerLapis);

            // Consume lapis
            rightStack.decrement(lapisNeeded);
        }
    }

    private boolean isToolOrWeapon(Item item) {
        return item instanceof ToolItem ||
                item instanceof SwordItem ||
                item instanceof TridentItem ||
                item instanceof MaceItem ||
                item instanceof BowItem ||
                item instanceof CrossbowItem ||
                item instanceof FishingRodItem ||
                item instanceof OnAStickItem ||
                item instanceof BrushItem ||
                item instanceof ShieldItem ||
                item instanceof ShearsItem;
    }
}
