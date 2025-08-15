package net.louis.overhaulmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.louis.overhaulmod.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // .add(ModBlocks.)
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.COPPER_RAIL)

                .add(ModBlocks.ROSE_QUARTZ_BRICKS)
                .add(ModBlocks.CHISELED_ROSE_QUARTZ)
                .add(ModBlocks.ROSE_QUARTZ_PILLAR)
                .add(ModBlocks.ROSE_QUARTZ_COLUMN)
                .add(ModBlocks.ROSE_QUARTZ_SLAB)
                .add(ModBlocks.ROSE_QUARTZ_STAIRS)
                .add(ModBlocks.ROSE_QUARTZ_WALL)

                .add(ModBlocks.LAVENDER_QUARTZ_BRICKS)
                .add(ModBlocks.CHISELED_LAVENDER_QUARTZ)
                .add(ModBlocks.LAVENDER_QUARTZ_PILLAR)
                .add(ModBlocks.LAVENDER_QUARTZ_COLUMN)
                .add(ModBlocks.LAVENDER_QUARTZ_SLAB)
                .add(ModBlocks.LAVENDER_QUARTZ_STAIRS)
                .add(ModBlocks.LAVENDER_QUARTZ_WALL)

                .add(ModBlocks.CRACKED_QUARTZ_BRICKS)
                .add(ModBlocks.CRACKED_PRISMARINE_BRICKS)
                .add(ModBlocks.CRACKED_TUFF_BRICKS)
                .add(ModBlocks.CRACKED_RED_NETHER_BRICKS)
                .add(ModBlocks.CRACKED_BRICKS)
                .add(ModBlocks.CRACKED_MUD_BRICKS)
                .add(ModBlocks.CRACKED_END_STONE_BRICKS)
                .add(ModBlocks.CRACKED_MOSSY_STONE_BRICKS)

                .add(ModBlocks.QUARTZ_BRICKS_SLAB)
                .add(ModBlocks.QUARTZ_BRICKS_STAIRS)
                .add(ModBlocks.QUARTZ_BRICKS_WALL)

                .add(ModBlocks.CALCITE_SLAB)
                .add(ModBlocks.CALCITE_STAIRS)
                .add(ModBlocks.CALCITE_WALL)

                .add(ModBlocks.SMOOTH_STONE_STAIRS)
                .add(ModBlocks.CUT_RED_SANDSTONE_STAIRS)
                .add(ModBlocks.CUT_SANDSTONE_STAIRS)

                .add(ModBlocks.PURPUR_WALL)
                .add(ModBlocks.QUARTZ_WALL)
                .add(ModBlocks.SMOOTH_QUARTZ_WALL)
                .add(ModBlocks.POLISHED_ANDESITE_WALL)
                .add(ModBlocks.POLISHED_GRANITE_WALL)
                .add(ModBlocks.POLISHED_DIORITE_WALL)
                .add(ModBlocks.CUT_RED_SANDSTONE_WALL)
                .add(ModBlocks.SMOOTH_RED_SANDSTONE_WALL)
                .add(ModBlocks.CUT_SANDSTONE_WALL)
                .add(ModBlocks.SMOOTH_SANDSTONE_WALL)
                .add(ModBlocks.PRISMARINE_BRICKS_WALL)
                .add(ModBlocks.PRISMARINE_WALL)
                .add(ModBlocks.DARK_PRISMARINE_WALL)
                .add(ModBlocks.STONE_WALL)
                .add(ModBlocks.SMOOTH_STONE_WALL)

                .add(ModBlocks.CHISELED_DARK_PRISMARINE)
                .add(ModBlocks.CHISELED_PURPUR)
                .add(ModBlocks.CHISELED_DEEPSLATE_BRICKS)
                .add(ModBlocks.CHISELED_END_STONE_BRICKS)
                .add(ModBlocks.CHISELED_MUD_BRICKS)
                .add(ModBlocks.CHISELED_RED_NETHER_BRICKS)
                .add(ModBlocks.CHISELED_MOSSY_STONE_BRICKS)
                .add(ModBlocks.CHISELED_PRISMARINE)
                .add(ModBlocks.CHISELED_PRISMARINE_BRICKS)

                .add(ModBlocks.RED_NETHER_BRICK_FENCE);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.ROSE_QUARTZ_BRICKS)
                .add(ModBlocks.CHISELED_ROSE_QUARTZ)
                .add(ModBlocks.ROSE_QUARTZ_PILLAR)
                .add(ModBlocks.ROSE_QUARTZ_COLUMN)
                .add(ModBlocks.ROSE_QUARTZ_SLAB)
                .add(ModBlocks.ROSE_QUARTZ_STAIRS)
                .add(ModBlocks.ROSE_QUARTZ_WALL)

                .add(ModBlocks.LAVENDER_QUARTZ_BRICKS)
                .add(ModBlocks.CHISELED_LAVENDER_QUARTZ)
                .add(ModBlocks.LAVENDER_QUARTZ_PILLAR)
                .add(ModBlocks.LAVENDER_QUARTZ_COLUMN)
                .add(ModBlocks.LAVENDER_QUARTZ_SLAB)
                .add(ModBlocks.LAVENDER_QUARTZ_STAIRS)
                .add(ModBlocks.LAVENDER_QUARTZ_WALL);

        getOrCreateTagBuilder(BlockTags.RAILS)
                .add(ModBlocks.COPPER_RAIL);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.ROSE_QUARTZ_WALL)
                .add(ModBlocks.LAVENDER_QUARTZ_WALL)

                .add(ModBlocks.QUARTZ_BRICKS_WALL)
                .add(ModBlocks.CALCITE_WALL)

                .add(ModBlocks.PURPUR_WALL)
                .add(ModBlocks.QUARTZ_WALL)
                .add(ModBlocks.SMOOTH_QUARTZ_WALL)
                .add(ModBlocks.POLISHED_ANDESITE_WALL)
                .add(ModBlocks.POLISHED_GRANITE_WALL)
                .add(ModBlocks.POLISHED_DIORITE_WALL)
                .add(ModBlocks.CUT_RED_SANDSTONE_WALL)
                .add(ModBlocks.SMOOTH_RED_SANDSTONE_WALL)
                .add(ModBlocks.CUT_SANDSTONE_WALL)
                .add(ModBlocks.SMOOTH_SANDSTONE_WALL)
                .add(ModBlocks.PRISMARINE_BRICKS_WALL)
                .add(ModBlocks.PRISMARINE_WALL)
                .add(ModBlocks.DARK_PRISMARINE_WALL)
                .add(ModBlocks.STONE_WALL)
                .add(ModBlocks.SMOOTH_STONE_WALL);

        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.RED_NETHER_BRICK_FENCE);
    }
}
