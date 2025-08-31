package net.hil.hilmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.hil.hilmod.HilMod;
import net.hil.hilmod.item.custom.ChiselItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CRYSTALITE = registerItem("crystalite", new Item(new Item.Settings()));
    public static final Item RAW_CRYSTALITE = registerItem("raw_crystalite", new Item(new Item.Settings()));

    public static final Item CHISEL = registerItem("chisel", new ChiselItem(new Item.Settings().maxDamage(32)));

    public static final Item CAULIFLOWER = registerItem("cauliflower", new Item(new Item.Settings().food(ModFoodComponents.CAULIFLOWER)));

    public static final Item ENDER_ROD = registerItem("ender_rod", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(HilMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        HilMod.LOGGER.info("Registering mod items for " + HilMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(CRYSTALITE);
            fabricItemGroupEntries.add(RAW_CRYSTALITE);
        });
    }

}
