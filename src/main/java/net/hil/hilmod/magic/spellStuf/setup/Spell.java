package net.hil.hilmod.magic.spellStuf.setup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface Spell {
    String getName();
    int getManaCost();
    void cast(World world, PlayerEntity caster);
}
