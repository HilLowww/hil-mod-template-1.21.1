package net.hil.hilmod.magic.Mana;

import net.minecraft.server.network.ServerPlayerEntity;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManaManager {
    private PlayerManaManager() { /* utility class */ }

    // Thread-safe map of player UUID -> Mana instance
    private static final ConcurrentHashMap<UUID, ModMana> PLAYER_MANA = new ConcurrentHashMap<>();

    /**
     * Create and register a default Mana for the player.
     * Call this on player join if you want a default starting Mana.
     */
    public static void initializePlayer(ServerPlayerEntity player) {
        initializePlayer(player, new ModMana(100)); // default 100 max; change as needed
    }

    /**
     * Register an existing Mana instance for the player (useful when loading from disk).
     */
    public static void initializePlayer(ServerPlayerEntity player, ModMana mana) {
        if (player == null || mana == null) return;
        PLAYER_MANA.put(player.getUuid(), mana);
    }

    /**
     * Get the Mana instance for a player, or null if not initialized.
     */
    public static ModMana getMana(ServerPlayerEntity player) {
        if (player == null) return null;
        return PLAYER_MANA.get(player.getUuid());
    }

    /**
     * Get the Mana instance by UUID (may be null).
     */
    public static ModMana getMana(UUID uuid) {
        return PLAYER_MANA.get(uuid);
    }

    /**
     * Returns true if the player currently has a Mana entry.
     */
    public static boolean hasMana(ServerPlayerEntity player) {
        return player != null && PLAYER_MANA.containsKey(player.getUuid());
    }

    /**
     * Remove the player's Mana from the runtime store (call on disconnect).
     */
    public static void removePlayer(ServerPlayerEntity player) {
        if (player == null) return;
        PLAYER_MANA.remove(player.getUuid());
    }

    /**
     * Clear all stored mana (for testing).
     */
    public static void clearAll() {
        PLAYER_MANA.clear();
    }
}
