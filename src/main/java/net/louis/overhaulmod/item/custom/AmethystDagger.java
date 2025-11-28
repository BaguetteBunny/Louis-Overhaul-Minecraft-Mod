package net.louis.overhaulmod.item.custom;

import net.louis.overhaulmod.utils.enums.ModToolMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Set;

public class AmethystDagger extends PickaxeItem {
    public AmethystDagger(Settings settings) {
        super(ModToolMaterials.AMETHYST, settings
                .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.AMETHYST, 2, -0.5f))
        );
    }

    private static final Map<Block, Block> DEFROSTABLES = Map.ofEntries(
            Map.entry(Blocks.BLUE_ICE, Blocks.PACKED_ICE),
            Map.entry(Blocks.PACKED_ICE, Blocks.ICE)
    );

    public static final Set<Block> UNDROPPABLES = Set.of(
            Blocks.GLASS,
            Blocks.BLACK_STAINED_GLASS,
            Blocks.BLUE_STAINED_GLASS,
            Blocks.BROWN_STAINED_GLASS,
            Blocks.CYAN_STAINED_GLASS,
            Blocks.GRAY_STAINED_GLASS,
            Blocks.GREEN_STAINED_GLASS,
            Blocks.LIGHT_BLUE_STAINED_GLASS,
            Blocks.LIGHT_GRAY_STAINED_GLASS,
            Blocks.LIME_STAINED_GLASS,
            Blocks.MAGENTA_STAINED_GLASS,
            Blocks.ORANGE_STAINED_GLASS,
            Blocks.PINK_STAINED_GLASS,
            Blocks.PURPLE_STAINED_GLASS,
            Blocks.RED_STAINED_GLASS,
            Blocks.WHITE_STAINED_GLASS,
            Blocks.YELLOW_STAINED_GLASS,
            Blocks.GLASS_PANE,
            Blocks.BLACK_STAINED_GLASS_PANE,
            Blocks.BLUE_STAINED_GLASS_PANE,
            Blocks.BROWN_STAINED_GLASS_PANE,
            Blocks.CYAN_STAINED_GLASS_PANE,
            Blocks.GRAY_STAINED_GLASS_PANE,
            Blocks.GREEN_STAINED_GLASS_PANE,
            Blocks.LIGHT_BLUE_STAINED_GLASS_PANE,
            Blocks.LIGHT_GRAY_STAINED_GLASS_PANE,
            Blocks.LIME_STAINED_GLASS_PANE,
            Blocks.MAGENTA_STAINED_GLASS_PANE,
            Blocks.ORANGE_STAINED_GLASS_PANE,
            Blocks.PINK_STAINED_GLASS_PANE,
            Blocks.PURPLE_STAINED_GLASS_PANE,
            Blocks.RED_STAINED_GLASS_PANE,
            Blocks.WHITE_STAINED_GLASS_PANE,
            Blocks.YELLOW_STAINED_GLASS_PANE
    );

    public static final Set<Block> DROPPABLES = Set.of(
            Blocks.SKELETON_SKULL,
            Blocks.SKELETON_WALL_SKULL,
            Blocks.WITHER_SKELETON_SKULL,
            Blocks.WITHER_SKELETON_WALL_SKULL,
            Blocks.ZOMBIE_HEAD,
            Blocks.ZOMBIE_WALL_HEAD,
            Blocks.CREEPER_HEAD,
            Blocks.CREEPER_WALL_HEAD,
            Blocks.DRAGON_HEAD,
            Blocks.DRAGON_WALL_HEAD,
            Blocks.PIGLIN_HEAD,
            Blocks.PIGLIN_WALL_HEAD,
            Blocks.PLAYER_HEAD,
            Blocks.PLAYER_WALL_HEAD,

            Blocks.HEAVY_CORE,
            Blocks.TINTED_GLASS
    );

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack daggerStack = user.getMainHandStack();
        ItemStack offHand = user.getOffHandStack();

        if (offHand.isEmpty() || world.isClient()) return TypedActionResult.pass(daggerStack);

        boolean success = true;
        Block offhandBlock = Block.getBlockFromItem(offHand.getItem());

        if (DEFROSTABLES.containsKey(offhandBlock)) {
            Block newBlock = DEFROSTABLES.get(offhandBlock);
            user.getInventory().offerOrDrop(new ItemStack(newBlock, 8));
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1.0F, 2.0F);
            daggerStack.damage(3, user, EquipmentSlot.MAINHAND);
        }

        else if (offHand.getItem().equals(Items.FILLED_MAP)) {
            user.getInventory().offerOrDrop(new ItemStack(Items.MAP, 1));
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, SoundCategory.BLOCKS, 1.0F, 2.0F);
        }

        else if (offHand.getItem().equals(Items.WRITTEN_BOOK)) {
            user.getInventory().offerOrDrop(new ItemStack(Items.WRITABLE_BOOK, 1));
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, SoundCategory.BLOCKS, 1.0F, 2.0F);
        }

        else if (offHand.getItem().equals(Items.PLAYER_HEAD)) {
            user.getInventory().offerOrDrop(new ItemStack(Items.PLAYER_HEAD, 1));
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, SoundCategory.BLOCKS, 1.0F, 2.0F);
        }

        else success = false;


        if (success) {
            offHand.decrement(1);
            daggerStack.damage(1, user, EquipmentSlot.MAINHAND);
            user.swingHand(Hand.MAIN_HAND, true);
            return TypedActionResult.success(daggerStack, world.isClient());
        }

        return TypedActionResult.pass(daggerStack);
    }
}