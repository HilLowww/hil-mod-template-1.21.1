package net.hil.hilmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.hil.hilmod.block.ModBlocks;
import net.hil.hilmod.component.ModDataComponentTypes;
import net.hil.hilmod.item.ModItemGroups;
import net.hil.hilmod.item.ModItems;
import net.hil.hilmod.util.HammerUseageEvent;
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


        PlayerBlockBreakEvents.BEFORE.register(new HammerUseageEvent());
	}
}