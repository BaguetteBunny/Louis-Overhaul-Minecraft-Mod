package net.louis.overhaulmod.utils;

import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.stat.Stats;
import net.minecraft.util.ItemActionResult;

public class CauldronBehaviorHooks {
    public static CauldronBehavior CLEAN_BLOCK = (state, world, pos, player, hand, stack) -> {
        if (!world.isClient) {
            Item i;
            if (stack.isIn(ItemTags.WOOL)) {i = Items.WHITE_WOOL;}
            else if (stack.isIn(ItemTags.CANDLES)) {i = Items.CANDLE;}
            else if (stack.isIn(ItemTags.WOOL_CARPETS)) {i = Items.WHITE_CARPET;}
            else if (stack.isIn(ItemTags.TERRACOTTA)) {i = Items.TERRACOTTA;}
            else if (stack.isIn(ItemTags.BEDS)) {i = Items.WHITE_BED;}
            else {return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;}

            int count = stack.getCount();
            stack.decrement(count);

            player.setStackInHand(hand, new ItemStack(i, count));
            player.incrementStat(Stats.USE_CAULDRON);
            LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
        }
        return ItemActionResult.success(world.isClient);
    };

    public static CauldronBehavior CLEAN_GLAZED_TERRACOTTA = (state, world, pos, player, hand, stack) -> {
        if (!world.isClient) {
            int count = stack.getCount();
            stack.decrement(count);

            player.setStackInHand(hand, new ItemStack(Items.TERRACOTTA, count));
            player.incrementStat(Stats.USE_CAULDRON);
            LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
        }
        return ItemActionResult.success(world.isClient);
    };

    public static CauldronBehavior CLEAN_CONCRETE = (state, world, pos, player, hand, stack) -> {
        if (!world.isClient) {
            int count = stack.getCount();
            stack.decrement(count);

            player.setStackInHand(hand, new ItemStack(Items.WHITE_CONCRETE, count));
            player.incrementStat(Stats.USE_CAULDRON);
            LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
        }
        return ItemActionResult.success(world.isClient);
    };

    public static CauldronBehavior CLEAN_CONCRETE_POWDER = (state, world, pos, player, hand, stack) -> {
        if (!world.isClient) {
            int count = stack.getCount();
            stack.decrement(count);

            player.setStackInHand(hand, new ItemStack(Items.WHITE_CONCRETE_POWDER, count));
            player.incrementStat(Stats.USE_CAULDRON);
            LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
        }
        return ItemActionResult.success(world.isClient);
    };

    public static CauldronBehavior CLEAN_GLASS = (state, world, pos, player, hand, stack) -> {
        if (!world.isClient) {
            int count = stack.getCount();
            stack.decrement(count);

            player.setStackInHand(hand, new ItemStack(Items.GLASS, count));
            player.incrementStat(Stats.USE_CAULDRON);
            LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
        }
        return ItemActionResult.success(world.isClient);
    };

    public static CauldronBehavior CLEAN_GLASS_PANE = (state, world, pos, player, hand, stack) -> {
        if (!world.isClient) {
            int count = stack.getCount();
            stack.decrement(count);

            player.setStackInHand(hand, new ItemStack(Items.GLASS_PANE, count));
            player.incrementStat(Stats.USE_CAULDRON);
            LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
        }
        return ItemActionResult.success(world.isClient);
    };
}
