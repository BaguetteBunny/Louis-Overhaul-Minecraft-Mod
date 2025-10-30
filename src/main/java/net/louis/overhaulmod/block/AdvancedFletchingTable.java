package net.louis.overhaulmod.block;

import com.mojang.serialization.MapCodec;
import net.louis.overhaulmod.block.entity.AdvancedFletchingTableBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AdvancedFletchingTable extends BlockWithEntity implements BlockEntityProvider {
    public static final MapCodec<AdvancedFletchingTable> CODEC = AdvancedFletchingTable.createCodec(AdvancedFletchingTable::new);

    public AdvancedFletchingTable(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AdvancedFletchingTableBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof AdvancedFletchingTableBlockEntity) {
                ItemScatterer.spawn(world, pos, ((AdvancedFletchingTableBlockEntity) blockEntity));
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(world.getBlockEntity(pos) instanceof AdvancedFletchingTableBlockEntity fbe && !world.isClient()) {
            player.openHandledScreen(fbe);
        }
        return ActionResult.SUCCESS;
    }

}
