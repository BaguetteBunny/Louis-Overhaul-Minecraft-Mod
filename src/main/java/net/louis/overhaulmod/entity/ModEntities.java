package net.louis.overhaulmod.entity;

import net.louis.overhaulmod.LouisOverhaulMod;
import net.louis.overhaulmod.entity.custom.living.BearEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModEntities {
    public static final EntityType<BearEntity> BROWN_BEAR = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(LouisOverhaulMod.MOD_ID, "brown_bear"),
            EntityType.Builder.create(BearEntity::new, SpawnGroup.AMBIENT)
                    .dimensions(1.4f, 1.4f).build());


    public static void registerModEntities() {
        LouisOverhaulMod.LOGGER.info("Registering Mod Entities for " + LouisOverhaulMod.MOD_ID);
    }
}