package net.louis.overhaulmod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemFrameEntity.class)
public class ItemFrameMixin {

    @Inject(method = "onBreak", at = @At("TAIL"))
    private void onItemFrameDeath(Entity entity, CallbackInfo ci) {
        ItemFrameEntity frame = (ItemFrameEntity) (Object) this;
        World world = frame.getWorld();

        if (!world.isClient && frame.isInvisible()) {
            ItemStack stack = new ItemStack(Items.PHANTOM_MEMBRANE);
            ItemEntity itemEntity = new ItemEntity(world, frame.getX(), frame.getY() + 0.1, frame.getZ(), stack);
            world.spawnEntity(itemEntity);
        }
    }
}
