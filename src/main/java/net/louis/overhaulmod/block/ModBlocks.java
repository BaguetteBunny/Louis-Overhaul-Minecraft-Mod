package net.louis.overhaulmod.block;


import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.cauldron.ColoredWaterCauldronBlock;
import net.louis.overhaulmod.cauldron.DragonBreathCauldronBlock;
import net.louis.overhaulmod.cauldron.HoneyCauldronBlock;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block ADVANCED_FLETCHING_TABLE = registerBlock("advanced_fletching_table",
            new AdvancedFletchingTable(AbstractBlock.Settings.copy(Blocks.FLETCHING_TABLE)));

    public static final Block COPPER_RAIL = registerBlock("copper_rail",
            new PoweredRailBlock(AbstractBlock.Settings.create()
                    .strength(0.7f)
                    .sounds(BlockSoundGroup.METAL)
                    .noCollision()
            ));

    public static final Block SAWMILL = registerBlock("sawmill",
            new SawmillBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS)));

    public static final Block GLOW_LANTERN = registerBlock("glow_lantern",
            new LanternBlock(AbstractBlock.Settings.copy(Blocks.SOUL_LANTERN)));

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
    public static final Block CHISELED_BRICKS = registerBlock("chiseled_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.BRICKS)));
    public static final Block CHISELED_QUARTZ_BRICKS = registerBlock("chiseled_quartz_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.CHISELED_QUARTZ_BLOCK)));

    public static final Block RED_NETHER_BRICK_FENCE = registerBlock("red_nether_brick_fence",
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICKS)));


    // Concrete Family
    public static final Block WHITE_CONCRETE_STAIRS = registerBlock("white_concrete_stairs", new StairsBlock(Blocks.WHITE_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.WHITE_CONCRETE).mapColor(MapColor.WHITE)));
    public static final Block ORANGE_CONCRETE_STAIRS = registerBlock("orange_concrete_stairs", new StairsBlock(Blocks.ORANGE_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.ORANGE_CONCRETE).mapColor(MapColor.ORANGE)));
    public static final Block MAGENTA_CONCRETE_STAIRS = registerBlock("magenta_concrete_stairs", new StairsBlock(Blocks.MAGENTA_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.MAGENTA_CONCRETE).mapColor(MapColor.MAGENTA)));
    public static final Block LIGHT_BLUE_CONCRETE_STAIRS = registerBlock("light_blue_concrete_stairs", new StairsBlock(Blocks.LIGHT_BLUE_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_CONCRETE).mapColor(MapColor.LIGHT_BLUE)));
    public static final Block YELLOW_CONCRETE_STAIRS = registerBlock("yellow_concrete_stairs", new StairsBlock(Blocks.YELLOW_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.YELLOW_CONCRETE).mapColor(MapColor.YELLOW)));
    public static final Block LIME_CONCRETE_STAIRS = registerBlock("lime_concrete_stairs", new StairsBlock(Blocks.LIME_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.LIME_CONCRETE).mapColor(MapColor.LIME)));
    public static final Block PINK_CONCRETE_STAIRS = registerBlock("pink_concrete_stairs", new StairsBlock(Blocks.PINK_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PINK_CONCRETE).mapColor(MapColor.PINK)));
    public static final Block GRAY_CONCRETE_STAIRS = registerBlock("gray_concrete_stairs", new StairsBlock(Blocks.GRAY_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.GRAY_CONCRETE).mapColor(MapColor.GRAY)));
    public static final Block LIGHT_GRAY_CONCRETE_STAIRS = registerBlock("light_gray_concrete_stairs", new StairsBlock(Blocks.LIGHT_GRAY_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_CONCRETE).mapColor(MapColor.LIGHT_GRAY)));
    public static final Block CYAN_CONCRETE_STAIRS = registerBlock("cyan_concrete_stairs", new StairsBlock(Blocks.CYAN_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.CYAN_CONCRETE).mapColor(MapColor.CYAN)));
    public static final Block PURPLE_CONCRETE_STAIRS = registerBlock("purple_concrete_stairs", new StairsBlock(Blocks.PURPLE_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PURPLE_CONCRETE).mapColor(MapColor.PURPLE)));
    public static final Block BLUE_CONCRETE_STAIRS = registerBlock("blue_concrete_stairs", new StairsBlock(Blocks.BLUE_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLUE_CONCRETE).mapColor(MapColor.BLUE)));
    public static final Block BROWN_CONCRETE_STAIRS = registerBlock("brown_concrete_stairs", new StairsBlock(Blocks.BROWN_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BROWN_CONCRETE).mapColor(MapColor.BROWN)));
    public static final Block GREEN_CONCRETE_STAIRS = registerBlock("green_concrete_stairs", new StairsBlock(Blocks.GREEN_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.GREEN_CONCRETE).mapColor(MapColor.GREEN)));
    public static final Block RED_CONCRETE_STAIRS = registerBlock("red_concrete_stairs", new StairsBlock(Blocks.RED_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.RED_CONCRETE).mapColor(MapColor.RED)));
    public static final Block BLACK_CONCRETE_STAIRS = registerBlock("black_concrete_stairs", new StairsBlock(Blocks.BLACK_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE).mapColor(MapColor.BLACK)));

    public static final Block WHITE_CONCRETE_SLAB = registerBlock("white_concrete_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.WHITE_CONCRETE).mapColor(MapColor.WHITE)));
    public static final Block ORANGE_CONCRETE_SLAB = registerBlock("orange_concrete_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.ORANGE_CONCRETE).mapColor(MapColor.ORANGE)));
    public static final Block MAGENTA_CONCRETE_SLAB = registerBlock("magenta_concrete_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.MAGENTA_CONCRETE).mapColor(MapColor.MAGENTA)));
    public static final Block LIGHT_BLUE_CONCRETE_SLAB = registerBlock("light_blue_concrete_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_CONCRETE).mapColor(MapColor.LIGHT_BLUE)));
    public static final Block YELLOW_CONCRETE_SLAB = registerBlock("yellow_concrete_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.YELLOW_CONCRETE).mapColor(MapColor.YELLOW)));
    public static final Block LIME_CONCRETE_SLAB = registerBlock("lime_concrete_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.LIME_CONCRETE).mapColor(MapColor.LIME)));
    public static final Block PINK_CONCRETE_SLAB = registerBlock("pink_concrete_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PINK_CONCRETE).mapColor(MapColor.PINK)));
    public static final Block GRAY_CONCRETE_SLAB = registerBlock("gray_concrete_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRAY_CONCRETE).mapColor(MapColor.GRAY)));
    public static final Block LIGHT_GRAY_CONCRETE_SLAB = registerBlock("light_gray_concrete_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_CONCRETE).mapColor(MapColor.LIGHT_GRAY)));
    public static final Block CYAN_CONCRETE_SLAB = registerBlock("cyan_concrete_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CYAN_CONCRETE).mapColor(MapColor.CYAN)));
    public static final Block PURPLE_CONCRETE_SLAB = registerBlock("purple_concrete_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PURPLE_CONCRETE).mapColor(MapColor.PURPLE)));
    public static final Block BLUE_CONCRETE_SLAB = registerBlock("blue_concrete_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLUE_CONCRETE).mapColor(MapColor.BLUE)));
    public static final Block BROWN_CONCRETE_SLAB = registerBlock("brown_concrete_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BROWN_CONCRETE).mapColor(MapColor.BROWN)));
    public static final Block GREEN_CONCRETE_SLAB = registerBlock("green_concrete_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GREEN_CONCRETE).mapColor(MapColor.GREEN)));
    public static final Block RED_CONCRETE_SLAB = registerBlock("red_concrete_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_CONCRETE).mapColor(MapColor.RED)));
    public static final Block BLACK_CONCRETE_SLAB = registerBlock("black_concrete_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE).mapColor(MapColor.BLACK)));

    public static final Block WHITE_CONCRETE_WALL = registerBlock("white_concrete_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.WHITE_CONCRETE).mapColor(MapColor.WHITE)));
    public static final Block ORANGE_CONCRETE_WALL = registerBlock("orange_concrete_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.ORANGE_CONCRETE).mapColor(MapColor.ORANGE)));
    public static final Block MAGENTA_CONCRETE_WALL = registerBlock("magenta_concrete_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.MAGENTA_CONCRETE).mapColor(MapColor.MAGENTA)));
    public static final Block LIGHT_BLUE_CONCRETE_WALL = registerBlock("light_blue_concrete_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_CONCRETE).mapColor(MapColor.LIGHT_BLUE)));
    public static final Block YELLOW_CONCRETE_WALL = registerBlock("yellow_concrete_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.YELLOW_CONCRETE).mapColor(MapColor.YELLOW)));
    public static final Block LIME_CONCRETE_WALL = registerBlock("lime_concrete_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.LIME_CONCRETE).mapColor(MapColor.LIME)));
    public static final Block PINK_CONCRETE_WALL = registerBlock("pink_concrete_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PINK_CONCRETE).mapColor(MapColor.PINK)));
    public static final Block GRAY_CONCRETE_WALL = registerBlock("gray_concrete_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.GRAY_CONCRETE).mapColor(MapColor.GRAY)));
    public static final Block LIGHT_GRAY_CONCRETE_WALL = registerBlock("light_gray_concrete_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_CONCRETE).mapColor(MapColor.LIGHT_GRAY)));
    public static final Block CYAN_CONCRETE_WALL = registerBlock("cyan_concrete_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.CYAN_CONCRETE).mapColor(MapColor.CYAN)));
    public static final Block PURPLE_CONCRETE_WALL = registerBlock("purple_concrete_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PURPLE_CONCRETE).mapColor(MapColor.PURPLE)));
    public static final Block BLUE_CONCRETE_WALL = registerBlock("blue_concrete_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.BLUE_CONCRETE).mapColor(MapColor.BLUE)));
    public static final Block BROWN_CONCRETE_WALL = registerBlock("brown_concrete_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.BROWN_CONCRETE).mapColor(MapColor.BROWN)));
    public static final Block GREEN_CONCRETE_WALL = registerBlock("green_concrete_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.GREEN_CONCRETE).mapColor(MapColor.GREEN)));
    public static final Block RED_CONCRETE_WALL = registerBlock("red_concrete_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.RED_CONCRETE).mapColor(MapColor.RED)));
    public static final Block BLACK_CONCRETE_WALL = registerBlock("black_concrete_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.GREEN_CONCRETE).mapColor(MapColor.BLACK)));

    public static final Block HONEY_CAULDRON = registerBlock("honey_cauldron",
            new HoneyCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block COLORED_WATER_CAULDRON = registerBlock("colored_water_cauldron",
            new ColoredWaterCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block DRAGON_BREATH_CAULDRON = registerBlock("dragon_breath_cauldron",
            new DragonBreathCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)));

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
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(ModBlocks.ADVANCED_FLETCHING_TABLE);
            entries.add(ModBlocks.SAWMILL);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> {
            entries.add(ModBlocks.COPPER_RAIL);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.GLOW_LANTERN);

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
            entries.add(ModBlocks.CHISELED_BRICKS);
            entries.add(ModBlocks.CHISELED_QUARTZ_BRICKS);

            entries.add(ModBlocks.RED_NETHER_BRICK_FENCE);


            entries.add(ModBlocks.WHITE_CONCRETE_STAIRS);
            entries.add(ModBlocks.ORANGE_CONCRETE_STAIRS);
            entries.add(ModBlocks.MAGENTA_CONCRETE_STAIRS);
            entries.add(ModBlocks.LIGHT_BLUE_CONCRETE_STAIRS);
            entries.add(ModBlocks.YELLOW_CONCRETE_STAIRS);
            entries.add(ModBlocks.LIME_CONCRETE_STAIRS);
            entries.add(ModBlocks.PINK_CONCRETE_STAIRS);
            entries.add(ModBlocks.GRAY_CONCRETE_STAIRS);
            entries.add(ModBlocks.LIGHT_GRAY_CONCRETE_STAIRS);
            entries.add(ModBlocks.CYAN_CONCRETE_STAIRS);
            entries.add(ModBlocks.PURPLE_CONCRETE_STAIRS);
            entries.add(ModBlocks.BLUE_CONCRETE_STAIRS);
            entries.add(ModBlocks.BROWN_CONCRETE_STAIRS);
            entries.add(ModBlocks.GREEN_CONCRETE_STAIRS);
            entries.add(ModBlocks.RED_CONCRETE_STAIRS);
            entries.add(ModBlocks.BLACK_CONCRETE_STAIRS);

            entries.add(ModBlocks.WHITE_CONCRETE_SLAB);
            entries.add(ModBlocks.ORANGE_CONCRETE_SLAB);
            entries.add(ModBlocks.MAGENTA_CONCRETE_SLAB);
            entries.add(ModBlocks.LIGHT_BLUE_CONCRETE_SLAB);
            entries.add(ModBlocks.YELLOW_CONCRETE_SLAB);
            entries.add(ModBlocks.LIME_CONCRETE_SLAB);
            entries.add(ModBlocks.PINK_CONCRETE_SLAB);
            entries.add(ModBlocks.GRAY_CONCRETE_SLAB);
            entries.add(ModBlocks.LIGHT_GRAY_CONCRETE_SLAB);
            entries.add(ModBlocks.CYAN_CONCRETE_SLAB);
            entries.add(ModBlocks.PURPLE_CONCRETE_SLAB);
            entries.add(ModBlocks.BLUE_CONCRETE_SLAB);
            entries.add(ModBlocks.BROWN_CONCRETE_SLAB);
            entries.add(ModBlocks.GREEN_CONCRETE_SLAB);
            entries.add(ModBlocks.RED_CONCRETE_SLAB);
            entries.add(ModBlocks.BLACK_CONCRETE_SLAB);

            entries.add(ModBlocks.WHITE_CONCRETE_WALL);
            entries.add(ModBlocks.ORANGE_CONCRETE_WALL);
            entries.add(ModBlocks.MAGENTA_CONCRETE_WALL);
            entries.add(ModBlocks.LIGHT_BLUE_CONCRETE_WALL);
            entries.add(ModBlocks.YELLOW_CONCRETE_WALL);
            entries.add(ModBlocks.LIME_CONCRETE_WALL);
            entries.add(ModBlocks.PINK_CONCRETE_WALL);
            entries.add(ModBlocks.GRAY_CONCRETE_WALL);
            entries.add(ModBlocks.LIGHT_GRAY_CONCRETE_WALL);
            entries.add(ModBlocks.CYAN_CONCRETE_WALL);
            entries.add(ModBlocks.PURPLE_CONCRETE_WALL);
            entries.add(ModBlocks.BLUE_CONCRETE_WALL);
            entries.add(ModBlocks.BROWN_CONCRETE_WALL);
            entries.add(ModBlocks.GREEN_CONCRETE_WALL);
            entries.add(ModBlocks.RED_CONCRETE_WALL);
            entries.add(ModBlocks.BLACK_CONCRETE_WALL);
        });

    }
}
