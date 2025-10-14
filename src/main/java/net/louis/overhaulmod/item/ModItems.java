package net.louis.overhaulmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CHILLED_BONE = registerItem("chilled_bone",
            new Item(new Item.Settings()));
    public static final Item CHILLED_BONE_MEAL = registerItem("chilled_bone_meal",
            new Item(new Item.Settings()));
    public static final Item BAT_FANG = registerItem("bat_fang",
            new Item(new Item.Settings()));
    public static final Item ENDERMITE_HEART = registerItem("endermite_heart",
            new Item(new Item.Settings()));
    public static final Item DECAYING_FLESH = registerItem("decaying_flesh",
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(4)
                    .saturationModifier(0.1f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 900, 0), 1f)
                    .build()))
    );
    public static final Item SANDY_FLESH = registerItem("sandy_flesh",
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(4)
                    .saturationModifier(0.1f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 900, 0), 1f)
                    .build()))
    );
    public static final Item FISH_STEW = registerItem("fish_stew",
            new Item(new Item.Settings().maxCount(16).food(new FoodComponent.Builder()
                    .nutrition(8)
                    .saturationModifier(6.5f)
                    .snack()
                    .statusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 9000, 0), .75f)
                    .build()))
    );
    public static final Item VEGETABLE_STEW = registerItem("vegetable_stew",
            new Item(new Item.Settings().maxCount(16).food(new FoodComponent.Builder()
                    .nutrition(6)
                    .saturationModifier(8f)
                    .snack()
                    .build()))
    );
    public static final Item ROTTEN_STEW = registerItem("rotten_stew",
            new Item(new Item.Settings().maxCount(16).food(new FoodComponent.Builder()
                    .nutrition(5)
                    .saturationModifier(.1f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 900, 0), .6f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 900, 0), .6f)
                    .snack()
                    .build()))
    );
    public static final Item NETHERITE_HORSE_ARMOR = registerItem("netherite_horse_armor",
            new AnimalArmorItem(ArmorMaterials.NETHERITE, AnimalArmorItem.Type.EQUESTRIAN, false, new Item.Settings().maxCount(1)));

    public static final Item POTION_POUCH = registerItem("potion_pouch",
            new PotionPouch(new Item.Settings().maxCount(1).component(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT)));

    public static final Item SADDLED_GOAT_HORN = registerItem("saddled_goat_horn",
            new SaddledGoatHorn(new Item.Settings().maxCount(1)));

    public static final Item PET_RECOVERY_COMPASS = registerItem("pet_recovery_compass",
            new PetRecoveryCompass(new Item.Settings().maxCount(1)));

    public static final Item RECALL_CLOCK = registerItem("recall_clock",
            new RecallClock(new Item.Settings().maxCount(1)));

    public static final Item LLAMAS_SPIT = registerItem("llamas_spit",
            new Item(new Item.Settings()));

    public static final Item AMETHYST_DAGGER = registerItem("amethyst_dagger",
            new AmethystDagger(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(LouisOverhaulMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        LouisOverhaulMod.LOGGER.info("Registering Mod Items for " + LouisOverhaulMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(BAT_FANG);
            entries.add(ENDERMITE_HEART);
            entries.add(DECAYING_FLESH);
            entries.add(SANDY_FLESH);
            entries.add(CHILLED_BONE);
            entries.add(LLAMAS_SPIT);
            entries.add(CHILLED_BONE_MEAL);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(NETHERITE_HORSE_ARMOR);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(FISH_STEW);
            entries.add(ROTTEN_STEW);
            entries.add(VEGETABLE_STEW);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(AMETHYST_DAGGER);
            entries.add(POTION_POUCH);
            entries.add(Items.BUNDLE);
            entries.add(SADDLED_GOAT_HORN);
            entries.add(PET_RECOVERY_COMPASS);
            entries.add(RECALL_CLOCK);
        });
    }
}
