package net.louis.overhaulmod.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class GroundedEffect extends StatusEffect {
    public GroundedEffect(StatusEffectCategory category, int color){
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.isOnGround() && !(entity instanceof PlayerEntity player && disallowedPlayerStates(player))) {
            entity.addVelocity(0, -0.025, 0);
        }

        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    private boolean disallowedPlayerStates(PlayerEntity player) {
        return player.isSpectator()
                || player.isSwimming()
                || player.isPushedByFluids()
                || player.isFallFlying()
                || player.isUsingRiptide()
                || player.getAbilities().allowFlying;
    }
}
