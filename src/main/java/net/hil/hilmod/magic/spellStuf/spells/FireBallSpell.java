package net.hil.hilmod.magic.spellStuf.spells;

import net.hil.hilmod.magic.spellStuf.setup.Spell;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class FireBallSpell implements Spell {
    @Override
    public String getName() {
        return "fireball";
    }

    @Override
    public int getManaCost() {
        return 10;
    }

    @Override
    public void cast(World world, PlayerEntity caster) {
        if (!(caster instanceof ServerPlayerEntity serverPlayer)) {
            // Either log a warning or do nothing on the client
            return;
        }
        //Fireball stuff
    }
}
