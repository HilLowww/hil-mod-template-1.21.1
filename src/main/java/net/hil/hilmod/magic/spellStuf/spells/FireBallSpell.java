package net.hil.hilmod.magic.spellStuf.spells;

import net.hil.hilmod.magic.spellStuf.setup.Spell;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
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
        // Make sure we're on the server
        if (world.isClient) {
            return;
        }

        Vec3d look = caster.getRotationVec(1.0F);
        int explosionPower = 1; // Ghast fireball power

        FireballEntity fireball = new FireballEntity(world, caster, look,10);

        // Spawn it a bit in front of the player
        fireball.setPosition(
                caster.getX() + look.x * 2.0,
                caster.getEyeY() - 0.2,
                caster.getZ() + look.z * 2.0
        );

        world.spawnEntity(fireball);
    }

}
