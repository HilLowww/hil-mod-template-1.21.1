package net.hil.hilmod.magic.mana;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

public class ManaRegenManager {
    private static final Map<UUID, Map<String, Float>> regenSources = new HashMap<>();
    private static final Map<UUID, Integer> regenDelays = new HashMap<>();

    public static void setRegenSource(ServerPlayerEntity player, String id, float multiplier) {
        UUID uuid = player.getUuid();
        regenSources.computeIfAbsent(uuid, k -> new HashMap<>()).put(id, multiplier);
    }

    public static void removeRegenSource(ServerPlayerEntity player, String id) {
        Map<String, Float> sources = regenSources.get(player.getUuid());
        if (sources != null) {
            sources.remove(id);
            if (sources.isEmpty()) regenSources.remove(player.getUuid());
        }
    }

    public static float getTotalRegenMultiplier(ServerPlayerEntity player) {
        Map<String, Float> sources = regenSources.get(player.getUuid());
        if (sources == null || sources.isEmpty()) return 1.0f;

        float total = 1.0f;
        for (float mult : sources.values()) {
            total *= mult; // Multiply all sources together
        }
        return total;
    }

    // Called every server tick (20 times per second)
    public static void tick(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            ModMana mana = PlayerManaManager.getMana(player);
            if (mana == null) continue;

            UUID id = player.getUuid();
            int delay = regenDelays.getOrDefault(id, 0);

            if (mana.getCurrentMana() < mana.getMaxMana()) {
                if (delay > 0) {
                    regenDelays.put(id, delay - 1);
                } else {
                    float totalMult = getTotalRegenMultiplier(player);
                    int regenAmount = Math.round(1 * totalMult);
                    mana.addMana(regenAmount);
                }
            }
        }
    }

    // Call this whenever a player spends mana
    public static void resetRegenDelay(ServerPlayerEntity player) {
        ModMana mana = PlayerManaManager.getMana(player);

        if (mana.getMaxMana() >= mana.getCurrentMana() && mana.getCurrentMana() >= mana.getMaxMana() * 80 / 100) {
            regenDelays.put(player.getUuid(), 20);
        } else if (mana.getMaxMana() * 80 / 100 >= mana.getCurrentMana() && mana.getCurrentMana() >= mana.getMaxMana() * 30 / 100) {
            regenDelays.put(player.getUuid(), 30);
        }  else if (mana.getMaxMana() * 30 / 100 >= mana.getCurrentMana() && mana.getCurrentMana() >= mana.getMaxMana() * 10 / 100) {
            regenDelays.put(player.getUuid(), 40);
        } else if (mana.getMaxMana() * 10 / 100 >= mana.getCurrentMana() && mana.getCurrentMana() >= 0) {
            regenDelays.put(player.getUuid(), 80);
        }
    }
}

