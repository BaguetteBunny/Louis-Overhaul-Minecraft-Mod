package net.louis.overhaulmod;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.block.entity.ModBlockEntities;
import net.louis.overhaulmod.cauldron.ModCauldron;
import net.louis.overhaulmod.component.CustomBundleTooltipComponent;
import net.louis.overhaulmod.component.CustomBundleTooltipData;
import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.effect.ModEffects;
import net.louis.overhaulmod.events.ModUseEvents;
import net.louis.overhaulmod.events.ModUseStewEvents;
import net.louis.overhaulmod.fluid.ModFluids;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.potion.ModPotions;
import net.louis.overhaulmod.recipe.ModRecipes;
import net.louis.overhaulmod.screen.ModScreenHandlers;
import net.louis.overhaulmod.sound.ModSounds;
import net.louis.overhaulmod.utils.DespawnManager;
import net.louis.overhaulmod.utils.EnchantmentCapRegistry;
import net.louis.overhaulmod.utils.GlowManager;
import net.louis.overhaulmod.utils.ModLootTableModifiers;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class LouisOverhaulMod implements ModInitializer {
	public static final String MOD_ID = "louis-overhaul-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		registerCustomBundleTooltip();
		EnchantmentCapRegistry.register();

		ModFluids.register();
		ModSounds.registerSounds();
		ModComponents.registerDataComponentTypes();
		ModEffects.registerEffects();
		ModPotions.registerPotions();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModCauldron.registerBehaviors();
		ModRecipes.registerRecipes();

		ModLootTableModifiers.modifyLootTables();
		ModLootTableModifiers.replaceLootTables();

		ModUseEvents.registerMain();
		ModUseEvents.registerProjectileItems();
		ModUseStewEvents.registerStew();

		registerDispenserProjectles();

		tickGlobal();
		replaceFletcherPOI();
	}

	private void registerCustomBundleTooltip() {
		TooltipComponentCallback.EVENT.register((tooltipData) -> {
			if (tooltipData instanceof CustomBundleTooltipData custom) {
				return new CustomBundleTooltipComponent(custom.contents());
			}
			return null;
		});
	}

	private void tickGlobal() {
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			GlowManager.tick();
			DespawnManager.tick();
		});
	}

	private void replaceFletcherPOI() {
		Set<BlockState> states = ImmutableSet.copyOf(ModBlocks.ADVANCED_FLETCHING_TABLE.getStateManager().getStates());
		PointOfInterestHelper.register(Identifier.ofVanilla("fletcher"), 1, 1, states);
	}

	private void registerDispenserProjectles() {
		DispenserBlock.registerProjectileBehavior(ModItems.ADVANCED_ARROW);
	}
}