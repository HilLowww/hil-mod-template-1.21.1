package net.hil.hilmod;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.hil.hilmod.item.ModItems;

public class ModFuels {
    public static void registerModFuels() {;
        FuelRegistry.INSTANCE.add(ModItems.ENDER_ROD, 2000);
    }
}
