package net.louis.overhaulmod.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.item.PotionItem;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

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

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType != ClickType.RIGHT) {
            return false;
        } else {
            BundleContentsComponent bundleContentsComponent = stack.get(DataComponentTypes.BUNDLE_CONTENTS);
            if (bundleContentsComponent == null) {
                return false;
            } else {
                ItemStack itemStack = slot.getStack();
                BundleContentsComponent.Builder builder = new BundleContentsComponent.Builder(bundleContentsComponent);
                if (itemStack.isEmpty()) {
                    this.playRemoveOneSound(player);
                    ItemStack itemStack2 = builder.removeFirst();
                    if (itemStack2 != null) {
                        ItemStack itemStack3 = slot.insertStack(itemStack2);
                        builder.add(itemStack3);
                    }
                } else if (itemStack.isOf(Items.POTION)) {
                    int i = builder.add(slot, player);
                    if (i > 0) {
                        this.playInsertSound(player);
                    }
                }

                stack.set(DataComponentTypes.BUNDLE_CONTENTS, builder.build());
                return true;
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (consumeFirstPotionItem(itemStack, user)) {
            this.playPotionConsumeSound(user);
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            return TypedActionResult.success(itemStack, world.isClient());
        } else {
            return TypedActionResult.fail(itemStack);
        }
    }

    private static boolean consumeFirstPotionItem(ItemStack stack, PlayerEntity player) {
        BundleContentsComponent bundleContentsComponent = stack.get(DataComponentTypes.BUNDLE_CONTENTS);
        if (bundleContentsComponent != null && !bundleContentsComponent.isEmpty()) {
            ItemStack firstItem = bundleContentsComponent.stream().iterator().next();


            if (firstItem.getItem() instanceof PotionItem) {
                PotionContentsComponent potionContents = firstItem.get(DataComponentTypes.POTION_CONTENTS);
                if (potionContents != null) {
                    for (StatusEffectInstance effect : potionContents.getEffects()) {
                        player.addStatusEffect(new StatusEffectInstance(effect));
                    }
                }

                BundleContentsComponent.Builder builder = new BundleContentsComponent.Builder(bundleContentsComponent);
                builder.removeFirst();
                stack.set(DataComponentTypes.BUNDLE_CONTENTS, builder.build());

                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    private void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_INSERT, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }

    private void playPotionConsumeSound(Entity entity) {
        entity.playSound(SoundEvents.ENTITY_GENERIC_DRINK, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }

}


