package net.louis.overhaulmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;


public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //  blockStateModelGenerator.registerSimpleCubeAll(ModBlocks);
        blockStateModelGenerator.registerStraightRail(ModBlocks.COPPER_RAIL);

        blockStateModelGenerator.registerLantern(ModBlocks.GLOW_LANTERN);

        BlockStateModelGenerator.BlockTexturePool roseQuartzPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.ROSE_QUARTZ_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_ROSE_QUARTZ);
        blockStateModelGenerator.registerLog(ModBlocks.ROSE_QUARTZ_PILLAR).log(ModBlocks.ROSE_QUARTZ_PILLAR).wood(ModBlocks.ROSE_QUARTZ_COLUMN);
        roseQuartzPool.stairs(ModBlocks.ROSE_QUARTZ_STAIRS);
        roseQuartzPool.slab(ModBlocks.ROSE_QUARTZ_SLAB);
        roseQuartzPool.wall(ModBlocks.ROSE_QUARTZ_WALL);

        BlockStateModelGenerator.BlockTexturePool lavenderQuartz = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LAVENDER_QUARTZ_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_LAVENDER_QUARTZ);
        blockStateModelGenerator.registerLog(ModBlocks.LAVENDER_QUARTZ_PILLAR).log(ModBlocks.LAVENDER_QUARTZ_PILLAR).wood(ModBlocks.LAVENDER_QUARTZ_COLUMN);
        lavenderQuartz.stairs(ModBlocks.LAVENDER_QUARTZ_STAIRS);
        lavenderQuartz.slab(ModBlocks.LAVENDER_QUARTZ_SLAB);
        lavenderQuartz.wall(ModBlocks.LAVENDER_QUARTZ_WALL);

        BlockStateModelGenerator.BlockTexturePool quartzBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.QUARTZ_BRICKS);
        quartzBricksPool.stairs(ModBlocks.QUARTZ_BRICKS_STAIRS);
        quartzBricksPool.slab(ModBlocks.QUARTZ_BRICKS_SLAB);
        quartzBricksPool.wall(ModBlocks.QUARTZ_BRICKS_WALL);

        BlockStateModelGenerator.BlockTexturePool calcitePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.CALCITE);
        calcitePool.stairs(ModBlocks.CALCITE_STAIRS);
        calcitePool.slab(ModBlocks.CALCITE_SLAB);
        calcitePool.wall(ModBlocks.CALCITE_WALL);

        BlockStateModelGenerator.BlockTexturePool purpurPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.PURPUR_BLOCK);
        purpurPool.wall(ModBlocks.PURPUR_WALL);
        BlockStateModelGenerator.BlockTexturePool smoothQuartzPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.SMOOTH_QUARTZ);
        smoothQuartzPool.wall(ModBlocks.SMOOTH_QUARTZ_WALL);
        BlockStateModelGenerator.BlockTexturePool polishedAndesitePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_ANDESITE);
        polishedAndesitePool.wall(ModBlocks.POLISHED_ANDESITE_WALL);
        BlockStateModelGenerator.BlockTexturePool polishedGranitePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_GRANITE);
        polishedGranitePool.wall(ModBlocks.POLISHED_GRANITE_WALL);
        BlockStateModelGenerator.BlockTexturePool polishedDioritePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_DIORITE);
        polishedDioritePool.wall(ModBlocks.POLISHED_DIORITE_WALL);
        BlockStateModelGenerator.BlockTexturePool smoothRedSandstonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.SMOOTH_RED_SANDSTONE);
        smoothRedSandstonePool.wall(ModBlocks.SMOOTH_RED_SANDSTONE_WALL);
        BlockStateModelGenerator.BlockTexturePool smoothSandstonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.SMOOTH_SANDSTONE);
        smoothSandstonePool.wall(ModBlocks.SMOOTH_SANDSTONE_WALL);
        BlockStateModelGenerator.BlockTexturePool prismarineBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.PRISMARINE_BRICKS);
        prismarineBricksPool.wall(ModBlocks.PRISMARINE_BRICKS_WALL);
        BlockStateModelGenerator.BlockTexturePool prismarinePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.PRISMARINE);
        prismarinePool.wall(ModBlocks.PRISMARINE_WALL);
        BlockStateModelGenerator.BlockTexturePool darkPrismarinePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.DARK_PRISMARINE);
        darkPrismarinePool.wall(ModBlocks.DARK_PRISMARINE_WALL);
        BlockStateModelGenerator.BlockTexturePool smoothStonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.SMOOTH_STONE);
        smoothStonePool.wall(ModBlocks.SMOOTH_STONE_WALL);
        smoothStonePool.stairs(ModBlocks.SMOOTH_STONE_STAIRS);
        BlockStateModelGenerator.BlockTexturePool stonePool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.STONE);
        stonePool.wall(ModBlocks.STONE_WALL);
        BlockStateModelGenerator.BlockTexturePool cutRedSandstone = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.CUT_RED_SANDSTONE);
        cutRedSandstone.stairs(ModBlocks.CUT_RED_SANDSTONE_STAIRS);
        BlockStateModelGenerator.BlockTexturePool cutSandstone = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.CUT_SANDSTONE);
        cutSandstone.stairs(ModBlocks.CUT_SANDSTONE_STAIRS);

        BlockStateModelGenerator.BlockTexturePool redNetherBricks = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.RED_NETHER_BRICKS);
        redNetherBricks.fence(ModBlocks.RED_NETHER_BRICK_FENCE);

        // Manually load Wall models for Quartz, Cut Sandstone & Cut Red Sandstone

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_END_STONE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_RED_NETHER_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_MUD_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_MOSSY_STONE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_TUFF_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_PRISMARINE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_QUARTZ_BRICKS);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_DARK_PRISMARINE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_PURPUR);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_RED_NETHER_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_DEEPSLATE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_END_STONE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_MUD_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_PRISMARINE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_PRISMARINE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_MOSSY_STONE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_QUARTZ_BRICKS);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.CHILLED_BONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SANDY_FLESH, Models.GENERATED);
        itemModelGenerator.register(ModItems.BAT_FANG, Models.GENERATED);
        itemModelGenerator.register(ModItems.NETHERITE_HORSE_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDERMITE_HEART, Models.GENERATED);
        itemModelGenerator.register(ModItems.DECAYING_FLESH, Models.GENERATED);
        itemModelGenerator.register(ModItems.POTION_POUCH, Models.GENERATED);
        itemModelGenerator.register(ModItems.SADDLED_GOAT_HORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.RECALL_CLOCK, Models.GENERATED);
        itemModelGenerator.register(ModItems.LLAMAS_SPIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.VEGETABLE_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.FISH_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROTTEN_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHILLED_BONE_MEAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMETHYST_DAGGER, Models.HANDHELD);
        // Pet Recovery Compass Generated Manually
    }
}