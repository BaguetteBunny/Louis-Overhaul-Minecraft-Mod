package net.louis.overhaulmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
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
                .input(Items.ROTTEN_FLESH)
                .input(ModItems.DECAYING_FLESH)
                .input(ModItems.DECAYING_FLESH)
                .input(ModItems.SANDY_FLESH)
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

        // INGREDIENT
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CHILLED_BONE_MEAL, 3)
                .input(ModItems.CHILLED_BONE)
                .criterion(hasItem(ModItems.CHILLED_BONE), conditionsFromItem(ModItems.CHILLED_BONE))
                .offerTo(exporter);

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


        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BRICKS_SLAB, 6)
                .pattern("AAA")
                .input('A', Blocks.QUARTZ_BRICKS)
                .criterion(hasItem(Blocks.QUARTZ_BRICKS), conditionsFromItem(Blocks.QUARTZ_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BRICKS_STAIRS, 4)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .input('A', Blocks.QUARTZ_BRICKS)
                .criterion(hasItem(Blocks.QUARTZ_BRICKS), conditionsFromItem(Blocks.QUARTZ_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_BRICKS_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', Blocks.QUARTZ_BRICKS)
                .criterion(hasItem(Blocks.QUARTZ_BRICKS), conditionsFromItem(Blocks.QUARTZ_BRICKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_SLAB, 6)
                .pattern("AAA")
                .input('A', Blocks.CALCITE)
                .criterion(hasItem(Blocks.CALCITE), conditionsFromItem(Blocks.CALCITE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_STAIRS, 4)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .input('A', Blocks.CALCITE)
                .criterion(hasItem(Blocks.CALCITE), conditionsFromItem(Blocks.CALCITE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CALCITE_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', Blocks.CALCITE)
                .criterion(hasItem(Blocks.CALCITE), conditionsFromItem(Blocks.CALCITE))
                .offerTo(exporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PURPUR_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', Blocks.PURPUR_BLOCK)
                .criterion(hasItem(Blocks.PURPUR_BLOCK), conditionsFromItem(Blocks.PURPUR_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', Blocks.QUARTZ_BLOCK)
                .criterion(hasItem(Blocks.QUARTZ_BLOCK), conditionsFromItem(Blocks.QUARTZ_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_QUARTZ_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', Blocks.SMOOTH_QUARTZ)
                .criterion(hasItem(Blocks.SMOOTH_QUARTZ), conditionsFromItem(Blocks.SMOOTH_QUARTZ))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_ANDESITE_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', Blocks.POLISHED_ANDESITE)
                .criterion(hasItem(Blocks.POLISHED_ANDESITE), conditionsFromItem(Blocks.POLISHED_ANDESITE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GRANITE_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', Blocks.POLISHED_GRANITE)
                .criterion(hasItem(Blocks.POLISHED_GRANITE), conditionsFromItem(Blocks.POLISHED_GRANITE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_DIORITE_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', Blocks.POLISHED_DIORITE)
                .criterion(hasItem(Blocks.POLISHED_DIORITE), conditionsFromItem(Blocks.POLISHED_DIORITE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_RED_SANDSTONE_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', Blocks.CUT_RED_SANDSTONE)
                .criterion(hasItem(Blocks.CUT_RED_SANDSTONE), conditionsFromItem(Blocks.CUT_RED_SANDSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_RED_SANDSTONE_STAIRS, 4)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .input('A', Blocks.CUT_RED_SANDSTONE)
                .criterion(hasItem(Blocks.CUT_RED_SANDSTONE), conditionsFromItem(Blocks.CUT_RED_SANDSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_RED_SANDSTONE_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', Blocks.SMOOTH_SANDSTONE)
                .criterion(hasItem(Blocks.SMOOTH_SANDSTONE), conditionsFromItem(Blocks.SMOOTH_SANDSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SANDSTONE_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', Blocks.CUT_SANDSTONE)
                .criterion(hasItem(Blocks.CUT_SANDSTONE), conditionsFromItem(Blocks.CUT_SANDSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SANDSTONE_STAIRS, 4)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .input('A', Blocks.CUT_SANDSTONE)
                .criterion(hasItem(Blocks.CUT_SANDSTONE), conditionsFromItem(Blocks.CUT_SANDSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_SANDSTONE_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', Blocks.SMOOTH_SANDSTONE)
                .criterion(hasItem(Blocks.SMOOTH_SANDSTONE), conditionsFromItem(Blocks.SMOOTH_SANDSTONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PRISMARINE_BRICKS_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', Blocks.PRISMARINE_BRICKS)
                .criterion(hasItem(Blocks.PRISMARINE_BRICKS), conditionsFromItem(Blocks.PRISMARINE_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PRISMARINE_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', Blocks.PRISMARINE)
                .criterion(hasItem(Blocks.PRISMARINE), conditionsFromItem(Blocks.PRISMARINE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_PRISMARINE_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', Blocks.DARK_PRISMARINE)
                .criterion(hasItem(Blocks.DARK_PRISMARINE), conditionsFromItem(Blocks.DARK_PRISMARINE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STONE_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', Blocks.STONE)
                .criterion(hasItem(Blocks.STONE), conditionsFromItem(Blocks.STONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_WALL, 6)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', Blocks.SMOOTH_STONE)
                .criterion(hasItem(Blocks.SMOOTH_STONE), conditionsFromItem(Blocks.SMOOTH_STONE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_STAIRS, 4)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .input('A', Blocks.SMOOTH_STONE)
                .criterion(hasItem(Blocks.SMOOTH_STONE), conditionsFromItem(Blocks.SMOOTH_STONE))
                .offerTo(exporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_DARK_PRISMARINE, 1)
                .pattern("A")
                .pattern("A")
                .input('A', Blocks.DARK_PRISMARINE_SLAB)
                .criterion(hasItem(Blocks.DARK_PRISMARINE), conditionsFromItem(Blocks.DARK_PRISMARINE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_DEEPSLATE_BRICKS, 1)
                .pattern("A")
                .pattern("A")
                .input('A', Blocks.DEEPSLATE_BRICK_SLAB)
                .criterion(hasItem(Blocks.DEEPSLATE_BRICKS), conditionsFromItem(Blocks.DEEPSLATE_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_PRISMARINE, 1)
                .pattern("A")
                .pattern("A")
                .input('A', Blocks.PRISMARINE_SLAB)
                .criterion(hasItem(Blocks.PRISMARINE), conditionsFromItem(Blocks.PRISMARINE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_MUD_BRICKS, 1)
                .pattern("A")
                .pattern("A")
                .input('A', Blocks.MUD_BRICK_SLAB)
                .criterion(hasItem(Blocks.MUD_BRICKS), conditionsFromItem(Blocks.MUD_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_PURPUR, 1)
                .pattern("A")
                .pattern("A")
                .input('A', Blocks.PURPUR_SLAB)
                .criterion(hasItem(Blocks.PURPUR_BLOCK), conditionsFromItem(Blocks.PURPUR_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_END_STONE_BRICKS, 1)
                .pattern("A")
                .pattern("A")
                .input('A', Blocks.END_STONE_BRICK_SLAB)
                .criterion(hasItem(Blocks.END_STONE_BRICKS), conditionsFromItem(Blocks.END_STONE_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_PRISMARINE_BRICKS, 1)
                .pattern("A")
                .pattern("A")
                .input('A', Blocks.PRISMARINE_BRICK_SLAB)
                .criterion(hasItem(Blocks.PRISMARINE_BRICKS), conditionsFromItem(Blocks.PRISMARINE_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_RED_NETHER_BRICKS, 1)
                .pattern("A")
                .pattern("A")
                .input('A', Blocks.RED_NETHER_BRICK_SLAB)
                .criterion(hasItem(Blocks.RED_NETHER_BRICKS), conditionsFromItem(Blocks.RED_NETHER_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_MOSSY_STONE_BRICKS, 1)
                .pattern("A")
                .pattern("A")
                .input('A', Blocks.MOSSY_STONE_BRICK_SLAB)
                .criterion(hasItem(Blocks.MOSSY_STONE_BRICKS), conditionsFromItem(Blocks.MOSSY_STONE_BRICKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_NETHER_BRICK_FENCE, 6)
                .pattern("ABA")
                .pattern("ABA")
                .input('A', Blocks.RED_NETHER_BRICKS)
                .input('B', Items.NETHER_BRICKS)
                .criterion(hasItem(Blocks.RED_NETHER_BRICKS), conditionsFromItem(Blocks.RED_NETHER_BRICKS))
                .offerTo(exporter);
    }
}