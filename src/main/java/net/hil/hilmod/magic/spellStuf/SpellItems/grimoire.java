package net.hil.hilmod.magic.spellStuf.SpellItems;

import net.hil.hilmod.component.ModDataComponentTypes;
import net.hil.hilmod.magic.mana.ModMana;
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

import java.util.ArrayList;
import java.util.List;

public class grimoire extends Item {
    private final int maxSpells;

    public grimoire(Settings settings, int maxSpells) {
        super(settings);
        this.maxSpells = maxSpells; // max number of spells this book can hold
    }

    /** Returns a copy of the spell list stored in this book */
    public List<String> getSpells(ItemStack stack) {
        List<String> spells = stack.get(ModDataComponentTypes.SPELLS);
        return spells != null ? new ArrayList<>(spells) : new ArrayList<>();
    }

    /** Adds a spell to the book if not full */
    public boolean addSpell(ItemStack stack, String spellId) {
        List<String> spells = getSpells(stack);
        if (spells.size() >= maxSpells) return false; // already full

        spells.add(spellId);
        stack.set(ModDataComponentTypes.SPELLS, spells);
        return true;
    }

    /** Removes a spell by index */
    public boolean removeSpell(ItemStack stack, int index) {
        List<String> spells = getSpells(stack);
        if (index < 0 || index >= spells.size()) return false;

        spells.remove(index);
        stack.set(ModDataComponentTypes.SPELLS, spells);
        return true;
    }

    /** Clears all spells from the book */
    public void clearSpells(ItemStack stack) {
        stack.set(ModDataComponentTypes.SPELLS, new ArrayList<>());
    }

    /** Checks if the book has space for more spells */
    public boolean hasSpace(ItemStack stack) {
        return getSpells(stack).size() < maxSpells;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        // Get the spell list from the book
        @SuppressWarnings("unchecked")
        List<String> spells = stack.get(ModDataComponentTypes.SPELLS);
        if (spells == null || spells.isEmpty()) {
            if (!world.isClient) {
                user.sendMessage(Text.literal("Your Grimware has no spells!"), false);
            }
            return TypedActionResult.pass(stack);
        }

        // For example, cast the first spell
        String spellId = spells.get(0);
        castSpell(user, spellId, world);

        return TypedActionResult.success(stack, world.isClient());
    }

    private void castSpell(PlayerEntity player, String spellId, World world) {
        Spell spell = SpellRegistry.get(Identifier.of(spellId));
        if (spell == null) {
            player.sendMessage(Text.literal("Unknown spell: " + spellId), false);
            return;
        }


        // Subtract mana and cast
        // Client-side code (particles, sounds)
        if (world.isClient) {
            // e.g., show particle effects or play sounds
            return; // stop here; server code does not run on client
        }
        // Check player mana
        // Server-side code (effects, damage, entity spawning)
        if (player instanceof ServerPlayerEntity serverPlayer) {
            if (PlayerManaManager.getMana(serverPlayer).getCurrentMana() < spell.getManaCost()) {
                player.sendMessage(Text.literal("Not enough mana!"), false);
                return;
            }


            PlayerManaManager.getMana(serverPlayer).useMana(spell.getManaCost(), serverPlayer);
            spell.cast(world, player);        }
    }
}
