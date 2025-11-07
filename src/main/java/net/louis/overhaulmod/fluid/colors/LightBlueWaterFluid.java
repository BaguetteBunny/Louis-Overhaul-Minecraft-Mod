package net.louis.overhaulmod.fluid.colors;

import net.louis.overhaulmod.fluid.BaseColoredFluid;
import net.louis.overhaulmod.fluid.ModFluids;
import net.louis.overhaulmod.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.*;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;

public abstract class LightBlueWaterFluid extends BaseColoredFluid {
    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_LIGHT_BLUE_WATER;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.STILL_LIGHT_BLUE_WATER;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.LIGHT_BLUE_WATER_BUCKET;
    }

    @Override
    public BlockState toBlockState(FluidState state) {
        return ModFluids.LIGHT_BLUE_WATER_BLOCK.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == ModFluids.STILL_LIGHT_BLUE_WATER || fluid == ModFluids.FLOWING_LIGHT_BLUE_WATER;
    }

    @Override
    public int getSPColor() {
        return 0x3AB3DA;
    }

    public static class Flowing extends LightBlueWaterFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }
    }

    public static class Still extends LightBlueWaterFluid {
        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}
