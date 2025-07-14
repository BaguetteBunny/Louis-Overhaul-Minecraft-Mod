package net.louis.overhaulmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.louis.overhaulmod.block.ModBlocks;
import net.minecraft.client.render.RenderLayer;

public class LouisOverhaulModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Add Transparency
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_RAIL, RenderLayer.getCutout());
    }
}
