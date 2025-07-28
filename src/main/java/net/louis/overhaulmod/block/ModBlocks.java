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
            entries.add(ModBlocks.LAVENDER_QUARTZ_COLUMN);
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
        });

    }
}
