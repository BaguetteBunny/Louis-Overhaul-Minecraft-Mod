package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.component.ModComponents;
import net.louis.overhaulmod.utils.DespawnManager;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(RangedWeaponItem.class)
public abstract class RangedWeaponMixin {
    RangedWeaponItem self = (RangedWeaponItem) (Object) this;

    private boolean hasNoGravity = false;

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

                projectileEntity.setNoGravity(hasNoGravity);
                if (hasNoGravity) DespawnManager.addDespawnTimerToEntity(projectileEntity, 200);

                shoot(shooter, projectileEntity, j, speed, divergence, k, target);
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

            if (arrowHeadMaterial.equals(Items.BREEZE_ROD)) hasNoGravity = true;
        }
    }
}
