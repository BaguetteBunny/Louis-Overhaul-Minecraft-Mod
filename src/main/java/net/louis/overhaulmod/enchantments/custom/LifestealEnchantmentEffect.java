package net.louis.overhaulmod.enchantments.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

public record LifestealEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<LifestealEnchantmentEffect> CODEC = MapCodec.unit(LifestealEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        LivingEntity attacker = context.owner();

        int cappedLevel = Math.min(level, 5);
        int denom = 12 - cappedLevel * 2;
        if (denom < 1) denom = 1;
        if (world.random.nextInt(denom) == 0) {
            float healAmount = 2.f;
            float newHealth = Math.min(attacker.getHealth() + healAmount, attacker.getMaxHealth());
            attacker.setHealth(newHealth);

            if (attacker instanceof PlayerEntity player)
                world.playSound(player, attacker.getX(), attacker.getY(), attacker.getZ(), SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1f, 2.f);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
