package net.louis.overhaulmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.item.PetRecoveryCompass;
import net.louis.overhaulmod.screen.AdvancedFletchingTableScreen;
import net.louis.overhaulmod.screen.ModScreenHandlers;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class LouisOverhaulModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Render Screen
        HandledScreens.register(ModScreenHandlers.ADVANCED_FLETCHING_TABLE_SCREEN_HANDLER, AdvancedFletchingTableScreen::new);

        // Add Block Transparency
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_RAIL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLOW_LANTERN, RenderLayer.getCutout());

        // Add Predicate Texture Transform
        PetRecoveryCompass.registerModelPredicates();

        // Add Arrow Predicate
        addArrowPredicate();
    }

    public void addArrowPredicate() {
        ModelPredicateProviderRegistry.register(
                ModItems.ADVANCED_ARROW,
                Identifier.of(LouisOverhaulMod.MOD_ID, "atid"),
                (stack, world, entity, seed) -> {
                    Item foot = stack.get(ModComponents.ARROW_FOOT);
                    Item shaft = stack.get(ModComponents.ARROW_SHAFT);
                    Item head = stack.get(ModComponents.ARROW_HEAD);
                    if (head == Items.AMETHYST_SHARD && shaft == Items.BLAZE_ROD && foot == Items.PHANTOM_MEMBRANE) return 0.111f;

                    if (head == Items.AMETHYST_SHARD) return 0.001F;
                    if (head == Items.ECHO_SHARD) return 0.002F;

                    if (shaft == Items.BLAZE_ROD) return 0.01f;
                    if (shaft == Items.BREEZE_ROD) return 0.01f;

                    if (foot == Items.PHANTOM_MEMBRANE) return 0.1f;

                    return 0.0f;
                }
        );
    }
}
