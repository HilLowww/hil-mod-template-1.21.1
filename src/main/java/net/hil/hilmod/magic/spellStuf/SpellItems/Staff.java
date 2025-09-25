package net.hil.hilmod.magic.spellStuf.SpellItems;

import net.hil.hilmod.magic.mana.PlayerManaManager;
import net.hil.hilmod.magic.spellStuf.setup.Spell;
import net.hil.hilmod.magic.spellStuf.setup.SpellRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Staff extends Item {
private final String spell;

    public Staff(Settings settings, String spellID) {
        super(settings);
        this.spell = spellID;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Spell Spell = (Spell) SpellRegistry.get(Identifier.of(spell));
        if (user instanceof ServerPlayerEntity serverPlayer) {
        if (PlayerManaManager.getMana(serverPlayer).getCurrentMana() < Spell.getManaCost()) {
            user.sendMessage(Text.literal("Not enough mana!"), false);
            return null;
        }


        PlayerManaManager.getMana(serverPlayer).useMana(Spell.getManaCost(), serverPlayer);
        Spell.cast(world, user);}
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }
}
