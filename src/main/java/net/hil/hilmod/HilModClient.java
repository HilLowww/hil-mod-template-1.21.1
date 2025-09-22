package net.hil.hilmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.hil.hilmod.basics.block.ModBlocks;
import net.hil.hilmod.basics.entity.ModEntities;
import net.hil.hilmod.basics.entity.client.MantisModel;
import net.hil.hilmod.basics.entity.client.MantisRenderer;
import net.hil.hilmod.magic.mana.ModMana;
import net.hil.hilmod.magic.mana.PlayerManaManager;
import net.hil.hilmod.util.ModModelPredicates;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.*;
import net.minecraft.text.Text;

import java.util.UUID;

public class HilModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRYSTALITE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRYSTALITE_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CAULIFLOWER_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUE_BERRY_BUSH, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DRIFTWOOD_SAPLING, RenderLayer.getCutout());

        ModModelPredicates.registerModelPredicates();

        EntityModelLayerRegistry.registerModelLayer(MantisModel.MANTIS, MantisModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MANTIS, MantisRenderer::new);

        HudRenderCallback.EVENT.register((matrices, tickDelta) -> drawManaBar(matrices));
    }

    private void drawManaBar(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        // Get mana by UUID (make sure PlayerManaManager.getMana(UUID) exists)
        UUID uuid = client.player.getUuid();
        ModMana mana = PlayerManaManager.getMana(uuid);
        if (mana == null) return; // may need sync from server in multiplayer

        int barWidth = 80;
        int barHeight = 8;
        int x = 10;
        int y = client.getWindow().getScaledHeight() - 50;

        int filled = 0;
        if (mana.getMaxMana() > 0) {
            filled = (int) ((long) mana.getCurrentMana() * barWidth / mana.getMaxMana());
        }

        // background (dark gray, ARGB)
        context.fill(x, y, x + barWidth, y + barHeight, 0xFF333333);
        // filled (blue)
        context.fill(x, y, x + filled, y + barHeight, 0xFF0000FF);

        // text above the bar
        String text = mana.getCurrentMana() + " / " + mana.getMaxMana();
        int textWidth = client.textRenderer.getWidth(text);
        int textX = x + (barWidth - textWidth) / 2;
        context.drawText(client.textRenderer, Text.literal(text), textX, y - 10, 0xFFFFFF, false);
    }
}
