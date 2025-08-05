package net.louis.overhaulmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        // SMELTING
        offerSmelting(exporter,List.of(ModItems.SANDY_FLESH), RecipeCategory.BUILDING_BLOCKS, Items.SAND, 0.1f, 300, "flesh_to_sand");
        offerSmelting(exporter, List.of(ModItems.DECAYING_FLESH), RecipeCategory.BUILDING_BLOCKS, Items.SEAGRASS, 0.1f, 300, "flesh_to_seagrass");

        offerSmelting(exporter, List.of(Items.BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_BRICKS, 0.1f, 300, "cracked");
        offerSmelting(exporter, List.of(Items.END_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_END_STONE_BRICKS, 0.1f, 300, "cracked");
        offerSmelting(exporter, List.of(Items.RED_NETHER_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_RED_NETHER_BRICKS, 0.1f, 300, "cracked");
        offerSmelting(exporter, List.of(Items.TUFF_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_TUFF_BRICKS, 0.1f, 300, "cracked");
        offerSmelting(exporter, List.of(Items.MUD_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_MUD_BRICKS, 0.1f, 300, "cracked");
        offerSmelting(exporter, List.of(Items.PRISMARINE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_PRISMARINE_BRICKS, 0.1f, 300, "cracked");
        offerSmelting(exporter, List.of(Items.MOSSY_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_MOSSY_STONE_BRICKS, 0.1f, 300, "cracked");
        offerSmelting(exporter, List.of(Items.QUARTZ_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_QUARTZ_BRICKS, 0.1f, 300, "cracked");

        // FOOD & DRINKS
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.FISH_STEW, 1)
                .input(Items.BOWL)
                .input(Items.DRIED_KELP)
                .input(Items.COOKED_SALMON)
                .input(Items.COOKED_COD)
                .input(Items.BAKED_POTATO)
                .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.VEGETABLE_STEW, 1)
                .input(Items.BOWL)
                .input(Items.POTATO)
                .input(Items.CARROT)
                .input(Items.BEETROOT)
                .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.ROTTEN_STEW, 1)
                .input(Items.BOWL)
                .input(Items.ROTTEN_FLESH)
                .input(ModItems.DECAYING_FLESH)
                .input(ModItems.SANDY_FLESH)
                .criterion(hasItem(Items.BOWL), conditionsFromItem(Items.BOWL))
                .offerTo(exporter);

        // REDSTONE
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.COPPER_RAIL, 18)
                .pattern("C C")
                .pattern("CSC")
                .pattern("C C")
                .input('C', Items.COPPER_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);

        // COMBAT
        offerNetheriteUpgradeRecipe(exporter, Items.DIAMOND_HORSE_ARMOR, RecipeCategory.COMBAT, ModItems.NETHERITE_HORSE_ARMOR);

        // MISC
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RECALL_CLOCK, 1)
                .pattern("PEP")
                .pattern("PCP")
                .pattern("PEP")
                .input('C', Items.CLOCK)
                .input('P', Items.POPPED_CHORUS_FRUIT)
                .input('E', ModItems.ENDERMITE_HEART)
                .criterion(hasItem(Items.CLOCK), conditionsFromItem(Items.CLOCK))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BUNDLE, 1)
                .input(Items.LEATHER)
                .input(Items.STRING)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PET_RECOVERY_COMPASS, 1)
                .input(Items.TOTEM_OF_UNDYING)
                .input(Items.RECOVERY_COMPASS)
                .criterion(hasItem(Items.TOTEM_OF_UNDYING), conditionsFromItem(Items.TOTEM_OF_UNDYING))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SADDLED_GOAT_HORN, 1)
                .input(Items.GOAT_HORN)
                .input(Items.SADDLE)
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
                .offerTo(exporter);

        // BUILDING BLOCKS
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.SUSPICIOUS_SAND, 1)
                .pattern("FS")
                .pattern("SF")
                .input('S', Items.SAND)
                .input('F', ModItems.SANDY_FLESH)
                .criterion(hasItem(ModItems.SANDY_FLESH), conditionsFromItem(ModItems.SANDY_FLESH))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.SUSPICIOUS_GRAVEL, 1)
                .pattern("DG")
                .pattern("GD")
                .input('G', Items.GRAVEL)
                .input('D', ModItems.DECAYING_FLESH)
                .criterion(hasItem(ModItems.DECAYING_FLESH), conditionsFromItem(ModItems.DECAYING_FLESH))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Items.PLAYER_HEAD, 1)
                .pattern("DDD")
                .pattern("DHD")
                .pattern("DDD")
                .input('D', Items.DRIED_KELP)
                .input('H', Items.HAY_BLOCK)
                .criterion(hasItem(Items.DRIED_KELP), conditionsFromItem(Items.DRIED_KELP))
                .offerTo(exporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROSE_QUARTZ_BRICKS, 4)
                .pattern("QA")
                .pattern("AQ")
                .input('A', Items.REDSTONE)
                .input('Q', Items.QUARTZ)
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
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROSE_QUARTZ_COLUMN, 2)
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
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_QUARTZ_COLUMN, 2)
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
}