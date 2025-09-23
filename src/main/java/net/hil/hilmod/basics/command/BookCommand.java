package net.hil.hilmod.basics.command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.hil.hilmod.component.ModDataComponentTypes;
import net.hil.hilmod.magic.spellStuf.SpellItems.grimoire;
import net.hil.hilmod.magic.spellStuf.setup.SpellRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.withCommandSource;

public class BookCommand {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {

            // /addspell fireball
            dispatcher.register(
                    literal("addspell")
                            .then(literal("fireball")
                                    .executes(context -> addSpell(context.getSource(), "hilmod:fireball"))
                            )
                            .then(literal("heal")
                                    .executes(context -> addSpell(context.getSource(), "hilmod:heal_self")))
                    // Add more spell literals here
            );
        });
    }

    // Method to safely add a spell to the player's held Grimware
    private static int addSpell(ServerCommandSource source, String spellId) {
        if (!(source.getPlayer() instanceof ServerPlayerEntity player)) {
            source.sendFeedback(() -> Text.literal("Some message"), false);            return 0;
        }

        ItemStack stack = player.getMainHandStack();
        if (stack.isEmpty() || !(stack.getItem() instanceof grimoire)) {
            source.sendFeedback(() -> Text.literal("Some message"), false);            return 0;
        }

        grimoire book = (grimoire) stack.getItem();

        @SuppressWarnings("unchecked")
        List<String> spells = stack.get(ModDataComponentTypes.SPELLS);
        if (spells == null) spells = new ArrayList<>();

        if (!spells.contains(spellId)) {
            spells.add(spellId);
            stack.set(ModDataComponentTypes.SPELLS, spells);
            source.sendFeedback(() -> Text.literal("Some message"), false);        } else {
            source.sendFeedback(() -> Text.literal("Some message"), false);        }

        return 1; // success
    }
}

