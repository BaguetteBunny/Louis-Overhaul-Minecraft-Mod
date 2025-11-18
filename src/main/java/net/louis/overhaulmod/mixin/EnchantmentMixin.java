package net.louis.overhaulmod.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Inject(method = "canBeCombined", at = @At("HEAD"), cancellable = true)
    private static void removeAllMutexesExceptSilkTouchFortune(RegistryEntry<Enchantment> first, RegistryEntry<Enchantment> second, CallbackInfoReturnable<Boolean> cir) {
        if (first.equals(second)) return;

        String firstName = first.getKey().get().getValue().getPath();
        String secondName = second.getKey().get().getValue().getPath();

        if ((firstName.equals("silk_touch") && secondName.equals("fortune")) || (firstName.equals("fortune") && secondName.equals("silk_touch"))) {
            cir.setReturnValue(false);
            return;
        }

        cir.setReturnValue(true);
    }

    @Inject(method = "modifyDamageProtection", at = @At("HEAD"), cancellable = true)
    private void limitProtectionToMeleeOnly(ServerWorld world, int level, ItemStack stack, Entity user, DamageSource damageSource, MutableFloat damageProtection, CallbackInfo ci) {
        Enchantment thisEnchant = (Enchantment)(Object)this;

        String enchantId = world.getRegistryManager()
                .get(RegistryKeys.ENCHANTMENT)
                .getId(thisEnchant)
                .getPath();

        if (enchantId.equals("protection")) {
            if (!isMeleeDamage(damageSource)) {
                ci.cancel();
            }
        }
    }

    private boolean isMeleeDamage(DamageSource source) {
        return source.getAttacker() != null &&
                source.getSource() == source.getAttacker() &&
                !source.isIn(DamageTypeTags.IS_PROJECTILE) &&
                !source.isIn(DamageTypeTags.IS_FIRE) &&
                !source.isIn(DamageTypeTags.IS_EXPLOSION) &&
                !source.isIn(DamageTypeTags.BYPASSES_ARMOR);
    }
}