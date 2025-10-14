package net.louis.overhaulmod.item;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Map;

public class AmethystDagger extends PickaxeItem {
    public AmethystDagger(Settings settings) {
        super(ModToolMaterials.AMETHYST, settings
                .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.AMETHYST, 2, -0.5f))
        );
    }

    private static final Map<Block, Block> STRIPPABLES = Map.ofEntries(
            Map.entry(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG),
            Map.entry(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG),
            Map.entry(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG),
            Map.entry(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG),
            Map.entry(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG),
            Map.entry(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG),
            Map.entry(Blocks.MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG),
            Map.entry(Blocks.CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG),
            Map.entry(Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM),
            Map.entry(Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM),

            Map.entry(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD),
            Map.entry(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD),
            Map.entry(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD),
            Map.entry(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD),
            Map.entry(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD),
            Map.entry(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD),
            Map.entry(Blocks.MANGROVE_WOOD, Blocks.STRIPPED_MANGROVE_WOOD),
            Map.entry(Blocks.CHERRY_WOOD, Blocks.STRIPPED_CHERRY_WOOD),
            Map.entry(Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE),
            Map.entry(Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE)
    );

    private static final Map<Block, Block> DEFROSTABLES = Map.ofEntries(
            Map.entry(Blocks.BLUE_ICE, Blocks.PACKED_ICE),
            Map.entry(Blocks.PACKED_ICE, Blocks.ICE)
    );

    private static final Map<Block, Block> SAPPLINGABLES = Map.ofEntries(
            Map.entry(Blocks.OAK_LEAVES, Blocks.OAK_SAPLING),
            Map.entry(Blocks.BIRCH_LEAVES, Blocks.BIRCH_SAPLING),
            Map.entry(Blocks.SPRUCE_LEAVES, Blocks.SPRUCE_SAPLING),
            Map.entry(Blocks.JUNGLE_LEAVES, Blocks.JUNGLE_SAPLING),
            Map.entry(Blocks.ACACIA_LEAVES, Blocks.ACACIA_SAPLING),
            Map.entry(Blocks.DARK_OAK_LEAVES, Blocks.DARK_OAK_SAPLING),
            Map.entry(Blocks.MANGROVE_LEAVES, Blocks.MANGROVE_PROPAGULE),
            Map.entry(Blocks.CHERRY_LEAVES, Blocks.CHERRY_SAPLING),
            Map.entry(Blocks.AZALEA_LEAVES, Blocks.AZALEA),
            Map.entry(Blocks.FLOWERING_AZALEA_LEAVES, Blocks.FLOWERING_AZALEA)
    );

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack daggerStack = user.getStackInHand(hand);
        ItemStack offHand = user.getOffHandStack();

        if (hand == Hand.MAIN_HAND && !offHand.isEmpty()) {
            Block offhandBlock = Block.getBlockFromItem(offHand.getItem());

            if (STRIPPABLES.containsKey(offhandBlock)) {
                Block newBlock = STRIPPABLES.get(offhandBlock);
                offHand.decrement(1);
                user.getInventory().offerOrDrop(new ItemStack(newBlock));
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.ITEM_AXE_STRIP, SoundCategory.PLAYERS, 1.0F, 1.0F);
                daggerStack.damage(1, user, EquipmentSlot.MAINHAND);

                return TypedActionResult.success(daggerStack, world.isClient());
            }

            if (DEFROSTABLES.containsKey(offhandBlock)) {
                Block newBlock = DEFROSTABLES.get(offhandBlock);
                offHand.decrement(1);
                user.getInventory().offerOrDrop(new ItemStack(newBlock, 8));
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1.0F, 2.0F);
                daggerStack.damage(2, user, EquipmentSlot.MAINHAND);

                return TypedActionResult.success(daggerStack, world.isClient());
            }

            if (SAPPLINGABLES.containsKey(offhandBlock)) {
                Block newBlock = SAPPLINGABLES.get(offhandBlock);
                offHand.decrement(1);
                if (world.random.nextFloat() < 0.02) {
                    user.getInventory().offerOrDrop(new ItemStack(Items.APPLE, 1));
                }
                if (world.random.nextFloat() < 0.55) {
                    user.getInventory().offerOrDrop(new ItemStack(newBlock, 1));
                }

                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.BLOCK_CHERRY_LEAVES_BREAK, SoundCategory.BLOCKS, 1.0F, 2.0F);
                daggerStack.damage(1, user, EquipmentSlot.MAINHAND);

                return TypedActionResult.success(daggerStack, world.isClient());
            }

            if (offhandBlock.equals(Blocks.CRYING_OBSIDIAN)) {
                offHand.decrement(1);
                user.getInventory().offerOrDrop(new ItemStack(Items.OBSIDIAN, 1));
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.BLOCK_CANDLE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 2.0F);
                daggerStack.damage(1, user, EquipmentSlot.MAINHAND);

                return TypedActionResult.success(daggerStack, world.isClient());
            }
        }

        return super.use(world, user, hand);
    }
}