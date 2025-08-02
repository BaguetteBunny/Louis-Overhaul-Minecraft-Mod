package net.louis.overhaulmod.utils;

import net.louis.overhaulmod.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {
    private static final Identifier BAT_ID
            = Identifier.of("minecraft", "entities/bat");
    private static final Identifier ENDERMITE_ID
            = Identifier.of("minecraft", "entities/endermite");
    private static final Identifier HUSK_ID
            = Identifier.of("minecraft", "entities/husk");
    private static final Identifier STRAY_ID
            = Identifier.of("minecraft", "entities/stray");
    private static final Identifier WARDEN_ID
            = Identifier.of("minecraft", "entities/warden");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            // Make bats drop bat fangs
            if(BAT_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.50f)) // 50% Drop Rate
                        .with(ItemEntry.builder(ModItems.BAT_FANG))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            // Make endermite drop heart
            if(ENDERMITE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.50f)) // 50% Drop Rate
                        .with(ItemEntry.builder(ModItems.ENDERMITE_HEART))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            // Make husk drop decaying flesh
            if(HUSK_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.25f)) // 25% Drop Rate
                        .with(ItemEntry.builder(ModItems.DECAYING_FLESH))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            // Make wardens drop reinforced deepslate
            if(WARDEN_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f)) // 100% Drop Rate
                        .with(ItemEntry.builder(Items.REINFORCED_DEEPSLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 8.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}

