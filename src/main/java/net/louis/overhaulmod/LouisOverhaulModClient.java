package net.louis.overhaulmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.component.CustomBundleContentsComponent;
import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.fluid.ModFluids;
import net.louis.overhaulmod.item.ModItems;
import net.louis.overhaulmod.item.custom.BigBundleItem;
import net.louis.overhaulmod.item.custom.PetRecoveryCompass;
import net.louis.overhaulmod.screen.AdvancedFletchingTableScreen;
import net.louis.overhaulmod.screen.ModScreenHandlers;
import net.louis.overhaulmod.screen.SawmillScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import static net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler.coloredWater;

@Environment(EnvType.CLIENT)
public class LouisOverhaulModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Render Screen
        HandledScreens.register(ModScreenHandlers.ADVANCED_FLETCHING_TABLE_SCREEN_HANDLER, AdvancedFletchingTableScreen::new);
        HandledScreens.register(ModScreenHandlers.SAWMILL_SCREEN_HANDLER, SawmillScreen::new);

        // Add Block Transparency
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_RAIL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLOW_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SAWMILL, RenderLayer.getCutout());

        // Add Predicate Texture Transform
        PetRecoveryCompass.registerModelPredicates();
        BigBundleItem.registerBigBundlePredicate(ModItems.LARGE_BUNDLE);
        BigBundleItem.registerBigBundlePredicate(ModItems.MASSIVE_BUNDLE);
        BigBundleItem.registerBigBundlePredicate(ModItems.PIONEER_POUCH);

        addArrowPredicate();

        addColoredWater();
    }

    public void addArrowPredicate() {
        ModelPredicateProviderRegistry.register(
                ModItems.ADVANCED_ARROW,
                Identifier.of(LouisOverhaulMod.MOD_ID, "atid"),
                (stack, world, entity, seed) -> {
                    int total = 0;
                    Item foot = stack.get(ModComponents.ARROW_FOOT);
                    Item shaft = stack.get(ModComponents.ARROW_SHAFT);
                    Item head = stack.get(ModComponents.ARROW_HEAD);

                    if (head == Items.AMETHYST_SHARD) total += 1;
                    if (head == Items.ECHO_SHARD) total += 2;
                    if (head == Items.PRISMARINE_SHARD) total += 3;

                    if (shaft == Items.BLAZE_ROD) total += 10;
                    if (shaft == Items.BREEZE_ROD) total += 20;

                    if (foot == Items.PHANTOM_MEMBRANE) total += 100;
                    if (foot == Items.DRIED_KELP) total += 200;
                    if (foot == Items.ARMADILLO_SCUTE) total += 300;

                    return Math.round((total / 1000f) * 1000f) / 1000f;
                }
        );
    }

    public void addColoredWater() {
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_WHITE_WATER, ModFluids.FLOWING_WHITE_WATER, coloredWater(0xF9FFFE));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_ORANGE_WATER, ModFluids.FLOWING_ORANGE_WATER, coloredWater(0xF9801D));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_MAGENTA_WATER, ModFluids.FLOWING_MAGENTA_WATER, coloredWater(0xC74EBD));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_LIGHT_BLUE_WATER, ModFluids.FLOWING_LIGHT_BLUE_WATER, coloredWater(0x3AB3DA));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_YELLOW_WATER, ModFluids.FLOWING_YELLOW_WATER, coloredWater(0xFED83D));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_LIME_WATER, ModFluids.FLOWING_LIME_WATER, coloredWater(0x80C71F));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_PINK_WATER, ModFluids.FLOWING_PINK_WATER, coloredWater(0xF38BAA));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_GRAY_WATER, ModFluids.FLOWING_GRAY_WATER, coloredWater(0x474F52));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_LIGHT_GRAY_WATER, ModFluids.FLOWING_LIGHT_GRAY_WATER, coloredWater(0x9D9D97));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_CYAN_WATER, ModFluids.FLOWING_CYAN_WATER, coloredWater(0x169C9C));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_PURPLE_WATER, ModFluids.FLOWING_PURPLE_WATER, coloredWater(0x8932B8));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_BLUE_WATER, ModFluids.FLOWING_BLUE_WATER, coloredWater(0x3C44AA));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_BROWN_WATER, ModFluids.FLOWING_BROWN_WATER, coloredWater(0x835432));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_GREEN_WATER, ModFluids.FLOWING_GREEN_WATER, coloredWater(0x5E7C16));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_RED_WATER, ModFluids.FLOWING_RED_WATER, coloredWater(0xB02E26));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_BLACK_WATER, ModFluids.FLOWING_BLACK_WATER, coloredWater(0x1D1D21));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluids.STILL_WHITE_WATER, ModFluids.FLOWING_WHITE_WATER,
                ModFluids.STILL_ORANGE_WATER, ModFluids.FLOWING_ORANGE_WATER,
                ModFluids.STILL_MAGENTA_WATER, ModFluids.FLOWING_MAGENTA_WATER,
                ModFluids.STILL_LIGHT_BLUE_WATER, ModFluids.FLOWING_LIGHT_BLUE_WATER,
                ModFluids.STILL_YELLOW_WATER, ModFluids.FLOWING_YELLOW_WATER,
                ModFluids.STILL_LIME_WATER, ModFluids.FLOWING_LIME_WATER,
                ModFluids.STILL_PINK_WATER, ModFluids.FLOWING_PINK_WATER,
                ModFluids.STILL_GRAY_WATER, ModFluids.FLOWING_GRAY_WATER,
                ModFluids.STILL_LIGHT_GRAY_WATER, ModFluids.FLOWING_LIGHT_GRAY_WATER,
                ModFluids.STILL_CYAN_WATER, ModFluids.FLOWING_CYAN_WATER,
                ModFluids.STILL_PURPLE_WATER, ModFluids.FLOWING_PURPLE_WATER,
                ModFluids.STILL_BLUE_WATER, ModFluids.FLOWING_BLUE_WATER,
                ModFluids.STILL_BROWN_WATER, ModFluids.FLOWING_BROWN_WATER,
                ModFluids.STILL_GREEN_WATER, ModFluids.FLOWING_GREEN_WATER,
                ModFluids.STILL_RED_WATER, ModFluids.FLOWING_RED_WATER,
                ModFluids.STILL_BLACK_WATER, ModFluids.FLOWING_BLACK_WATER);

    }
}
