package net.louis.overhaulmod.sound;

import net.louis.overhaulmod.LouisOverhaulMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent SADDLED_GOAT_HORN_USE = registerSoundEvent("saddled_goat_horn_use");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(LouisOverhaulMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        LouisOverhaulMod.LOGGER.info("Registering Mod Sounds for " + LouisOverhaulMod.MOD_ID);
    }
}