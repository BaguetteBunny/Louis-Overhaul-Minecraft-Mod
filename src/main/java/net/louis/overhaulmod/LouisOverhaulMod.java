package net.louis.overhaulmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.effect.ModEffects;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.potion.ModPotions;
import net.louis.overhaulmod.utils.DelayedActionScheduler;
import net.louis.overhaulmod.utils.ModLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LouisOverhaulMod implements ModInitializer {
	public static final String MOD_ID = "louis-overhaul-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ServerTickEvents.END_SERVER_TICK.register(server -> {DelayedActionScheduler.tick();});
		ModEffects.registerEffects();
		ModPotions.registerPotions();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModLootTableModifiers.modifyLootTables();
	}
}