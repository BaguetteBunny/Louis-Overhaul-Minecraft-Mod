package net.louis.overhaulmod.cauldron.color;

import com.mojang.serialization.MapCodec;
import net.louis.overhaulmod.utils.FluidType;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;

public class ColoredWaterCauldronBlock extends AbstractCauldronBlock {
    @Override protected MapCodec<? extends AbstractCauldronBlock> getCodec() {return null;}
    public static final EnumProperty<FluidType> FLUID_TYPE = EnumProperty.of("fluid_type", FluidType.class);
    public static final CauldronBehavior.CauldronBehaviorMap COLORED_WATER_BEHAVIOR = CauldronBehavior.createMap("colored_water_cauldron_behavior");

    public ColoredWaterCauldronBlock(Settings settings) {
        super(settings, COLORED_WATER_BEHAVIOR);
        this.setDefaultState(this.stateManager.getDefaultState().with(FLUID_TYPE, FluidType.WHITE));
    }

    @Override
    public boolean isFull(BlockState state) {
        return true;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FLUID_TYPE);
    }

    @Override
    protected double getFluidHeight(BlockState state) {
        return 0.8125;
    }
}
