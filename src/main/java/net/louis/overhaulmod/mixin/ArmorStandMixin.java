package net.louis.overhaulmod.mixin;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorStandEntity.class)
public class ArmorStandMixin {

    @Inject(method = "onBreak", at = @At("TAIL"))
    private void onArmorStandBreak(ServerWorld world, DamageSource source, CallbackInfo ci) {
        ArmorStandEntity stand = (ArmorStandEntity) (Object) this;
        if (stand.shouldShowArms()) {
            ItemStack stack = new ItemStack(Items.STICK);
            ItemEntity itemEntity = new ItemEntity(world, stand.getX(), stand.getY() + 0.5, stand.getZ(), stack);
            world.spawnEntity(itemEntity);
        }
        if (stand.isInvisible()) {
            ItemStack stack = new ItemStack(Items.PHANTOM_MEMBRANE);
            ItemEntity itemEntity = new ItemEntity(world, stand.getX(), stand.getY() + 0.5, stand.getZ(), stack);
            world.spawnEntity(itemEntity);

        }
    }
}
