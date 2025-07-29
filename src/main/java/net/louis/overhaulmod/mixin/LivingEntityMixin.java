package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.effect.ModEffects;
import net.louis.overhaulmod.utils.TeleportUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
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
}

