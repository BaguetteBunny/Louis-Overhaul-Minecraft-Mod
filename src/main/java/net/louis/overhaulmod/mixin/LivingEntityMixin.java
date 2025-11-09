package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.effect.ModEffects;
import net.louis.overhaulmod.utils.TeleportUtils;
import net.minecraft.block.SkullBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "damage", at = @At("HEAD"))
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity attacker = source.getAttacker();
        Entity target = (Entity) (Object) this;

        if (attacker instanceof EndermiteEntity && target instanceof ServerPlayerEntity player) {
            Random random = new Random();
            if (random.nextBoolean()) {TeleportUtils.chorusTeleport(player);}
        }
    }

    @Inject(method = "jump", at = @At("HEAD"), cancellable = true)
    private void overrideJump(CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self.hasStatusEffect(ModEffects.GROUNDED)) {
            ci.cancel();
        }
    }

    @Inject(method = "getAttackDistanceScalingFactor", at = @At("RETURN"), cancellable = true)
    private void injectedHeadDetectionReduction(Entity entity, CallbackInfoReturnable<Double> cir) {
        LivingEntity self = (LivingEntity) (Object) this;
        double original = cir.getReturnValue();
        ItemStack head = self.getEquippedStack(EquipmentSlot.HEAD);

        if (entity != null) {
            if (entity.getType().equals(EntityType.SKELETON) && head.isOf(Items.SKELETON_SKULL)
                    || entity.getType().equals(EntityType.STRAY) && head.isOf(Items.SKELETON_SKULL)
                    || entity.getType().equals(EntityType.BOGGED) && head.isOf(Items.SKELETON_SKULL)
                    || entity.getType().equals(EntityType.ZOMBIE) && head.isOf(Items.ZOMBIE_HEAD)
                    || entity.getType().equals(EntityType.HUSK) && head.isOf(Items.ZOMBIE_HEAD)
                    || entity.getType().equals(EntityType.DROWNED) && head.isOf(Items.ZOMBIE_HEAD)
                    || entity.getType().equals(EntityType.ZOMBIE_VILLAGER) && head.isOf(Items.ZOMBIE_HEAD)
                    || entity.getType().equals(EntityType.PIGLIN) && head.isOf(Items.PIGLIN_HEAD)
                    || entity.getType().equals(EntityType.PIGLIN_BRUTE) && head.isOf(Items.PIGLIN_HEAD)
                    || entity.getType().equals(EntityType.ZOMBIFIED_PIGLIN) && head.isOf(Items.PIGLIN_HEAD)
                    || entity.getType().equals(EntityType.CREEPER) && head.isOf(Items.CREEPER_HEAD)) {
                cir.setReturnValue(.0);
            } else {
                cir.setReturnValue(original);
            }
        }
    }
}

