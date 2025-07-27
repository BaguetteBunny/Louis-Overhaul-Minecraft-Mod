package net.louis.overhaulmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //  blockStateModelGenerator.registerSimpleCubeAll(ModBlocks);
        blockStateModelGenerator.registerStraightRail(ModBlocks.COPPER_RAIL);

        BlockStateModelGenerator.BlockTexturePool roseQuartzPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.ROSE_QUARTZ_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_ROSE_QUARTZ);
        blockStateModelGenerator.registerLog(ModBlocks.ROSE_QUARTZ_PILLAR).log(ModBlocks.ROSE_QUARTZ_PILLAR).wood(ModBlocks.ROSE_QUARTZ_COLUMN);
        roseQuartzPool.stairs(ModBlocks.ROSE_QUARTZ_STAIRS);
        roseQuartzPool.slab(ModBlocks.ROSE_QUARTZ_SLAB);
        roseQuartzPool.wall(ModBlocks.ROSE_QUARTZ_WALL);

        BlockStateModelGenerator.BlockTexturePool lavenderQuartzPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LAVENDER_QUARTZ_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_LAVENDER_QUARTZ);
        blockStateModelGenerator.registerLog(ModBlocks.LAVENDER_QUARTZ_PILLAR).log(ModBlocks.LAVENDER_QUARTZ_PILLAR).wood(ModBlocks.LAVENDER_QUARTZ_COLUMN);
        lavenderQuartzPool.stairs(ModBlocks.LAVENDER_QUARTZ_STAIRS);
        lavenderQuartzPool.slab(ModBlocks.LAVENDER_QUARTZ_SLAB);
        lavenderQuartzPool.wall(ModBlocks.LAVENDER_QUARTZ_WALL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BAT_FANG, Models.GENERATED);
        itemModelGenerator.register(ModItems.NETHERITE_HORSE_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDERMITE_HEART, Models.GENERATED);
        itemModelGenerator.register(ModItems.POTION_POUCH, Models.GENERATED);
    }
}