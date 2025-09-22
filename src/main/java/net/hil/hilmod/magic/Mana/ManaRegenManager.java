package net.hil.hilmod.magic.Mana;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

public class ManaRegenManager {
    private static final Map<UUID, Integer> regenDelays = new HashMap<>();

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
                    mana.addMana(1); // regen 1 per tick (customize this)
                }
            }
        }
    }

    // Call this whenever a player spends mana
    public static void resetRegenDelay(ServerPlayerEntity player, int ticks) {
        regenDelays.put(player.getUuid(), ticks);
    }
}

