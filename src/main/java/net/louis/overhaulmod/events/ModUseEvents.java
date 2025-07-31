package net.louis.overhaulmod.events;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.Map;

public class ModUseEvents {

    public static void register() {
        UseBlockCallback.EVENT.register(ModUseEvents::oxidizeCopperWithClock);
    }

    private static ActionResult oxidizeCopperWithClock(PlayerEntity player, World world, net.minecraft.util.Hand hand, BlockHitResult hitResult) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient && stack.getItem() == Items.CLOCK) {
            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();

            Map<Block, Block> oxidationMap = Map.ofEntries(
                    Map.entry(Blocks.COPPER_BLOCK, Blocks.EXPOSED_COPPER),
                    Map.entry(Blocks.EXPOSED_COPPER, Blocks.WEATHERED_COPPER),
                    Map.entry(Blocks.WEATHERED_COPPER, Blocks.OXIDIZED_COPPER),

                    Map.entry(Blocks.COPPER_BULB, Blocks.EXPOSED_COPPER_BULB),
                    Map.entry(Blocks.EXPOSED_COPPER_BULB, Blocks.WEATHERED_COPPER_BULB),
                    Map.entry(Blocks.WEATHERED_COPPER_BULB, Blocks.OXIDIZED_COPPER_BULB),

                    Map.entry(Blocks.COPPER_DOOR, Blocks.EXPOSED_COPPER_DOOR),
                    Map.entry(Blocks.EXPOSED_COPPER_DOOR, Blocks.WEATHERED_COPPER_DOOR),
                    Map.entry(Blocks.WEATHERED_COPPER_DOOR, Blocks.OXIDIZED_COPPER_DOOR),

                    Map.entry(Blocks.COPPER_GRATE, Blocks.EXPOSED_COPPER_GRATE),
                    Map.entry(Blocks.EXPOSED_COPPER_GRATE, Blocks.WEATHERED_COPPER_GRATE),
                    Map.entry(Blocks.WEATHERED_COPPER_GRATE, Blocks.OXIDIZED_COPPER_GRATE),

                    Map.entry(Blocks.COPPER_TRAPDOOR, Blocks.EXPOSED_COPPER_TRAPDOOR),
                    Map.entry(Blocks.EXPOSED_COPPER_TRAPDOOR, Blocks.WEATHERED_COPPER_TRAPDOOR),
                    Map.entry(Blocks.WEATHERED_COPPER_TRAPDOOR, Blocks.OXIDIZED_COPPER_TRAPDOOR),

                    Map.entry(Blocks.CUT_COPPER_SLAB, Blocks.EXPOSED_CUT_COPPER_SLAB),
                    Map.entry(Blocks.EXPOSED_CUT_COPPER_SLAB, Blocks.WEATHERED_CUT_COPPER_SLAB),
                    Map.entry(Blocks.WEATHERED_CUT_COPPER_SLAB, Blocks.OXIDIZED_CUT_COPPER_SLAB),

                    Map.entry(Blocks.CUT_COPPER_STAIRS, Blocks.EXPOSED_CUT_COPPER_STAIRS),
                    Map.entry(Blocks.EXPOSED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER_STAIRS),
                    Map.entry(Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.OXIDIZED_CUT_COPPER_STAIRS),

                    Map.entry(Blocks.CHISELED_COPPER, Blocks.EXPOSED_CHISELED_COPPER),
                    Map.entry(Blocks.EXPOSED_CHISELED_COPPER, Blocks.WEATHERED_CHISELED_COPPER),
                    Map.entry(Blocks.WEATHERED_CHISELED_COPPER, Blocks.OXIDIZED_CHISELED_COPPER),

                    Map.entry(Blocks.CUT_COPPER, Blocks.EXPOSED_CUT_COPPER),
                    Map.entry(Blocks.EXPOSED_CUT_COPPER, Blocks.WEATHERED_CUT_COPPER),
                    Map.entry(Blocks.WEATHERED_CUT_COPPER, Blocks.OXIDIZED_CUT_COPPER)
            );

            BlockState clickedState = world.getBlockState(pos);
            Block clickedBlock = clickedState.getBlock();

            if (oxidationMap.containsKey(clickedBlock)) {
                if (clickedBlock instanceof DoorBlock && clickedState.get(DoorBlock.HALF) == DoubleBlockHalf.UPPER) {
                    return ActionResult.PASS;
                }
            }

            if (oxidationMap.containsKey(block)) {
                Block nextStage = oxidationMap.get(block);
                BlockState newState = nextStage.getDefaultState();

                if (state.contains(Properties.WATERLOGGED)) {
                    newState = newState.with(Properties.WATERLOGGED, state.get(Properties.WATERLOGGED));
                }

                world.setBlockState(pos, newState);
                if (Random.create().nextInt(20) == 0) {
                    world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_DESTROY, SoundCategory.BLOCKS, 1f, 2f);
                    stack.decrement(1);
                } else {
                    world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 1f, 2f);
                }

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }
}