package net.louis.overhaulmod.mixin;

import net.louis.overhaulmod.component.ModComponents;
import net.minecraft.component.ComponentMap;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArrowEntity.class)
public abstract class ArrowEntityMixin {
    ArrowEntity arrow = (ArrowEntity) (Object) this;
    ComponentMap components = arrow.getItemStack().getComponents();

    @Inject(method = "tick", at = @At("TAIL"))
    private void injectHomingLogic(CallbackInfo ci) {
        if (arrow.getWorld().isClient || arrow.isOnGround()) return;
        if (!components.contains(ModComponents.ARROW_HEAD) || !Items.ECHO_SHARD.equals(components.get(ModComponents.ARROW_HEAD))) return;

        LivingEntity target = arrow.getWorld().getClosestEntity(
                LivingEntity.class,
                TargetPredicate.DEFAULT,
                (LivingEntity) arrow.getOwner(),
                arrow.getX(), arrow.getY(), arrow.getZ(),
                arrow.getBoundingBox().expand(16.0)
        );

        if (target != null && target != arrow.getOwner()) {
            Vec3d toTarget = target.getPos().add(0, target.getHeight() * 0.5, 0).subtract(arrow.getPos()).normalize();
            Vec3d newVel = arrow.getVelocity().normalize().lerp(toTarget, 0.3).normalize().multiply(arrow.getVelocity().length());
            arrow.setVelocity(newVel);
        }
    }

    /**
     * @author BaguetteBunny @ LouisOverhaulMod
     * @reason Tipped arrows drop AoE Cloud
     */
    @Overwrite
    public void onHit(LivingEntity target) {
        ArrowEntity self = (ArrowEntity)(Object)this;
        World world = self.getWorld();

        Entity entity = self.getEffectCause();
        PotionContentsComponent potionContentsComponent = ((ArrowEntityAccessor) self).callgetPotionContents();
        if (potionContentsComponent.potion().isPresent()) {
            for (StatusEffectInstance statusEffectInstance : ((Potion)((RegistryEntry)potionContentsComponent.potion().get()).value()).getEffects()) {
                target.addStatusEffect(
                        new StatusEffectInstance(
                                statusEffectInstance.getEffectType(),
                                Math.max(statusEffectInstance.mapDuration(i -> i / 8), 1),
                                statusEffectInstance.getAmplifier(),
                                statusEffectInstance.isAmbient(),
                                statusEffectInstance.shouldShowParticles()
                        ),
                        entity
                );

                AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(world, target.getX(), target.getY(), target.getZ());
                cloud.setOwner(self.getOwner() instanceof LivingEntity living ? living : null);
                cloud.setRadius(3.0F);
                cloud.setRadiusOnUse(-0.5F);
                cloud.setWaitTime(0);
                cloud.setDuration(20+Math.max(statusEffectInstance.mapDuration(i -> i / 8), 1)/2);
                cloud.setRadiusGrowth(-cloud.getRadius() / (float)cloud.getDuration());
                cloud.setPotionContents(potionContentsComponent);
                world.spawnEntity(cloud);
            }
        }

        for (StatusEffectInstance statusEffectInstance : potionContentsComponent.customEffects()) {
            target.addStatusEffect(statusEffectInstance, entity);
        }
    }
}
