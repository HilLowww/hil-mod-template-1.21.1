package net.hil.hilmod.magic.spellStuf.spells;

import net.hil.hilmod.magic.spellStuf.setup.Spell;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class HealSelfSpell implements Spell {
    @Override
    public String getName() {
        return "heal_self";
    }

    @Override
    public int getManaCost() {
        return 40;
    }

    @Override
    public void cast(World world, PlayerEntity caster) {
        caster.heal(4);
    }
}
