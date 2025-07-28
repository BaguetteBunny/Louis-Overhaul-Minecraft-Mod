package net.louis.overhaulmod.potion;

import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static final int NORMAL_300 = 3600;
    public static final int LONG_300 = 9600;
    public static final int STRONG_300 = 1800;
    public static final int LONGEST_300 = 36000;
    public static final int STRONGEST_300 = 1500;

    public static final int NORMAL_045 = 900;
    public static final int LONG_045 = 1800;
    public static final int STRONG_045 = 440;
    public static final int LONGEST_045 = 6000;
    public static final int STRONGEST_045 = 220;

    public static final int NORMAL_130 = 1800;
    public static final int LONG_130 = 4800;
    public static final int STRONG_130 = 400;
    public static final int LONGEST_130 = 12000;
    public static final int STRONGEST_130 = 200;

    public static final int NORMAL_005 = 100;
    public static final int LONG_005 = 1500;
    public static final int LONGEST_005 = 200;

    public static final int LONGEST_TURTLE = 1800;
    public static final int STRONGEST_TURTLE = 200;

    // UNIQUELY LONG
    public static final RegistryEntry<Potion> LONGEST_NIGHT_VISION = registerPotion("longest_night_vision",
            new Potion("night_vision", new StatusEffectInstance(StatusEffects.NIGHT_VISION, LONGEST_300, 0)));

    public static final RegistryEntry<Potion> LONGEST_WATER_BREATHING = registerPotion("longest_water_breathing",
            new Potion("water_breathing", new StatusEffectInstance(StatusEffects.WATER_BREATHING, LONGEST_300, 0)));

    public static final RegistryEntry<Potion> LONGEST_INVISIBILITY = registerPotion("longest_invisibility",
            new Potion("invisibility", new StatusEffectInstance(StatusEffects.INVISIBILITY, LONGEST_300, 0)));

    public static final RegistryEntry<Potion> LONGEST_FIRE_RESISTANCE = registerPotion("longest_fire_resistance",
            new Potion("fire_resistance", new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, LONGEST_300, 0)));

    public static final RegistryEntry<Potion> LONGEST_SLOW_FALLING = registerPotion("longest_slow_falling",
            new Potion("slow_falling", new StatusEffectInstance(StatusEffects.SLOW_FALLING, LONGEST_300, 0)));


    // UNIQUELY STRONG
    public static final RegistryEntry<Potion> STRONGEST_HEALING = registerPotion("strongest_healing",
            new Potion("healing", new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, LONGEST_300, 2)));

    public static final RegistryEntry<Potion> STRONGEST_HARMING = registerPotion("strongest_harming",
            new Potion("harming", new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, LONGEST_300, 2)));


    // HYBRIDS
    public static final RegistryEntry<Potion> LONGEST_LEAPING = registerPotion("longest_leaping",
            new Potion("leaping", new StatusEffectInstance(StatusEffects.JUMP_BOOST, LONGEST_300, 0)));
    public static final RegistryEntry<Potion> STRONGEST_LEAPING = registerPotion("strongest_leaping",
            new Potion("leaping", new StatusEffectInstance(StatusEffects.JUMP_BOOST, STRONGEST_300, 2)));

    public static final RegistryEntry<Potion> LONGEST_SWIFTNESS = registerPotion("longest_swiftness",
            new Potion("swiftness", new StatusEffectInstance(StatusEffects.SPEED, LONGEST_300, 0)));
    public static final RegistryEntry<Potion> STRONGEST_SWIFTNESS = registerPotion("strongest_swiftness",
            new Potion("swiftness", new StatusEffectInstance(StatusEffects.SPEED, STRONGEST_300, 2)));

    public static final RegistryEntry<Potion> LONGEST_SLOWNESS = registerPotion("longest_slowness",
            new Potion("slowness", new StatusEffectInstance(StatusEffects.SLOWNESS, LONGEST_130, 0)));
    public static final RegistryEntry<Potion> STRONGEST_SLOWNESS = registerPotion("strongest_slowness",
            new Potion("slowness", new StatusEffectInstance(StatusEffects.SLOWNESS, STRONGEST_130, 5)));

    public static final RegistryEntry<Potion> LONGEST_REGENERATION = registerPotion("longest_regeneration",
            new Potion("regeneration", new StatusEffectInstance(StatusEffects.REGENERATION, LONGEST_045, 0)));
    public static final RegistryEntry<Potion> STRONGEST_REGENERATION = registerPotion("strongest_regeneration",
            new Potion("regeneration", new StatusEffectInstance(StatusEffects.REGENERATION, STRONGEST_045, 2)));

    public static final RegistryEntry<Potion> LONGEST_POISON = registerPotion("longest_poison",
            new Potion("poison", new StatusEffectInstance(StatusEffects.POISON, LONGEST_045, 0)));
    public static final RegistryEntry<Potion> STRONGEST_POISON = registerPotion("strongest_poison",
            new Potion("poison", new StatusEffectInstance(StatusEffects.POISON, STRONGEST_045, 2)));

    public static final RegistryEntry<Potion> LONGEST_STRENGTH = registerPotion("longest_strength",
            new Potion("strength", new StatusEffectInstance(StatusEffects.STRENGTH, LONGEST_300, 0)));
    public static final RegistryEntry<Potion> STRONGEST_STRENGTH = registerPotion("strongest_strength",
            new Potion("strength", new StatusEffectInstance(StatusEffects.STRENGTH, STRONGEST_300, 2)));

    public static final RegistryEntry<Potion> LONGEST_TURTLE_MASTER = registerPotion("longest_w3turtle_master",
            new Potion(
                    "turtle_master",
                    new StatusEffectInstance(StatusEffects.SLOWNESS, LONGEST_TURTLE, 3),
                    new StatusEffectInstance(StatusEffects.RESISTANCE, LONGEST_TURTLE, 2),
                    new StatusEffectInstance(ModEffects.GROUNDED, STRONGEST_TURTLE, 0)
            )
    );
    public static final RegistryEntry<Potion> STRONGEST_TURTLE_MASTER = registerPotion("strongest_turtle_master",
            new Potion(
                    "turtle_master",
                    new StatusEffectInstance(StatusEffects.SLOWNESS, STRONGEST_TURTLE, 6),
                    new StatusEffectInstance(StatusEffects.RESISTANCE, STRONGEST_TURTLE, 4),
                    new StatusEffectInstance(ModEffects.GROUNDED, STRONGEST_TURTLE, 0)
            )
    );

    // HYBRID & ADDONS
    public static final RegistryEntry<Potion> STRONG_WEAKNESS = registerPotion("strong_weakness",
            new Potion("weakness", new StatusEffectInstance(StatusEffects.WEAKNESS, STRONG_130, 1)));
    public static final RegistryEntry<Potion> LONGEST_WEAKNESS = registerPotion("longest_weakness",
            new Potion("weakness", new StatusEffectInstance(StatusEffects.WEAKNESS, LONGEST_130, 0)));
    public static final RegistryEntry<Potion> STRONGEST_WEAKNESS = registerPotion("strongest_weakness",
            new Potion("weakness", new StatusEffectInstance(StatusEffects.WEAKNESS, STRONGEST_130, 2)));

    public static final RegistryEntry<Potion> LONG_LUCK = registerPotion("long_luck",
            new Potion("luck", new StatusEffectInstance(StatusEffects.LUCK, LONG_130, 0)));
    public static final RegistryEntry<Potion> STRONG_LUCK = registerPotion("strong_luck",
            new Potion("luck", new StatusEffectInstance(StatusEffects.LUCK, STRONG_130, 1)));
    public static final RegistryEntry<Potion> LONGEST_LUCK = registerPotion("longest_luck",
            new Potion("luck", new StatusEffectInstance(StatusEffects.LUCK, LONGEST_130, 0)));
    public static final RegistryEntry<Potion> STRONGEST_LUCK = registerPotion("strongest_luck",
            new Potion("luck", new StatusEffectInstance(StatusEffects.LUCK, STRONGEST_130, 2)));

    public static final RegistryEntry<Potion> LONG_WIND_CHARGED = registerPotion("long_wind_charged",
            new Potion("wind_charged", new StatusEffectInstance(StatusEffects.WIND_CHARGED, LONG_300, 0)));
    public static final RegistryEntry<Potion> STRONG_WIND_CHARGED = registerPotion("long_strong_wind_charged",
            new Potion("wind_charged", new StatusEffectInstance(StatusEffects.WIND_CHARGED, STRONG_300, 1)));
    public static final RegistryEntry<Potion> LONGEST_WIND_CHARGED = registerPotion("longest_wind_charged",
            new Potion("wind_charged", new StatusEffectInstance(StatusEffects.WIND_CHARGED, LONGEST_300, 0)));
    public static final RegistryEntry<Potion> STRONGEST_WIND_CHARGED = registerPotion("strongest_wind_charged",
            new Potion("wind_charged", new StatusEffectInstance(StatusEffects.WIND_CHARGED, STRONGEST_300, 2)));

    public static final RegistryEntry<Potion> LONG_WEAVING = registerPotion("long_weaving",
            new Potion("weaving", new StatusEffectInstance(StatusEffects.WEAVING, LONG_300, 0)));
    public static final RegistryEntry<Potion> STRONG_WEAVING = registerPotion("strong_weaving",
            new Potion("weaving", new StatusEffectInstance(StatusEffects.WEAVING, STRONG_300, 1)));
    public static final RegistryEntry<Potion> LONGEST_WEAVING = registerPotion("longest_weaving",
            new Potion("weaving", new StatusEffectInstance(StatusEffects.WEAVING, LONGEST_300, 0)));
    public static final RegistryEntry<Potion> STRONGEST_WEAVING = registerPotion("strongest_weaving",
            new Potion("weaving", new StatusEffectInstance(StatusEffects.WEAVING, STRONGEST_300, 2)));

    public static final RegistryEntry<Potion> LONG_OOZING = registerPotion("long_oozing",
            new Potion("oozing", new StatusEffectInstance(StatusEffects.OOZING, LONG_300, 0)));
    public static final RegistryEntry<Potion> STRONG_OOZING = registerPotion("strong_oozing",
            new Potion("oozing", new StatusEffectInstance(StatusEffects.OOZING, STRONG_300, 1)));
    public static final RegistryEntry<Potion> LONGEST_OOZING = registerPotion("longest_oozing",
            new Potion("oozing", new StatusEffectInstance(StatusEffects.OOZING, LONGEST_300, 0)));
    public static final RegistryEntry<Potion> STRONGEST_OOZING = registerPotion("strongest_oozing",
            new Potion("oozing", new StatusEffectInstance(StatusEffects.OOZING, STRONGEST_300, 2)));

    public static final RegistryEntry<Potion> LONG_INFESTED = registerPotion("long_infested",
            new Potion("infested", new StatusEffectInstance(StatusEffects.INFESTED, LONG_300, 0)));
    public static final RegistryEntry<Potion> STRONG_INFESTED = registerPotion("strong_infested",
            new Potion("infested", new StatusEffectInstance(StatusEffects.INFESTED, STRONG_300, 1)));
    public static final RegistryEntry<Potion> LONGEST_INFESTED = registerPotion("longest_infested",
            new Potion("infested", new StatusEffectInstance(StatusEffects.INFESTED, LONGEST_300, 0)));
    public static final RegistryEntry<Potion> STRONGEST_INFESTED = registerPotion("strongest_infested",
            new Potion("infested", new StatusEffectInstance(StatusEffects.INFESTED, STRONGEST_300, 2)));

    // NEW POTIONS
    public static final RegistryEntry<Potion> GROUNDED = registerPotion("grounded",
            new Potion("grounded", new StatusEffectInstance(ModEffects.GROUNDED, NORMAL_130, 0)));
    public static final RegistryEntry<Potion> LONG_GROUNDED = registerPotion("long_grounded",
            new Potion("grounded", new StatusEffectInstance(ModEffects.GROUNDED, LONG_130, 0)));
    public static final RegistryEntry<Potion> LONGEST_GROUNDED = registerPotion("longest_grounded",
            new Potion("grounded", new StatusEffectInstance(ModEffects.GROUNDED, LONGEST_130, 0)));

    public static final RegistryEntry<Potion> UNLUCK = registerPotion("unluck",
            new Potion("unluck", new StatusEffectInstance(StatusEffects.UNLUCK, NORMAL_130, 0)));
    public static final RegistryEntry<Potion> LONG_UNLUCK = registerPotion("long_unluck",
            new Potion("unluck", new StatusEffectInstance(StatusEffects.UNLUCK, LONG_130, 0)));
    public static final RegistryEntry<Potion> STRONG_UNLUCK = registerPotion("strong_unluck",
            new Potion("unluck", new StatusEffectInstance(StatusEffects.UNLUCK, STRONG_130, 1)));
    public static final RegistryEntry<Potion> LONGEST_UNLUCK = registerPotion("longest_unluck",
            new Potion("unluck", new StatusEffectInstance(StatusEffects.UNLUCK, LONGEST_130, 0)));
    public static final RegistryEntry<Potion> STRONGEST_UNLUCK = registerPotion("strongest_unluck",
            new Potion("unluck", new StatusEffectInstance(StatusEffects.UNLUCK, STRONGEST_130, 2)));

    public static final RegistryEntry<Potion> BLINDNESS = registerPotion("blindness",
            new Potion("blindness", new StatusEffectInstance(StatusEffects.BLINDNESS, NORMAL_005, 0)));
    public static final RegistryEntry<Potion> LONG_BLINDNESS = registerPotion("long_blindness",
            new Potion("blindness", new StatusEffectInstance(StatusEffects.BLINDNESS, LONG_005, 0)));
    public static final RegistryEntry<Potion> LONGEST_BLINDNESS = registerPotion("longest_blindness",
            new Potion("blindness", new StatusEffectInstance(StatusEffects.BLINDNESS, LONGEST_005, 0)));

    public static final RegistryEntry<Potion> DARKNESS = registerPotion("darkness",
            new Potion("darkness", new StatusEffectInstance(StatusEffects.DARKNESS, NORMAL_005, 0)));
    public static final RegistryEntry<Potion> LONG_DARKNESS = registerPotion("long_darkness",
            new Potion("darkness", new StatusEffectInstance(StatusEffects.DARKNESS, LONG_005, 0)));
    public static final RegistryEntry<Potion> LONGEST_DARKNESS = registerPotion("longest_darkness",
            new Potion("darkness", new StatusEffectInstance(StatusEffects.DARKNESS, LONGEST_005, 0)));

    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(LouisOverhaulMod.MOD_ID, name), potion);
    }

    public static void registerPotions() {
        LouisOverhaulMod.LOGGER.info("Registering Mod Potions for " + LouisOverhaulMod.MOD_ID);
    }
}
