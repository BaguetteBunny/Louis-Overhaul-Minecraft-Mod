package net.louis.overhaulmod.item;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
            Map.entry(Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM)
    );

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack daggerStack = user.getStackInHand(hand);
        ItemStack offHand = user.getOffHandStack();

        if (hand == Hand.MAIN_HAND && !offHand.isEmpty()) {
            Block offhandBlock = Block.getBlockFromItem(offHand.getItem());

            if (STRIPPABLES.containsKey(offhandBlock)) {
                Block stripped = STRIPPABLES.get(offhandBlock);
                offHand.decrement(1);
                user.getInventory().offerOrDrop(new ItemStack(stripped));
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.ITEM_AXE_STRIP, SoundCategory.PLAYERS, 1.0F, 1.0F);

                daggerStack.damage(1, user, EquipmentSlot.MAINHAND);

                return TypedActionResult.success(daggerStack, world.isClient());
            }
        }

        return super.use(world, user, hand);
    }
}