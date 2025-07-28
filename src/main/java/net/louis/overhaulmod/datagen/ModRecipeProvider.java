package net.louis.overhaulmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.item.ModItems;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        // SMELTING
        offerSmelting(exporter, ModItems.DECAYING_FLESH, RecipeCategory.MISC, Items.GREEN_DYE, 0.25f, 200);

        // REDSTONE
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.COPPER_RAIL, 18)
                .pattern("C C")
                .pattern("CSC")
                .pattern("C C")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter);

        // COMBAT
        offerNetheriteUpgradeRecipe(exporter, Items.DIAMOND_HORSE_ARMOR, RecipeCategory.COMBAT, ModItems.NETHERITE_HORSE_ARMOR);

        // MISC
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BUNDLE, 1)
                .input(Items.LEATHER)
                .input(Items.STRING)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SADDLED_GOAT_HORN, 1)
                .input(Items.GOAT_HORN)
                .input(Items.SADDLE)
                .criterion(hasItem(Items.GOAT_HORN), conditionsFromItem(Items.GOAT_HORN))
                .criterion(hasItem(Items.SADDLE), conditionsFromItem(Items.SADDLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.POTION_POUCH, 1)
                .pattern("SBS")
                .pattern("H H")
                .pattern("   ")
                .input('H', Items.RABBIT_HIDE)
                .input('S', Items.STRING)
                .input('B', ModItems.BAT_FANG)
                .criterion(hasItem(Items.RABBIT_HIDE), conditionsFromItem(Items.RABBIT_HIDE))
                .criterion(hasItem(ModItems.BAT_FANG), conditionsFromItem(ModItems.BAT_FANG))
                .offerTo(exporter);

        // BUILDING BLOCKS
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROSE_QUARTZ_BRICKS, 4)
                .pattern("QA")
                .pattern("AQ")
                .input('A', Items.REDSTONE)
                .input('Q', Items.QUARTZ)
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .criterion(hasItem(Items.QUARTZ), conditionsFromItem(Items.QUARTZ))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROSE_QUARTZ_PILLAR, 2)
                .pattern("A")
                .pattern("A")
                .input('A', ModBlocks.ROSE_QUARTZ_BRICKS)
                .criterion(hasItem(ModBlocks.ROSE_QUARTZ_BRICKS), conditionsFromItem(ModBlocks.ROSE_QUARTZ_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_ROSE_QUARTZ, 1)
                .pattern("A")
                .pattern("A")
                .input('A', ModBlocks.ROSE_QUARTZ_SLAB)
                .criterion(hasItem(ModBlocks.ROSE_QUARTZ_BRICKS), conditionsFromItem(ModBlocks.ROSE_QUARTZ_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROSE_QUARTZ_COLUMN, 1)
                .pattern("A")
                .pattern("A")
                .input('A', ModBlocks.ROSE_QUARTZ_PILLAR)
                .criterion(hasItem(ModBlocks.ROSE_QUARTZ_BRICKS), conditionsFromItem(ModBlocks.ROSE_QUARTZ_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROSE_QUARTZ_STAIRS, 4)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .input('A', ModBlocks.ROSE_QUARTZ_BRICKS)
                .criterion(hasItem(ModBlocks.ROSE_QUARTZ_BRICKS), conditionsFromItem(ModBlocks.ROSE_QUARTZ_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROSE_QUARTZ_SLAB, 6)
                .pattern("AAA")
                .input('A', ModBlocks.ROSE_QUARTZ_BRICKS)
                .criterion(hasItem(ModBlocks.ROSE_QUARTZ_BRICKS), conditionsFromItem(ModBlocks.ROSE_QUARTZ_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROSE_QUARTZ_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', ModBlocks.ROSE_QUARTZ_BRICKS)
                .criterion(hasItem(ModBlocks.ROSE_QUARTZ_BRICKS), conditionsFromItem(ModBlocks.ROSE_QUARTZ_BRICKS))
                .offerTo(exporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_QUARTZ_BRICKS, 4)
                .pattern("QA")
                .pattern("AQ")
                .input('A', Items.AMETHYST_SHARD)
                .input('Q', Items.QUARTZ)
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_QUARTZ_PILLAR, 2)
                .pattern("A")
                .pattern("A")
                .input('A', ModBlocks.LAVENDER_QUARTZ_BRICKS)
                .criterion(hasItem(ModBlocks.LAVENDER_QUARTZ_BRICKS), conditionsFromItem(ModBlocks.LAVENDER_QUARTZ_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_QUARTZ_COLUMN, 1)
                .pattern("A")
                .pattern("A")
                .input('A', ModBlocks.LAVENDER_QUARTZ_PILLAR)
                .criterion(hasItem(ModBlocks.LAVENDER_QUARTZ_BRICKS), conditionsFromItem(ModBlocks.LAVENDER_QUARTZ_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_LAVENDER_QUARTZ, 1)
                .pattern("A")
                .pattern("A")
                .input('A', ModBlocks.LAVENDER_QUARTZ_SLAB)
                .criterion(hasItem(ModBlocks.LAVENDER_QUARTZ_BRICKS), conditionsFromItem(ModBlocks.LAVENDER_QUARTZ_SLAB))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_QUARTZ_STAIRS, 4)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .input('A', ModBlocks.LAVENDER_QUARTZ_BRICKS)
                .criterion(hasItem(ModBlocks.LAVENDER_QUARTZ_BRICKS), conditionsFromItem(ModBlocks.LAVENDER_QUARTZ_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_QUARTZ_SLAB, 6)
                .pattern("AAA")
                .input('A', ModBlocks.LAVENDER_QUARTZ_BRICKS)
                .criterion(hasItem(ModBlocks.LAVENDER_QUARTZ_BRICKS), conditionsFromItem(ModBlocks.LAVENDER_QUARTZ_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_QUARTZ_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', ModBlocks.LAVENDER_QUARTZ_BRICKS)
                .criterion(hasItem(ModBlocks.LAVENDER_QUARTZ_BRICKS), conditionsFromItem(ModBlocks.LAVENDER_QUARTZ_BRICKS))
                .offerTo(exporter);
    }

    private void offerSmelting(RecipeExporter exporter, Item rottenFlesh, RecipeCategory recipeCategory, Item decayingFlesh, float v, int i) {
    }
}