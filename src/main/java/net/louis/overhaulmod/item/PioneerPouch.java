package net.louis.overhaulmod.item;

import net.louis.overhaulmod.component.CustomBundleContentsComponent;
import net.louis.overhaulmod.component.ModComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class PioneerPouch extends BigBundleItem {
    public PioneerPouch(Settings settings, int max_slot) {
        super(settings, max_slot);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (!(otherStack.getItem() instanceof BlockItem)) {
            return false;
        }
        return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType != ClickType.RIGHT) {
            return false;
        } else {
            CustomBundleContentsComponent bundleContentsComponent = stack.get(ModComponents.CUSTOM_BUNDLE_CONTENTS);
            if (bundleContentsComponent == null) {
                return false;
            } else {
                ItemStack itemStack = slot.getStack();
                CustomBundleContentsComponent.Builder builder = new CustomBundleContentsComponent.Builder(bundleContentsComponent);
                if (itemStack.isEmpty()) {
                    this.playRemoveOneSound(player);
                    ItemStack itemStack2 = builder.removeFirst();
                    if (itemStack2 != null) {
                        ItemStack itemStack3 = slot.insertStack(itemStack2);
                        builder.add(itemStack3);
                    }
                } else if (itemStack.getItem() instanceof BlockItem) {
                    int i = builder.add(slot, player);
                    if (i > 0) {
                        this.playInsertSound(player);
                    }
                }

                stack.set(ModComponents.CUSTOM_BUNDLE_CONTENTS, builder.build());
                return true;
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return TypedActionResult.fail(user.getStackInHand(hand));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack bundle = context.getStack();
        PlayerEntity player = context.getPlayer();

        if (player == null || player.getWorld().isClient() || !placeRandomBlock(bundle, player, context)) return ActionResult.PASS;
        return ActionResult.SUCCESS;
    }

    private static boolean placeRandomBlock(ItemStack bundle, PlayerEntity player, ItemUsageContext ctx) {
        CustomBundleContentsComponent contents = bundle.get(ModComponents.CUSTOM_BUNDLE_CONTENTS);
        if (contents == null || contents.isEmpty()) return false;

        List<ItemStack> blockItems = contents.stream()
                .filter(s -> s.getItem() instanceof BlockItem)
                .toList();
        if (blockItems.isEmpty()) return false;

        ItemStack chosen = getWeightedRandom(blockItems, player);
        BlockItem blockItem = (BlockItem) chosen.getItem();
        ItemPlacementContext placeCtx = new ItemPlacementContext(ctx);
        blockItem.place(placeCtx);

        CustomBundleContentsComponent.Builder builder = new CustomBundleContentsComponent.Builder(contents);
        for (int i = 0; i < builder.stacks.size(); i++) {
            ItemStack s = builder.stacks.get(i);
            if (ItemStack.areItemsAndComponentsEqual(s, chosen)) {
                s.decrement(1);
                if (s.isEmpty()) builder.stacks.remove(i);

                break;
            }
        }
        bundle.set(ModComponents.CUSTOM_BUNDLE_CONTENTS, builder.build());
        return true;
    }

    private static ItemStack getWeightedRandom(List<ItemStack> stacks, PlayerEntity player) {
        int total = stacks.stream().mapToInt(ItemStack::getCount).sum();
        int r = player.getRandom().nextInt(total);

        for (ItemStack s : stacks) {
            r -= s.getCount();
            if (r < 0) return s;
        }
        return stacks.getFirst();
    }

    private void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_INSERT, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }

    private void playPlaceBlockSound(Entity entity, ItemStack stack) {
        if (stack.getItem() instanceof BlockItem blockItem) {
            entity.playSound(blockItem.getBreakSound(), 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
        }
    }

}
