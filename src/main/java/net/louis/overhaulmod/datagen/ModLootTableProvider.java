package net.louis.overhaulmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.louis.overhaulmod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.COPPER_RAIL);

        addDrop(ModBlocks.ROSE_QUARTZ_BRICKS);
        addDrop(ModBlocks.ROSE_QUARTZ_PILLAR);
        addDrop(ModBlocks.ROSE_QUARTZ_COLUMN);
        addDrop(ModBlocks.CHISELED_ROSE_QUARTZ);
        addDrop(ModBlocks.ROSE_QUARTZ_SLAB);
        addDrop(ModBlocks.ROSE_QUARTZ_STAIRS);
        addDrop(ModBlocks.ROSE_QUARTZ_WALL);

        addDrop(ModBlocks.LAVENDER_QUARTZ_BRICKS);
        addDrop(ModBlocks.LAVENDER_QUARTZ_PILLAR);
        addDrop(ModBlocks.LAVENDER_QUARTZ_COLUMN);
        addDrop(ModBlocks.CHISELED_LAVENDER_QUARTZ);
        addDrop(ModBlocks.LAVENDER_QUARTZ_SLAB);
        addDrop(ModBlocks.LAVENDER_QUARTZ_STAIRS);
        addDrop(ModBlocks.LAVENDER_QUARTZ_WALL);

        addDrop(ModBlocks.CRACKED_BRICKS);
        addDrop(ModBlocks.CRACKED_MOSSY_STONE_BRICKS);
        addDrop(ModBlocks.CRACKED_QUARTZ_BRICKS);
        addDrop(ModBlocks.CRACKED_PRISMARINE_BRICKS);
        addDrop(ModBlocks.CRACKED_TUFF_BRICKS);
        addDrop(ModBlocks.CRACKED_RED_NETHER_BRICKS);
        addDrop(ModBlocks.CRACKED_END_STONE_BRICKS);
        addDrop(ModBlocks.CRACKED_MUD_BRICKS);

        addDrop(ModBlocks.QUARTZ_BRICKS_SLAB);
        addDrop(ModBlocks.QUARTZ_BRICKS_STAIRS);
        addDrop(ModBlocks.QUARTZ_BRICKS_WALL);

        addDrop(ModBlocks.CALCITE_SLAB);
        addDrop(ModBlocks.CALCITE_STAIRS);
        addDrop(ModBlocks.CALCITE_WALL);

        addDrop(ModBlocks.SMOOTH_STONE_STAIRS);
        addDrop(ModBlocks.CUT_RED_SANDSTONE_STAIRS);
        addDrop(ModBlocks.CUT_SANDSTONE_STAIRS);

        addDrop(ModBlocks.PURPUR_WALL);
        addDrop(ModBlocks.QUARTZ_WALL);
        addDrop(ModBlocks.SMOOTH_QUARTZ_WALL);
        addDrop(ModBlocks.POLISHED_ANDESITE_WALL);
        addDrop(ModBlocks.POLISHED_GRANITE_WALL);
        addDrop(ModBlocks.POLISHED_DIORITE_WALL);
        addDrop(ModBlocks.CUT_RED_SANDSTONE_WALL);
        addDrop(ModBlocks.SMOOTH_RED_SANDSTONE_WALL);
        addDrop(ModBlocks.CUT_SANDSTONE_WALL);
        addDrop(ModBlocks.SMOOTH_SANDSTONE_WALL);
        addDrop(ModBlocks.PRISMARINE_BRICKS_WALL);
        addDrop(ModBlocks.PRISMARINE_WALL);
        addDrop(ModBlocks.DARK_PRISMARINE_WALL);
        addDrop(ModBlocks.SMOOTH_STONE_WALL);
        addDrop(ModBlocks.STONE_WALL);

        addDrop(ModBlocks.CHISELED_DARK_PRISMARINE);
        addDrop(ModBlocks.CHISELED_PURPUR);
        addDrop(ModBlocks.CHISELED_DEEPSLATE_BRICKS);
        addDrop(ModBlocks.CHISELED_END_STONE_BRICKS);
        addDrop(ModBlocks.CHISELED_MUD_BRICKS);
        addDrop(ModBlocks.CHISELED_RED_NETHER_BRICKS);
        addDrop(ModBlocks.CHISELED_MOSSY_STONE_BRICKS);
        addDrop(ModBlocks.CHISELED_PRISMARINE);
        addDrop(ModBlocks.CHISELED_PRISMARINE_BRICKS);

        addDrop(ModBlocks.RED_NETHER_BRICK_FENCE);
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
