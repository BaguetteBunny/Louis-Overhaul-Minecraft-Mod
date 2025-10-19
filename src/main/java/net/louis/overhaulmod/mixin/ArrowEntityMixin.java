package net.louis.overhaulmod.mixin;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.Entity;
import net.minecraft.potion.Potion;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;

@Mixin(ArrowEntity.class)
public abstract class ArrowEntityMixin {

    /**
     * Overwrites the vanilla onHit method to apply both direct and AoE effects.
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
