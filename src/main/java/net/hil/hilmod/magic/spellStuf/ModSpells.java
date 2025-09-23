package net.hil.hilmod.magic.spellStuf;

import net.hil.hilmod.HilMod;
import net.hil.hilmod.magic.spellStuf.setup.Spell;
import net.hil.hilmod.magic.spellStuf.setup.SpellRegistry;
import net.hil.hilmod.magic.spellStuf.spells.FireBallSpell;
import net.hil.hilmod.magic.spellStuf.spells.HealSelfSpell;
import net.minecraft.util.Identifier;

public class ModSpells {
    public static final Spell FIREBALL = registerSpell("fireball",
            new FireBallSpell());
    public static final Spell HEAL_SELF = registerSpell("heal_self",
            new HealSelfSpell());

    private static Spell registerSpell(String name, Spell spell) {
        Identifier id = Identifier.of(HilMod.MOD_ID, name);
        SpellRegistry.register(id, spell);
        return spell;
    }

    public static void registerModSpells() {
        HilMod.LOGGER.info("Registering spells for " + HilMod.MOD_ID);
    }
}
