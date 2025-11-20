package net.louis.overhaulmod.enchantments.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;

public record SmeltingEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<SmeltingEnchantmentEffect> CODEC = MapCodec.unit(SmeltingEnchantmentEffect::new);

    public static final Map<Block, Item> TRANSFORM_MAP = new HashMap<>();

    static {
        TRANSFORM_MAP.put(Blocks.IRON_ORE, Items.IRON_INGOT);
        TRANSFORM_MAP.put(Blocks.GOLD_ORE, Items.GOLD_INGOT);
        TRANSFORM_MAP.put(Blocks.COPPER_ORE, Items.COPPER_INGOT);
        TRANSFORM_MAP.put(Blocks.DEEPSLATE_IRON_ORE, Items.IRON_INGOT);
        TRANSFORM_MAP.put(Blocks.DEEPSLATE_GOLD_ORE, Items.GOLD_INGOT);
        TRANSFORM_MAP.put(Blocks.DEEPSLATE_COPPER_ORE, Items.COPPER_INGOT);
        TRANSFORM_MAP.put(Blocks.RAW_IRON_BLOCK, Items.IRON_BLOCK);
        TRANSFORM_MAP.put(Blocks.RAW_GOLD_BLOCK, Items.GOLD_BLOCK);
        TRANSFORM_MAP.put(Blocks.RAW_COPPER_BLOCK, Items.COPPER_BLOCK);
        TRANSFORM_MAP.put(Blocks.ANCIENT_DEBRIS, Items.NETHERITE_SCRAP);
        TRANSFORM_MAP.put(Blocks.SEA_PICKLE, Items.LIME_DYE);
        TRANSFORM_MAP.put(Blocks.CACTUS, Items.GREEN_DYE);

        TRANSFORM_MAP.put(Blocks.CLAY, Items.TERRACOTTA);
        TRANSFORM_MAP.put(Blocks.WET_SPONGE, Items.SPONGE);
        TRANSFORM_MAP.put(Blocks.NETHERRACK, Items.NETHER_BRICKS);

        TRANSFORM_MAP.put(Blocks.POTATOES, Items.BAKED_POTATO);
        TRANSFORM_MAP.put(Blocks.KELP, Items.DRIED_KELP);
        TRANSFORM_MAP.put(Blocks.KELP_PLANT, Items.DRIED_KELP);

        TRANSFORM_MAP.put(Blocks.OAK_LOG, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.SPRUCE_LOG, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.BIRCH_LOG, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.JUNGLE_LOG, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.ACACIA_LOG, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.DARK_OAK_LOG, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.MANGROVE_LOG, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.CHERRY_LOG, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.BAMBOO_BLOCK, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.STRIPPED_OAK_LOG, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.STRIPPED_SPRUCE_LOG, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.STRIPPED_BIRCH_LOG, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.STRIPPED_JUNGLE_LOG, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.STRIPPED_ACACIA_LOG, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.STRIPPED_DARK_OAK_LOG, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.STRIPPED_MANGROVE_LOG, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.STRIPPED_CHERRY_LOG, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.STRIPPED_BAMBOO_BLOCK, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.OAK_WOOD, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.SPRUCE_WOOD, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.BIRCH_WOOD, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.JUNGLE_WOOD, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.ACACIA_WOOD, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.DARK_OAK_WOOD, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.MANGROVE_WOOD, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.CHERRY_WOOD, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.STRIPPED_OAK_WOOD, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.STRIPPED_SPRUCE_WOOD, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.STRIPPED_BIRCH_WOOD, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.STRIPPED_JUNGLE_WOOD, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.STRIPPED_ACACIA_WOOD, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.STRIPPED_DARK_OAK_WOOD, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.STRIPPED_MANGROVE_WOOD, Items.CHARCOAL);
        TRANSFORM_MAP.put(Blocks.STRIPPED_CHERRY_WOOD, Items.CHARCOAL);
    }

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
