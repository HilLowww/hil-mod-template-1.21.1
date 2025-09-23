package net.hil.hilmod.magic.spellStuf.setup;

import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class SpellRegistry {
    private static final Map<Identifier, Spell> SPELLS = new HashMap<>();


    public static void register(Identifier id, Spell spell) {
        SPELLS.put(id, spell);
    }

    public static Spell get(Identifier id) {
        return SPELLS.get(id);
    }

    public static Map<Identifier, Spell> getAll() {
        return SPELLS;
    }
}
