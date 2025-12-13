package net.hil.hilmod.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ManaUi {

    // Stores timer for how long to show the bar after hitting full mana
    private static final Map<UUID, Integer> uiTimers = new HashMap<>();

    public static void init() {
        // Register HUD event once
        HudRenderCallback.EVENT.register((context, tickDelta) -> onRender(context));
    }

    private static void onRender(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        UUID uuid = client.player.getUuid();
        var mana = net.hil.hilmod.magic.mana.PlayerManaManager.getMana(uuid);
        if (mana == null) return;

        // Update timer
        updateUiTimer(uuid, mana.getCurrentMana(), mana.getMaxMana());

        int timer = uiTimers.getOrDefault(uuid, 0);
        if (timer <= 0) return; // Don't draw if timer ran out

        // Calculate fade (last 20 ticks = 1s fade out)
        float alpha = timer < 20 ? timer / 20.0f : 1.0f;

        drawManaBar(context, client, mana, alpha);
    }

    private static void updateUiTimer(UUID uuid, int current, int max) {
        // Reset timer if mana not full or just reached full
        if (current < max) {
            uiTimers.put(uuid, 60); // always visible while regaining
        } else {
            // If full, keep visible for 3 seconds (60 ticks)
            uiTimers.put(uuid, uiTimers.getOrDefault(uuid, 160) - 1);
        }
    }

    private static void drawManaBar(DrawContext context, MinecraftClient client, net.hil.hilmod.magic.mana.ModMana mana, float alpha) {
        int barWidth = 80;
        int barHeight = 8;
        int x = 10;
        int y = client.getWindow().getScaledHeight() - 50;

        int filled = 0;
        if (mana.getMaxMana() > 0) {
            filled = (int) ((long) mana.getCurrentMana() * barWidth / mana.getMaxMana());
        }

        // Convert alpha into ARGB color multiplier
        int alphaByte = (int) (alpha * 255) << 24;

        // Background (dark gray)
        context.fill(x, y, x + barWidth, y + barHeight, alphaByte | 0x333333);
        // Filled (blue)
        context.fill(x, y, x + filled, y + barHeight, alphaByte | 0x0000FF);

        // Text
        String text = mana.getCurrentMana() + " / " + mana.getMaxMana();
        int textWidth = client.textRenderer.getWidth(text);
        int textX = x + (barWidth - textWidth) / 2;
        context.drawText(client.textRenderer, Text.literal(text), textX, y - 10, alphaByte | 0xFFFFFF, false);
    }
}
