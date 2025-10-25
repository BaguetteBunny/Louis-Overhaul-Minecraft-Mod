package net.louis.overhaulmod.effect;

import net.louis.overhaulmod.LouisOverhaulMod;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> GROUNDED = registerStatusEffect("grounded",
            new GroundedEffect(StatusEffectCategory.HARMFUL, 0xa67655)
                    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                            Identifier.of(LouisOverhaulMod.MOD_ID, "grounded"), -0.25f,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));


    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(LouisOverhaulMod.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        LouisOverhaulMod.LOGGER.info("Registering Mod Effects for " + LouisOverhaulMod.MOD_ID);
    }
}
