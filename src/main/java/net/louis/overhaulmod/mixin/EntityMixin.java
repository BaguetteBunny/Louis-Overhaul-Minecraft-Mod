package net.louis.overhaulmod.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "collidesWithStateAtPos", at = @At("HEAD"), cancellable = true)
    private void noLeafCollision(BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        Entity self = (Entity)(Object)this;

        if (self instanceof HorseEntity && state.isIn(BlockTags.LEAVES)) {
            cir.setReturnValue(false); // Skip collision with leaves
        }
    }
}
