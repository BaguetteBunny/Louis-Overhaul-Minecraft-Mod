package net.louis.overhaulmod.mixin;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
    @Inject(
            method = "getCollisionShape",
            at = @At("HEAD"),
            cancellable = true
    )
    private void ignoreHorseLeafCollision(
            BlockState state, BlockView world, BlockPos pos, ShapeContext context,
            CallbackInfoReturnable<VoxelShape> cir
    ) {
        if (!(state.getBlock() instanceof LeavesBlock)) return;

        if (context instanceof EntityShapeContext esc) {
            Entity entity = esc.getEntity();
            if (entity instanceof HorseEntity && ((HorseEntity) entity).isTame()) {
                cir.setReturnValue(VoxelShapes.empty());
            }
        }
    }
}



