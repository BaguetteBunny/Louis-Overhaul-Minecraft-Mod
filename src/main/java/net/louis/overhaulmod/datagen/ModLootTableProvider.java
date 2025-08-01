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
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
