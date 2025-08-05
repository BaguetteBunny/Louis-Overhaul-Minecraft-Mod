package net.louis.overhaulmod;

import net.fabricmc.api.ModInitializer;
import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.effect.ModEffects;
import net.louis.overhaulmod.events.ModUseEvents;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.potion.ModPotions;
import net.louis.overhaulmod.sound.ModSounds;
import net.louis.overhaulmod.utils.ModLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LouisOverhaulMod implements ModInitializer {
	public static final String MOD_ID = "louis-overhaul-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModSounds.registerSounds();
		ModComponents.registerDataComponentTypes();
		ModEffects.registerEffects();
		ModPotions.registerPotions();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModLootTableModifiers.modifyLootTables();
		ModLootTableModifiers.replaceLootTables();

		ModUseEvents.registerMain();
		ModUseEvents.registerStew();
	}
}