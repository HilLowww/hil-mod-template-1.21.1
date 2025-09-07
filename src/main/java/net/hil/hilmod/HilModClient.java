package net.hil.hilmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.impl.client.indigo.renderer.render.BlockRenderContext;
import net.hil.hilmod.block.ModBlocks;
import net.hil.hilmod.util.ModModelPredicates;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

public class HilModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRYSTALITE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRYSTALITE_TRAPDOOR, RenderLayer.getCutout());

        ModModelPredicates.registerModelPredicates();
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (entity instanceof SheepEntity sheepEntity && !world.isClient) {
                if (player.getMainHandStack().getItem() == Items.END_ROD) {
                    player.sendMessage(Text.literal("THE PLAYER HIT A SHEEEEEEEEP"));
                    player.getMainHandStack().decrement(1);
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 600, 1));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600, 2));
                    sheepEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 600, 2));
                }
                return ActionResult.PASS;
            }


            return ActionResult.SUCCESS;
        });
    }
}
