package net.louis.overhaulmod.effect;

import net.louis.overhaulmod.LouisOverhaulMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.xml.sax.helpers.AttributesImpl;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;

import java.util.UUID;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;

public class GigantismEffect extends StatusEffect {
    private static final Identifier MODIFIER_ID = Identifier.of(LouisOverhaulMod.MOD_ID, "gigantism_scale");

    public GigantismEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(AttributeContainer attributes, int amplifier) {
        EntityAttributeInstance scale = attributes.getCustomInstance(EntityAttributes.GENERIC_SCALE);
        if (scale != null) {
            scale.addPersistentModifier(
                    new EntityAttributeModifier(
                            MODIFIER_ID,
                            0.5 * (amplifier + 1),
                            EntityAttributeModifier.Operation.ADD_VALUE
                    )
            );
        }
    }

    @Override
    public void onRemoved(AttributeContainer attributes) {
        EntityAttributeInstance scale = attributes.getCustomInstance(EntityAttributes.GENERIC_SCALE);
        if (scale != null) {
            if (scale.getModifier(MODIFIER_ID) != null) {
                scale.removeModifier(MODIFIER_ID);
            }
        }
    }
}





