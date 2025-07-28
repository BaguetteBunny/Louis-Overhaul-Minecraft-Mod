package net.louis.overhaulmod.utils;

import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DelayedActionScheduler {
    private static final Map<ServerPlayerEntity, Integer> scheduledPlayers = new HashMap<>();

    public static void schedule(ServerPlayerEntity player, int delayTicks) {
        scheduledPlayers.put(player, delayTicks);
    }

    public static void tick() {
        Iterator<Map.Entry<ServerPlayerEntity, Integer>> it = scheduledPlayers.entrySet().iterator();
        while (it.hasNext()) {
            var entry = it.next();
            int ticksLeft = entry.getValue() - 1;
            if (ticksLeft <= 0) {
                ServerPlayerEntity player = entry.getKey();

                it.remove();
            } else {
                entry.setValue(ticksLeft);
            }
        }
    }
}
