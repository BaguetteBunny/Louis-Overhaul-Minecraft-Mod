package net.louis.overhaulmod.mixin;

import com.mojang.authlib.GameProfile;
import net.louis.overhaulmod.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;
import java.util.List;

import static net.louis.overhaulmod.item.custom.AmethystDagger.DROPPABLES;
import static net.louis.overhaulmod.item.custom.AmethystDagger.UNDROPPABLES;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {

    @Inject(method = "getCollisionShape", at = @At("HEAD"), cancellable = true)
    private void ignoreHorseLeafCollision(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (state.getBlock() instanceof LeavesBlock && context instanceof EntityShapeContext esc) {
            Entity entity = esc.getEntity();
            if (entity instanceof HorseEntity && ((HorseEntity) entity).isTame()) {
                cir.setReturnValue(VoxelShapes.empty());
            }
        }
    }

    @Inject(method = "onBlockBreakStart", at = @At("HEAD"), cancellable = true)
    protected void useAmethystDagger(BlockState state, World world, BlockPos pos, PlayerEntity player, CallbackInfo ci) {
        Block bl = state.getBlock();
        ItemStack stack = player.getMainHandStack();
        if (!(world instanceof ServerWorld)) return;

        if (stack.getItem().equals(ModItems.AMETHYST_DAGGER)) {
            if (UNDROPPABLES.contains(bl)) {
                stack.damage(1, player, EquipmentSlot.MAINHAND);
                world.breakBlock(pos, false, player);
                player.incrementStat(Stats.MINED.getOrCreateStat(bl));
                player.addExhaustion(0.005F);
                ci.cancel();
            }

            if (DROPPABLES.contains(bl)) {
                stack.damage(1, player, EquipmentSlot.MAINHAND);
                ItemStack drop = new ItemStack(bl);

                if (state.getBlock() instanceof PlayerSkullBlock) {
                    BlockEntity be = world.getBlockEntity(pos);
                    ProfileComponent profileComponent = getProfileComponent(be);
                    world.breakBlock(pos, false, player);
                    if (profileComponent != null) drop.set(DataComponentTypes.PROFILE, profileComponent);
                }

                world.breakBlock(pos, false, player);
                world.spawnEntity(new ItemEntity(
                        world,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        drop
                ));

                player.incrementStat(Stats.MINED.getOrCreateStat(bl));
                player.addExhaustion(0.005f);
                ci.cancel();
            }
        }
    }

    private static @Nullable ProfileComponent getProfileComponent(BlockEntity be) {
        ProfileComponent profileComponent = null;
        if (be instanceof SkullBlockEntity skull) {

            ProfileComponent stored = skull.getOwner();
            if (stored != null) {
                profileComponent = new ProfileComponent(
                        stored.name(),
                        stored.id(),
                        stored.properties()
                );
            }
        }
        return profileComponent;
    }
}



