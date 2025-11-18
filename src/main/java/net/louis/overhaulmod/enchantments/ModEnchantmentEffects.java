package net.louis.overhaulmod.enchantments;

import com.mojang.serialization.MapCodec;
import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.enchantments.custom.LifestealEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> VAMPIRISM =
            registerEntityEffect("vampirism", LifestealEnchantmentEffect.CODEC);


    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(LouisOverhaulMod.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects() {
        LouisOverhaulMod.LOGGER.info("Registering Mod Enchantment Effects for " + LouisOverhaulMod.MOD_ID);
    }
}
