package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.utils.DespawnManager;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Unit;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Predicate;

@Mixin(RangedWeaponItem.class)
public abstract class RangedWeaponMixin {
    RangedWeaponItem self = (RangedWeaponItem) (Object) this;

    private static boolean hasNoGravity = false;
    private static boolean hasPiercing = false;
    private static boolean hasLightness = false;

    @Inject(method = "shootAll", at = @At("HEAD"), cancellable = true)
    public void shootAll(
            ServerWorld world,
            LivingEntity shooter,
            Hand hand,
            ItemStack stack,
            List<ItemStack> projectiles,
            float speed,
            float divergence,
            boolean critical,
            @Nullable LivingEntity target,
            CallbackInfo ci
    ) {
        float f = EnchantmentHelper.getProjectileSpread(world, stack, shooter, 0.0F);
        float g = projectiles.size() == 1 ? 0.0F : 2.0F * f / (projectiles.size() - 1);
        float h = (projectiles.size() - 1) % 2 * g / 2.0F;
        float i = 1.0F;

        for (int j = 0; j < projectiles.size(); j++) {
            ItemStack itemStack = (ItemStack) projectiles.get(j);
            if (!itemStack.isEmpty()) {
                addArrowAttribute(projectiles.getLast());

                float k = h + i * ((j + 1) / 2) * g;
                i = -i;
                ProjectileEntity projectileEntity = createArrowEntity(world, shooter, stack, itemStack, critical);

                if (hasNoGravity) {
                    projectileEntity.setNoGravity(true);
                    DespawnManager.addDespawnTimerToEntity(projectileEntity, 1200);
                }

                if (hasPiercing && projectileEntity instanceof PersistentProjectileEntity persistent) {
                    ((PersistentProjectileEntityAccessor) persistent).callSetPierceLevel((byte) 5);
                }

                shoot(shooter, projectileEntity, j, speed, divergence, k, target);
                if (hasLightness) {
                    projectileEntity.setVelocity(projectileEntity.getVelocity().normalize().multiply(2.25f));
                }

                world.spawnEntity(projectileEntity);
                stack.damage(getWeaponStackDamage(itemStack), shooter, LivingEntity.getSlotForHand(hand));
                if (stack.isEmpty()) {
                    break;
                }
            }
        }
        ci.cancel();
    }

    @Shadow
    public ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
        ArrowItem arrowItem2 = projectileStack.getItem() instanceof ArrowItem arrowItem ? arrowItem : (ArrowItem) Items.ARROW;
        PersistentProjectileEntity persistentProjectileEntity = arrowItem2.createArrow(world, projectileStack, shooter, weaponStack);
        if (critical) {
            persistentProjectileEntity.setCritical(true);
        }
        return persistentProjectileEntity;
    }

    @Shadow
    public abstract void shoot(
            LivingEntity shooter, ProjectileEntity projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target
    );

    @Shadow
    public int getWeaponStackDamage(ItemStack projectile) {
        return 1;
    }

    public void addArrowAttribute(ItemStack selectedProjectile) {
        if (selectedProjectile.contains(ModComponents.ARROW_SHAFT)) {
            Item arrowHeadMaterial = selectedProjectile.get(ModComponents.ARROW_SHAFT);
            assert arrowHeadMaterial != null;

            if (arrowHeadMaterial.equals(Items.BREEZE_ROD)) hasNoGravity = true;
        }

        if (selectedProjectile.contains(ModComponents.ARROW_HEAD)) {
            Item arrowHeadMaterial = selectedProjectile.get(ModComponents.ARROW_HEAD);
            assert arrowHeadMaterial != null;

            if (arrowHeadMaterial.equals(Items.AMETHYST_SHARD)) hasPiercing = true;
        }

        if (selectedProjectile.contains(ModComponents.ARROW_FOOT)) {
            Item arrowHeadMaterial = selectedProjectile.get(ModComponents.ARROW_FOOT);
            assert arrowHeadMaterial != null;

            if (arrowHeadMaterial.equals(Items.PHANTOM_MEMBRANE)) hasLightness = true;
        }
    }

    @Inject(method = "getProjectile", at = @At("HEAD"), cancellable = true)
    private static void reduceArrowCount(ItemStack stack, ItemStack projectileStack, LivingEntity shooter, boolean multishot, CallbackInfoReturnable<ItemStack> cir) {
        int i = !multishot && !shooter.isInCreativeMode() && shooter.getWorld() instanceof ServerWorld serverWorld
                ? EnchantmentHelper.getAmmoUse(serverWorld, stack, projectileStack, 1)
                : 0;

        if (hasNoGravity || hasPiercing || hasLightness) {
            hasLightness = false;
            hasPiercing = false;
            hasNoGravity = false;
            i = 1;
        }

        if (i > projectileStack.getCount()) {
            cir.setReturnValue(ItemStack.EMPTY);
            cir.cancel();
        } else if (i == 0) {
            ItemStack itemStack = projectileStack.copyWithCount(1);
            itemStack.set(DataComponentTypes.INTANGIBLE_PROJECTILE, Unit.INSTANCE);
            cir.setReturnValue(itemStack);
            cir.cancel();
        } else {
            ItemStack itemStack = projectileStack.split(i);
            if (projectileStack.isEmpty() && shooter instanceof PlayerEntity playerEntity) {
                playerEntity.getInventory().removeOne(projectileStack);
            }

            cir.setReturnValue(itemStack);
            cir.cancel();
        }
    }
}
