package net.louis.overhaulmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item BAT_FANG = registerItem("bat_fang", new Item(new Item.Settings()));
    public static final Item ENDERMITE_HEART = registerItem("endermite_heart", new Item(new Item.Settings()));
    public static final Item NETHERITE_HORSE_ARMOR = registerItem("netherite_horse_armor",
            new AnimalArmorItem(ArmorMaterials.NETHERITE, AnimalArmorItem.Type.EQUESTRIAN, false, new Item.Settings().maxCount(1)));
    public static final Item POTION_POUCH = registerItem("potion_pouch",
            new PotionPouchItem(new Item.Settings().maxCount(1).component(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(LouisOverhaulMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        LouisOverhaulMod.LOGGER.info("Registering Mod Items for " + LouisOverhaulMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(BAT_FANG);
            entries.add(ENDERMITE_HEART);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(NETHERITE_HORSE_ARMOR);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(POTION_POUCH);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(Items.BUNDLE);
        });
    }
}
