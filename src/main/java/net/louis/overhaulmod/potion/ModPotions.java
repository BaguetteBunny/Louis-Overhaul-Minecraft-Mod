package net.louis.overhaulmod.potion;

import net.louis.overhaulmod.LouisOverhaulMod;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import static java.lang.Float.NaN;

public class ModPotions {
    public static final int LONG_300 = 9600;
    public static final int STRONG_300 = 1800;
    public static final int LONGEST_300 = 36000;
    public static final int STRONGEST_300 = 1500;

    public static final int LONG_045 = 1800;
    public static final int STRONG_045 = 440;
    public static final int LONGEST_045 = 6000;
    public static final int STRONGEST_045 = 220;

    public static final int LONG_130 = 4800;
    public static final int STRONG_130 = 400;
    public static final int LONGEST_130 = 12000;
    public static final int STRONGEST_130 = 200;

    public static final int LONGEST_TURTLE = 1800;
    public static final int STRONGEST_TURTLE = 200;

    // UNIQUELY LONG
    public static final RegistryEntry<Potion> LONGEST_NIGHT_VISION = registerPotion("night_vision",
            new Potion(new StatusEffectInstance(StatusEffects.NIGHT_VISION, LONGEST_300, 0)));

    public static final RegistryEntry<Potion> LONGEST_WATER_BREATHING = registerPotion("water_breathing",
            new Potion(new StatusEffectInstance(StatusEffects.WATER_BREATHING, LONGEST_300, 0)));

    public static final RegistryEntry<Potion> LONGEST_INVISIBILITY = registerPotion("invisibility",
            new Potion(new StatusEffectInstance(StatusEffects.INVISIBILITY, LONGEST_300, 0)));

    public static final RegistryEntry<Potion> LONGEST_FIRE_RESISTANCE = registerPotion("fire_resistance",
            new Potion(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, LONGEST_300, 0)));

    public static final RegistryEntry<Potion> LONGEST_SLOW_FALLING = registerPotion("slow_falling",
            new Potion(new StatusEffectInstance(StatusEffects.SLOW_FALLING, LONGEST_300, 0)));


    // UNIQUELY STRONG
    public static final RegistryEntry<Potion> STRONGEST_HEALING = registerPotion("healing",
            new Potion(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, LONGEST_300, 2)));

    public static final RegistryEntry<Potion> STRONGEST_HARMING = registerPotion("harming",
            new Potion(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, LONGEST_300, 2)));


    // HYBRIDS
    public static final RegistryEntry<Potion> LONGEST_LEAPING = registerPotion("leaping",
            new Potion(new StatusEffectInstance(StatusEffects.JUMP_BOOST, LONGEST_300, 0)));
    public static final RegistryEntry<Potion> STRONGEST_LEAPING = registerPotion("leaping",
            new Potion(new StatusEffectInstance(StatusEffects.JUMP_BOOST, STRONGEST_300, 2)));

    public static final RegistryEntry<Potion> LONGEST_SWIFTNESS = registerPotion("swiftness",
            new Potion(new StatusEffectInstance(StatusEffects.SPEED, LONGEST_300, 0)));
    public static final RegistryEntry<Potion> STRONGEST_SWIFTNESS = registerPotion("swiftness",
            new Potion(new StatusEffectInstance(StatusEffects.SPEED, STRONGEST_300, 2)));

    public static final RegistryEntry<Potion> LONGEST_SLOWNESS = registerPotion("slowness",
            new Potion(new StatusEffectInstance(StatusEffects.SLOWNESS, LONGEST_130, 0)));
    public static final RegistryEntry<Potion> STRONGEST_SLOWNESS = registerPotion("slowness",
            new Potion(new StatusEffectInstance(StatusEffects.SLOWNESS, STRONGEST_130, 5)));

    public static final RegistryEntry<Potion> LONGEST_REGENERATION = registerPotion("regeneration",
            new Potion(new StatusEffectInstance(StatusEffects.REGENERATION, LONGEST_045, 0)));
    public static final RegistryEntry<Potion> STRONGEST_REGENERATION = registerPotion("regeneration",
            new Potion(new StatusEffectInstance(StatusEffects.REGENERATION, STRONGEST_045, 2)));

    public static final RegistryEntry<Potion> LONGEST_POISON = registerPotion("poison",
            new Potion(new StatusEffectInstance(StatusEffects.POISON, LONGEST_045, 0)));
    public static final RegistryEntry<Potion> STRONGEST_POISON = registerPotion("poison",
            new Potion(new StatusEffectInstance(StatusEffects.POISON, STRONGEST_045, 2)));

    public static final RegistryEntry<Potion> LONGEST_STRENGTH = registerPotion("strength",
            new Potion(new StatusEffectInstance(StatusEffects.STRENGTH, LONGEST_300, 0)));
    public static final RegistryEntry<Potion> STRONGEST_STRENGTH = registerPotion("strength",
            new Potion(new StatusEffectInstance(StatusEffects.STRENGTH, STRONGEST_300, 2)));

    // HYBRID & ADDONS
    public static final RegistryEntry<Potion> STRONG_WEAKNESS = registerPotion("weakness",
            new Potion(new StatusEffectInstance(StatusEffects.WEAKNESS, STRONG_130, 1)));
    public static final RegistryEntry<Potion> LONGEST_WEAKNESS = registerPotion("weakness",
            new Potion(new StatusEffectInstance(StatusEffects.WEAKNESS, LONGEST_130, 0)));
    public static final RegistryEntry<Potion> STRONGEST_WEAKNESS = registerPotion("weakness",
            new Potion(new StatusEffectInstance(StatusEffects.WEAKNESS, STRONGEST_130, 2)));

    public static final RegistryEntry<Potion> LONG_LUCK = registerPotion("luck",
            new Potion(new StatusEffectInstance(StatusEffects.LUCK, LONG_300, 0)));
    public static final RegistryEntry<Potion> STRONG_LUCK = registerPotion("luck",
            new Potion(new StatusEffectInstance(StatusEffects.LUCK, STRONG_130, 1)));
    public static final RegistryEntry<Potion> LONGEST_LUCK = registerPotion("luck",
            new Potion(new StatusEffectInstance(StatusEffects.LUCK, LONGEST_130, 0)));
    public static final RegistryEntry<Potion> STRONGEST_LUCK = registerPotion("luck",
            new Potion(new StatusEffectInstance(StatusEffects.LUCK, STRONGEST_130, 2)));

    public static final RegistryEntry<Potion> LONG_WIND_CHARGED = registerPotion("wind_charged",
            new Potion(new StatusEffectInstance(StatusEffects.WIND_CHARGED, LONG_300, 0)));
    public static final RegistryEntry<Potion> STRONG_WIND_CHARGED = registerPotion("wind_charged",
            new Potion(new StatusEffectInstance(StatusEffects.WIND_CHARGED, STRONG_300, 1)));
    public static final RegistryEntry<Potion> LONGEST_WIND_CHARGED = registerPotion("wind_charged",
            new Potion(new StatusEffectInstance(StatusEffects.WIND_CHARGED, LONGEST_300, 0)));
    public static final RegistryEntry<Potion> STRONGEST_WIND_CHARGED = registerPotion("wind_charged",
            new Potion(new StatusEffectInstance(StatusEffects.WIND_CHARGED, STRONGEST_300, 2)));

    public static final RegistryEntry<Potion> LONG_WEAVING = registerPotion("weaving",
            new Potion(new StatusEffectInstance(StatusEffects.WEAVING, LONG_300, 0)));
    public static final RegistryEntry<Potion> STRONG_WEAVING = registerPotion("weaving",
            new Potion(new StatusEffectInstance(StatusEffects.WEAVING, STRONG_300, 1)));
    public static final RegistryEntry<Potion> LONGEST_WEAVING = registerPotion("weaving",
            new Potion(new StatusEffectInstance(StatusEffects.WEAVING, LONGEST_300, 0)));
    public static final RegistryEntry<Potion> STRONGEST_WEAVING = registerPotion("weaving",
            new Potion(new StatusEffectInstance(StatusEffects.WEAVING, STRONGEST_300, 2)));

    public static final RegistryEntry<Potion> LONG_OOZING = registerPotion("oozing",
            new Potion(new StatusEffectInstance(StatusEffects.OOZING, LONG_300, 0)));
    public static final RegistryEntry<Potion> STRONG_OOZING = registerPotion("oozing",
            new Potion(new StatusEffectInstance(StatusEffects.OOZING, STRONG_300, 1)));
    public static final RegistryEntry<Potion> LONGEST_OOZING = registerPotion("oozing",
            new Potion(new StatusEffectInstance(StatusEffects.OOZING, LONGEST_300, 0)));
    public static final RegistryEntry<Potion> STRONGEST_OOZING = registerPotion("oozing",
            new Potion(new StatusEffectInstance(StatusEffects.OOZING, STRONGEST_300, 2)));

    public static final RegistryEntry<Potion> LONG_INFESTED = registerPotion("infested",
            new Potion(new StatusEffectInstance(StatusEffects.INFESTED, LONG_300, 0)));
    public static final RegistryEntry<Potion> STRONG_INFESTED = registerPotion("infested",
            new Potion(new StatusEffectInstance(StatusEffects.INFESTED, STRONG_300, 1)));
    public static final RegistryEntry<Potion> LONGEST_INFESTED = registerPotion("infested",
            new Potion(new StatusEffectInstance(StatusEffects.INFESTED, LONGEST_300, 0)));
    public static final RegistryEntry<Potion> STRONGEST_INFESTED = registerPotion("infested",
            new Potion(new StatusEffectInstance(StatusEffects.INFESTED, STRONGEST_300, 2)));






    public static final RegistryEntry<Potion> LONGEST_TURTLE_MASTER = registerPotion("turtle_master",
            new Potion(
                    new StatusEffectInstance(StatusEffects.SLOWNESS, LONGEST_TURTLE, 3),
                    new StatusEffectInstance(StatusEffects.RESISTANCE, LONGEST_TURTLE, 2)
            )
    );
    public static final RegistryEntry<Potion> STRONGEST_TURTLE_MASTER = registerPotion("turtle_master",
            new Potion(
                    new StatusEffectInstance(StatusEffects.SLOWNESS, STRONGEST_TURTLE, 6),
                    new StatusEffectInstance(StatusEffects.RESISTANCE, STRONGEST_TURTLE, 4)
            )
    );



    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(LouisOverhaulMod.MOD_ID, name), potion);
    }

    public static void registerPotions() {
        LouisOverhaulMod.LOGGER.info("Registering Mod Potions for " + LouisOverhaulMod.MOD_ID);
    }
}
