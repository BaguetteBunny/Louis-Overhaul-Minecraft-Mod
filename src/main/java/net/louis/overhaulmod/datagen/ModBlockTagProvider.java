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
                .add(ModBlocks.LAVENDER_QUARTZ_WALL);

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
                .add(ModBlocks.LAVENDER_QUARTZ_WALL);
    }
}
