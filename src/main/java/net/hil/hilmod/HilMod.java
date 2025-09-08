package net.hil.hilmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.hil.hilmod.block.ModBlocks;
import net.hil.hilmod.component.ModDataComponentTypes;
import net.hil.hilmod.effect.ModEffects;
import net.hil.hilmod.item.ModItemGroups;
import net.hil.hilmod.item.ModItems;
import net.hil.hilmod.potion.ModPotions;
import net.hil.hilmod.util.HammerUseageEvent;
import net.hil.hilmod.util.ModTags;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// LLLLLLL
public class HilMod implements ModInitializer {
	public static final String MOD_ID = "hilmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItemGroups.registerItemGroups();

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModFuels.registerModFuels();
        ModDataComponentTypes.registerDataComponentTypes();
        ModEffects.registerEffects();
        ModPotions.registerPotions();

        PlayerBlockBreakEvents.BEFORE.register(new HammerUseageEvent());


        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.SLIMEY_POTION);
        });
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


            return ActionResult.PASS;
        });
	}
}