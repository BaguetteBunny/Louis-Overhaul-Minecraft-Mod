package net.louis.overhaulmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.louis.overhaulmod.block.ModBlocks;
import net.louis.overhaulmod.item.PetRecoveryCompass;
import net.minecraft.client.render.RenderLayer;

public class LouisOverhaulModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Add Block Transparency
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_RAIL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLOW_LANTERN, RenderLayer.getCutout());

        // Add Predicate Texture Transform
        PetRecoveryCompass.registerModelPredicates();
    }
}
