package net.louis.overhaulmod.utils;

import net.minecraft.entity.LivingEntity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GlowManager {
    private static final Map<LivingEntity, Integer> glowingEntities = new HashMap<>();

    public static void addGlowingEntity(LivingEntity entity, int durationTicks) {
        glowingEntities.put(entity, durationTicks);
    }

    public static void tick() {
        Iterator<Map.Entry<LivingEntity, Integer>> iter = glowingEntities.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<LivingEntity, Integer> entry = iter.next();
            int ticksLeft = entry.getValue() - 1;
            if (ticksLeft <= 0) {
                entry.getKey().setGlowing(false);
                iter.remove();
            } else {
                entry.setValue(ticksLeft);
            }
        }
    }
}

