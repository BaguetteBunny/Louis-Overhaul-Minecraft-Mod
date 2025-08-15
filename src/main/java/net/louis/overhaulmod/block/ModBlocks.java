package net.louis.overhaulmod.block;


import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block COPPER_RAIL = registerBlock("copper_rail",
            new PoweredRailBlock(AbstractBlock.Settings.create()
                    .strength(0.7f)
                    .sounds(BlockSoundGroup.METAL)
                    .noCollision()
            ));

    // ROSE QUARTZ FAMILY
    public static final Block ROSE_QUARTZ_BRICKS = registerBlock("rose_quartz_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK).mapColor(MapColor.PINK)));
    public static final Block ROSE_QUARTZ_PILLAR = registerBlock("rose_quartz_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_PILLAR).mapColor(MapColor.PINK)));
    public static final Block ROSE_QUARTZ_COLUMN = registerBlock("rose_quartz_column",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_PILLAR).mapColor(MapColor.PINK)));
    public static final Block CHISELED_ROSE_QUARTZ = registerBlock("chiseled_rose_quartz",
            new Block(AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK).mapColor(MapColor.PINK)));
    public static final Block ROSE_QUARTZ_SLAB = registerBlock("rose_quartz_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_SLAB).mapColor(MapColor.PINK)));
    public static final Block ROSE_QUARTZ_STAIRS = registerBlock("rose_quartz_stairs",
            new StairsBlock(ModBlocks.ROSE_QUARTZ_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.QUARTZ_STAIRS).mapColor(MapColor.PINK)));
    public static final Block ROSE_QUARTZ_WALL = registerBlock("rose_quartz_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.PINK)));

    // QUARTZ BRICK FAMILY
    public static final Block QUARTZ_BRICKS_SLAB = registerBlock("quartz_bricks_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_SLAB)));
    public static final Block QUARTZ_BRICKS_STAIRS = registerBlock("quartz_bricks_stairs",
            new StairsBlock(Blocks.QUARTZ_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.QUARTZ_STAIRS)));
    public static final Block QUARTZ_BRICKS_WALL = registerBlock("quartz_bricks_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.WHITE)));

    // CALCITE FAMILY
    public static final Block CALCITE_SLAB = registerBlock("calcite_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_SLAB)));
    public static final Block CALCITE_STAIRS = registerBlock("calcite_stairs",
            new StairsBlock(Blocks.QUARTZ_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.QUARTZ_STAIRS)));
    public static final Block CALCITE_WALL = registerBlock("calcite_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.WHITE)));

    // MISSING WALL FAMILY
    public static final Block PURPUR_WALL = registerBlock("purpur_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.PINK)));
    public static final Block QUARTZ_WALL = registerBlock("quartz_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.WHITE)));
    public static final Block SMOOTH_QUARTZ_WALL = registerBlock("smooth_quartz_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.WHITE)));
    public static final Block POLISHED_ANDESITE_WALL = registerBlock("polished_andesite_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.LIGHT_GRAY)));
    public static final Block POLISHED_GRANITE_WALL = registerBlock("polished_granite_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.RAW_IRON_PINK)));
    public static final Block POLISHED_DIORITE_WALL = registerBlock("polished_diorite_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.WHITE)));
    public static final Block CUT_RED_SANDSTONE_WALL = registerBlock("cut_red_sandstone_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block SMOOTH_RED_SANDSTONE_WALL = registerBlock("smooth_red_sandstone_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block CUT_SANDSTONE_WALL = registerBlock("cut_sandstone_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.PALE_YELLOW)));
    public static final Block SMOOTH_SANDSTONE_WALL = registerBlock("smooth_sandstone_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.PALE_YELLOW)));
    public static final Block PRISMARINE_BRICKS_WALL = registerBlock("prismarine_bricks_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.LIGHT_BLUE)));
    public static final Block PRISMARINE_WALL = registerBlock("prismarine_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.LIGHT_BLUE)));
    public static final Block DARK_PRISMARINE_WALL = registerBlock("dark_prismarine_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.TERRACOTTA_CYAN)));
    public static final Block SMOOTH_STONE_WALL = registerBlock("smooth_stone_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.STONE_GRAY)));
    public static final Block STONE_WALL = registerBlock("stone_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.STONE_GRAY)));

    // MISSING STAIRS FAMILY
    public static final Block CUT_SANDSTONE_STAIRS = registerBlock("cut_sandstone_stairs",
            new StairsBlock(Blocks.CUT_SANDSTONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.CUT_SANDSTONE).mapColor(MapColor.PALE_YELLOW)));
    public static final Block CUT_RED_SANDSTONE_STAIRS = registerBlock("cut_red_sandstone_stairs",
            new StairsBlock(Blocks.CUT_RED_SANDSTONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.CUT_RED_SANDSTONE).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block SMOOTH_STONE_STAIRS = registerBlock("smooth_stone_stairs",
            new StairsBlock(Blocks.SMOOTH_STONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.SMOOTH_STONE).mapColor(MapColor.STONE_GRAY)));

    // LAVENDER QUARTZ FAMILY
    public static final Block LAVENDER_QUARTZ_BRICKS = registerBlock("lavender_quartz_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK).mapColor(MapColor.PALE_PURPLE)));
    public static final Block LAVENDER_QUARTZ_PILLAR = registerBlock("lavender_quartz_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_PILLAR).mapColor(MapColor.PALE_PURPLE)));
    public static final Block LAVENDER_QUARTZ_COLUMN = registerBlock("lavender_quartz_column",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_PILLAR).mapColor(MapColor.PALE_PURPLE)));
    public static final Block CHISELED_LAVENDER_QUARTZ = registerBlock("chiseled_lavender_quartz",
            new Block(AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK).mapColor(MapColor.PALE_PURPLE)));
    public static final Block LAVENDER_QUARTZ_SLAB = registerBlock("lavender_quartz_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_SLAB).mapColor(MapColor.PALE_PURPLE)));
    public static final Block LAVENDER_QUARTZ_STAIRS = registerBlock("lavender_quartz_stairs",
            new StairsBlock(ModBlocks.LAVENDER_QUARTZ_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.QUARTZ_STAIRS).mapColor(MapColor.PALE_PURPLE)));
    public static final Block LAVENDER_QUARTZ_WALL = registerBlock("lavender_quartz_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.PALE_PURPLE)));

    // CRACKED BRICKS FAMILY
    public static final Block CRACKED_BRICKS = registerBlock("cracked_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.BRICKS)));
    public static final Block CRACKED_END_STONE_BRICKS = registerBlock("cracked_end_stone_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICKS)));
    public static final Block CRACKED_MOSSY_STONE_BRICKS = registerBlock("cracked_mossy_stone_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.MOSSY_STONE_BRICKS)));
    public static final Block CRACKED_MUD_BRICKS = registerBlock("cracked_mud_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));
    public static final Block CRACKED_PRISMARINE_BRICKS = registerBlock("cracked_prismarine_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE_BRICKS)));
    public static final Block CRACKED_QUARTZ_BRICKS = registerBlock("cracked_quartz_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS)));
    public static final Block CRACKED_RED_NETHER_BRICKS = registerBlock("cracked_red_nether_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICKS)));
    public static final Block CRACKED_TUFF_BRICKS = registerBlock("cracked_tuff_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.TUFF_BRICKS)));

    // CHISELED BRICKS FAMILY
    public static final Block CHISELED_DARK_PRISMARINE = registerBlock("chiseled_dark_prismarine",
            new Block(AbstractBlock.Settings.copy(Blocks.DARK_PRISMARINE)));
    public static final Block CHISELED_DEEPSLATE_BRICKS = registerBlock("chiseled_deepslate_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_BRICKS)));
    public static final Block CHISELED_END_STONE_BRICKS = registerBlock("chiseled_end_stone_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICKS)));
    public static final Block CHISELED_MOSSY_STONE_BRICKS = registerBlock("chiseled_mossy_stone_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.MOSSY_STONE_BRICKS)));
    public static final Block CHISELED_MUD_BRICKS = registerBlock("chiseled_mud_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));
    public static final Block CHISELED_PRISMARINE = registerBlock("chiseled_prismarine",
            new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
    public static final Block CHISELED_PRISMARINE_BRICKS = registerBlock("chiseled_prismarine_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE_BRICKS)));
    public static final Block CHISELED_PURPUR = registerBlock("chiseled_purpur",
            new Block(AbstractBlock.Settings.copy(Blocks.PURPUR_BLOCK)));
    public static final Block CHISELED_RED_NETHER_BRICKS = registerBlock("chiseled_red_nether_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICKS)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(LouisOverhaulMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(LouisOverhaulMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));

    }
    public static void registerModBlocks() {
        LouisOverhaulMod.LOGGER.info("Registering Mod Blocks for " + LouisOverhaulMod.MOD_ID);

        // Add Group
        // entries.add(ModBlocks.);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> {
            entries.add(ModBlocks.COPPER_RAIL);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.ROSE_QUARTZ_BRICKS);
            entries.add(ModBlocks.ROSE_QUARTZ_PILLAR);
            entries.add(ModBlocks.ROSE_QUARTZ_COLUMN);
            entries.add(ModBlocks.CHISELED_ROSE_QUARTZ);
            entries.add(ModBlocks.ROSE_QUARTZ_SLAB);
            entries.add(ModBlocks.ROSE_QUARTZ_STAIRS);
            entries.add(ModBlocks.ROSE_QUARTZ_WALL);

            entries.add(ModBlocks.LAVENDER_QUARTZ_BRICKS);
            entries.add(ModBlocks.LAVENDER_QUARTZ_PILLAR);
            entries.add(ModBlocks.LAVENDER_QUARTZ_COLUMN);
            entries.add(ModBlocks.CHISELED_LAVENDER_QUARTZ);
            entries.add(ModBlocks.LAVENDER_QUARTZ_SLAB);
            entries.add(ModBlocks.LAVENDER_QUARTZ_STAIRS);
            entries.add(ModBlocks.LAVENDER_QUARTZ_WALL);

            entries.add(ModBlocks.CRACKED_BRICKS);
            entries.add(ModBlocks.CRACKED_QUARTZ_BRICKS);
            entries.add(ModBlocks.CRACKED_RED_NETHER_BRICKS);
            entries.add(ModBlocks.CRACKED_TUFF_BRICKS);
            entries.add(ModBlocks.CRACKED_MUD_BRICKS);
            entries.add(ModBlocks.CRACKED_END_STONE_BRICKS);
            entries.add(ModBlocks.CRACKED_PRISMARINE_BRICKS);
            entries.add(ModBlocks.CRACKED_MOSSY_STONE_BRICKS);

            entries.add(ModBlocks.QUARTZ_BRICKS_SLAB);
            entries.add(ModBlocks.QUARTZ_BRICKS_STAIRS);
            entries.add(ModBlocks.QUARTZ_BRICKS_WALL);

            entries.add(ModBlocks.CALCITE_SLAB);
            entries.add(ModBlocks.CALCITE_STAIRS);
            entries.add(ModBlocks.CALCITE_WALL);

            entries.add(ModBlocks.SMOOTH_STONE_STAIRS);
            entries.add(ModBlocks.CUT_RED_SANDSTONE_STAIRS);
            entries.add(ModBlocks.CUT_SANDSTONE_STAIRS);

            entries.add(ModBlocks.PURPUR_WALL);
            entries.add(ModBlocks.QUARTZ_WALL);
            entries.add(ModBlocks.SMOOTH_QUARTZ_WALL);
            entries.add(ModBlocks.POLISHED_ANDESITE_WALL);
            entries.add(ModBlocks.POLISHED_GRANITE_WALL);
            entries.add(ModBlocks.POLISHED_DIORITE_WALL);
            entries.add(ModBlocks.CUT_RED_SANDSTONE_WALL);
            entries.add(ModBlocks.SMOOTH_RED_SANDSTONE_WALL);
            entries.add(ModBlocks.CUT_SANDSTONE_WALL);
            entries.add(ModBlocks.SMOOTH_SANDSTONE_WALL);
            entries.add(ModBlocks.PRISMARINE_BRICKS_WALL);
            entries.add(ModBlocks.PRISMARINE_WALL);
            entries.add(ModBlocks.DARK_PRISMARINE_WALL);
            entries.add(ModBlocks.SMOOTH_STONE_WALL);
            entries.add(ModBlocks.STONE_WALL);

            entries.add(ModBlocks.CHISELED_DARK_PRISMARINE);
            entries.add(ModBlocks.CHISELED_PURPUR);
            entries.add(ModBlocks.CHISELED_DEEPSLATE_BRICKS);
            entries.add(ModBlocks.CHISELED_END_STONE_BRICKS);
            entries.add(ModBlocks.CHISELED_MUD_BRICKS);
            entries.add(ModBlocks.CHISELED_RED_NETHER_BRICKS);
            entries.add(ModBlocks.CHISELED_MOSSY_STONE_BRICKS);
            entries.add(ModBlocks.CHISELED_PRISMARINE);
            entries.add(ModBlocks.CHISELED_PRISMARINE_BRICKS);
        });

    }
}
