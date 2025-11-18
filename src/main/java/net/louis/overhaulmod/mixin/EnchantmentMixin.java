package net.louis.overhaulmod.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.BreezeEntity;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
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

        if (enchantId.equals("protection") && !isMeleeDamage(damageSource)) ci.cancel();

        if (enchantId.equals("blast_protection")) {
            if (damageSource.isIn(DamageTypeTags.IS_EXPLOSION)) return;
            if (isMagicDamage(damageSource)) {
                float currentProtection = damageProtection.getValue();
                float additionalProtection = level * 2.0f;
                damageProtection.setValue(currentProtection + additionalProtection);
                ci.cancel();
                return;
            }
            ci.cancel();
        }

        if (enchantId.equals("fire_protection")) {
            if (damageSource.isIn(DamageTypeTags.IS_FIRE)) return;

            if (isEnvironmentalDamage(damageSource)) {
                float currentProtection = damageProtection.getValue();
                float additionalProtection = level * 2.0f; // Same as fire protection
                damageProtection.setValue(currentProtection + additionalProtection);
                ci.cancel();
                return;
            }
            ci.cancel();
        }
    }

    @Inject(method = "modifyDamage", at = @At("HEAD"), cancellable = true)
    private void limitSharpnessToIllagers(ServerWorld world, int level, ItemStack stack, Entity user, DamageSource damageSource, MutableFloat damage, CallbackInfo ci) {
        Enchantment thisEnchant = (Enchantment)(Object)this;
        Entity target = user;

        String enchantId = world.getRegistryManager()
                .get(RegistryKeys.ENCHANTMENT)
                .getId(thisEnchant)
                .getPath();

        if (enchantId.equals("sharpness")) {
            if (isIllager(target)) {
                float currentDamage = damage.getValue();
                float bonusDamage = 2.5f * level;
                damage.setValue(currentDamage + bonusDamage);
            }
            ci.cancel();
        }

        if (enchantId.equals("bane_of_arthropods")) {
            if (isArthropodOrFireMob(target)) {
                float currentDamage = damage.getValue();
                float bonusDamage = 2.5f * level;
                damage.setValue(currentDamage + bonusDamage);
            }
            ci.cancel();
        }

        if (enchantId.equals("impaling")) {
            if (shouldImpalingWork(world, target)) {
                float currentDamage = damage.getValue();
                float bonusDamage = 2.5f * level;
                damage.setValue(currentDamage + bonusDamage);
            }
            ci.cancel();
        }
    }

    private boolean isArthropodOrFireMob(Entity entity) {
        if (entity.getType().isIn(EntityTypeTags.SENSITIVE_TO_BANE_OF_ARTHROPODS)) return true;

        return entity instanceof BlazeEntity || entity instanceof BreezeEntity ||
                entity.getType() == EntityType.BLAZE || entity.getType() == EntityType.BREEZE;
    }

    private boolean shouldImpalingWork(ServerWorld world, Entity target) {
        if (target.isTouchingWater() || target.isSubmergedInWater()) return true;

        if (world.isRaining()) {
            BlockPos headPos = BlockPos.ofFloored(target.getX(), target.getEyeY(), target.getZ());
            if (world.isSkyVisible(headPos)) return true;
        }
        return false;
    }

    private boolean isIllager(Entity entity) {
        return entity instanceof IllagerEntity ||
                entity.getType() == EntityType.PILLAGER ||
                entity.getType() == EntityType.VINDICATOR ||
                entity.getType() == EntityType.EVOKER ||
                entity.getType() == EntityType.VEX ||
                entity.getType() == EntityType.WITCH ||
                entity.getType() == EntityType.ILLUSIONER ||
                entity.getType() == EntityType.RAVAGER;
    }

    private boolean isMeleeDamage(DamageSource source) {
        return source.getAttacker() != null &&
                source.getSource() == source.getAttacker() &&
                !source.isIn(DamageTypeTags.IS_PROJECTILE) &&
                !source.isIn(DamageTypeTags.IS_FIRE) &&
                !source.isIn(DamageTypeTags.IS_EXPLOSION) &&
                !source.isIn(DamageTypeTags.BYPASSES_ARMOR);
    }

    private boolean isMagicDamage(DamageSource source) {
        return source.isIn(DamageTypeTags.WITCH_RESISTANT_TO) ||
                source.getName().equals("magic") ||
                source.getName().equals("indirectMagic") ||
                source.getName().equals("thorns") ||
                (source.getSource() != null &&
                        (source.getSource().getType().toString().contains("guardian") ||
                                source.getSource().getType().toString().contains("evoker_fangs")));
    }

    private boolean isEnvironmentalDamage(DamageSource source) {
        String name = source.getName();
        return name.equals("cactus") ||
                name.equals("sweetBerryBush") ||
                name.equals("freeze") ||
                name.equals("stalagmite") ||
                name.equals("fallingBlock") ||
                name.equals("flyIntoWall") ||
                name.equals("outOfWorld") ||
                name.equals("drown") ||
                name.equals("starve") ||
                (source.getAttacker() == null && source.getSource() == null);
    }
}