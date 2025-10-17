package net.louis.overhaulmod.utils;

import net.louis.overhaulmod.LouisOverhaulMod;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class StackableStews {
    public static void registerStewStacking() {
        Registry.register(Registries.ITEM, Identifier.of("minecraft", "mushroom_stew"),
                new Item(new Item.Settings().maxCount(16).food(new FoodComponent.Builder()
                        .nutrition(6)
                        .saturationModifier(0.6f)
                        .snack()
                        .build()))
        );
        Registry.register(Registries.ITEM, Identifier.of("minecraft", "beetroot_soup"),
                new Item(new Item.Settings().maxCount(16).food(new FoodComponent.Builder()
                        .nutrition(6)
                        .saturationModifier(0.6f)
                        .snack()
                        .build()))
        );
        Registry.register(Registries.ITEM, Identifier.of("minecraft", "rabbit_stew"),
                new Item(new Item.Settings().maxCount(16).food(new FoodComponent.Builder()
                        .nutrition(10)
                        .saturationModifier(0.6f)
                        .snack()
                        .build()))
        );
        LouisOverhaulMod.LOGGER.info("Registering Mod Stackable Stews for " + LouisOverhaulMod.MOD_ID);
    }
}
