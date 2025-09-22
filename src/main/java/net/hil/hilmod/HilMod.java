package net.hil.hilmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.hil.hilmod.basics.block.ModBlocks;
import net.hil.hilmod.component.ModDataComponentTypes;
import net.hil.hilmod.basics.effect.ModEffects;
import net.hil.hilmod.basics.enchantment.ModEnchantmentsEffects;
import net.hil.hilmod.basics.entity.ModEntities;
import net.hil.hilmod.basics.entity.custom.MantisEntity;
import net.hil.hilmod.basics.item.ModItemGroups;
import net.hil.hilmod.basics.item.ModItems;
import net.hil.hilmod.basics.potion.ModPotions;
import net.hil.hilmod.magic.mana.ManaRegenManager;
import net.hil.hilmod.magic.mana.PlayerManaManager;
import net.hil.hilmod.util.HammerUseageEvent;
import net.hil.hilmod.world.gen.ModWorldGeneration;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.server.network.ServerPlayerEntity;
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
        ModEnchantmentsEffects.registerEnchentmentsefects();
        ModWorldGeneration.generateModWorldGen();
        ModEntities.registerEntities();

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

        CompostingChanceRegistry.INSTANCE.add(ModItems.CAULIFLOWER, 0.5f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.CAULIFLOWER_SEEDS, 0.3f);

        StrippableBlockRegistry.register(ModBlocks.DRIFTWOOD_LOG, ModBlocks.STRIPPED_DRIFTWOOD_LOG);
        StrippableBlockRegistry.register(ModBlocks.DRIFTWOOD_WOOD, ModBlocks.STRIPPED_DRIFTWOOD_WOOD);

        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_DRIFTWOOD_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_SAPLING, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_LEAVES, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_DRIFTWOOD_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_WOOD, 5, 5);

        FabricDefaultAttributeRegistry.register(ModEntities.MANTIS, MantisEntity.createAttributes());



//MAGIC SECTION
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            // if you have saved mana, load and call initializePlayer(player, savedMana)
            // otherwise initialize default:
            PlayerManaManager.initializePlayer(player);
        });

        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            // if you persist manually, save here
            PlayerManaManager.removePlayer(player);
        });
        ServerTickEvents.END_SERVER_TICK.register(ManaRegenManager::tick);
	}
}