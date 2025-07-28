package net.louis.overhaulmod.component;

import com.mojang.serialization.Codec;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

import java.util.function.UnaryOperator;

public class ModComponents {

    public static final ComponentType<Double> HORSE_SPEED = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(LouisOverhaulMod.MOD_ID, "horse_speed"),
            ComponentType.<Double>builder().codec(Codec.DOUBLE).build()
    );

    public static final ComponentType<Double> HORSE_JUMP = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(LouisOverhaulMod.MOD_ID, "horse_jump"),
            ComponentType.<Double>builder().codec(Codec.DOUBLE).build()
    );

    public static final ComponentType<Float> HORSE_MAX_HEALTH = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(LouisOverhaulMod.MOD_ID, "horse_max_hp"),
            ComponentType.<Float>builder().codec(Codec.FLOAT).build()
    );

    public static final ComponentType<Float> HORSE_HEALTH = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(LouisOverhaulMod.MOD_ID, "horse_hp"),
            ComponentType.<Float>builder().codec(Codec.FLOAT).build()
    );

    public static final ComponentType<String> HORSE_NAME = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(LouisOverhaulMod.MOD_ID, "horse_name"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );

    public static final ComponentType<String> HORSE_UUID = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(LouisOverhaulMod.MOD_ID, "horse_uuid"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );

    public static final ComponentType<Integer> HORSE_ARMOR = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(LouisOverhaulMod.MOD_ID, "horse_armor"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );

    public static final ComponentType<Integer> HORSE_COLOR = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(LouisOverhaulMod.MOD_ID, "horse_color"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );

    public static final ComponentType<Integer> HORSE_IDENTIFIER = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(LouisOverhaulMod.MOD_ID, "horse_identifier"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );

    public static final ComponentType<String> HORSE_ARMOR_DYE = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(LouisOverhaulMod.MOD_ID, "horse_armor_dye"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(LouisOverhaulMod.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        LouisOverhaulMod.LOGGER.info("Registering Data Component Types for " + LouisOverhaulMod.MOD_ID);
    }
}