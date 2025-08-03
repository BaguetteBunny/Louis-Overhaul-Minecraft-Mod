package net.louis.overhaulmod.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.effect.EnchantmentEffectEntry;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static net.louis.overhaulmod.LouisOverhaulMod.LOGGER;

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

    public static void replaceLootTables() {
        LootTableEvents.REPLACE.register((key, lootManager, source, registry) -> {
            RegistryKey<Enchantment> lootingKey = Enchantments.LOOTING;
            RegistryWrapper<Enchantment> enchantmentRegistry = registry.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
            RegistryEntry<Enchantment> lootingEntry = enchantmentRegistry.getOrThrow(lootingKey);
            Enchantment lootingEnchant = lootingEntry.value();
            System.out.println(lootingEnchant);

            if (WARDEN_ID.equals(key.getValue())) {
                LootPool.Builder customPool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f)) // 100% Drop Rate
                        .with(ItemEntry.builder(Items.REINFORCED_DEEPSLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 8.0f)).build());
                return LootTable.builder().pool(customPool).build();
            }

            if (HUSK_ID.equals(key.getValue())) {
                LootPool.Builder customPool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f)) // 100% Drop Rate
                        .with(ItemEntry.builder(ModItems.DECAYING_FLESH))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)).build())
                        .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                return LootTable.builder().pool(customPool).build();
            }
            return null;
        });
    }

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

        });
    }
}

