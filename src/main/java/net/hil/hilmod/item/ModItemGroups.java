package net.hil.hilmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.hil.hilmod.HilMod;
import net.hil.hilmod.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup CRYSTELITE_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(HilMod.MOD_ID, "crystalite_items_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.CRYSTALITE))
                    .displayName(Text.translatable("itemgroup.hilmod.crystalite_items_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.RAW_CRYSTALITE);
                        entries.add(ModItems.CRYSTALITE);

                        entries.add(ModItems.CAULIFLOWER);

                        entries.add(ModItems.CHISEL);
                        entries.add(ModItems.CRYSALITE_HAMMER);

                        entries.add(ModItems.ENDER_ROD);

                        entries.add(ModItems.CRYSTALITE_SWORD);
                        entries.add(ModItems.CRYSTALITE_PICKAXE);
                        entries.add(ModItems.CRYSTALITE_SHOVEL);
                        entries.add(ModItems.CRYSTALITE_AXE);
                        entries.add(ModItems.CRYSTALITE_HOE);

                        entries.add(ModItems.CRYSTALITE_HELMET);
                        entries.add(ModItems.CRYSTALITE_CHESTPLATE);
                        entries.add(ModItems.CRYSTALITE_LEGGINGS);
                        entries.add(ModItems.CRYSTALITE_BOOTS);

                        entries.add(ModItems.CRYSTALITE_HORSE_ARMOR);
                        entries.add(ModItems.HIL_SMITHING_TEMPLATE);
                    }).build());

    public static final ItemGroup CRYSTELITE_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(HilMod.MOD_ID, "crystalite_blocks_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.CRYSTALITE_BLOCK))
                    .displayName(Text.translatable("itemgroup.hilmod.crystalite_blocks_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.CRYSTALITE_BLOCK);
                        entries.add(ModBlocks.RAW_CRYSTALITE_BLOCK);

                        entries.add(ModBlocks.CRYSTALITE_DOOR);
                        entries.add(ModBlocks.CRYSTALITE_FENCE);
                        entries.add(ModBlocks.CRYSTALITE_BUTTON);
                        entries.add(ModBlocks.CRYSTALITE_FENCE_GATE);
                        entries.add(ModBlocks.CRYSTALITE_PRESSURE_PLATE);
                        entries.add(ModBlocks.CRYSTALITE_SLAB);
                        entries.add(ModBlocks.CRYSTALITE_STAIRS);
                        entries.add(ModBlocks.CRYSTALITE_TRAPDOOR);
                        entries.add(ModBlocks.CRYSTALITE_WALL);

                        entries.add(ModBlocks.CRYSTALITE_ORE);
                        entries.add(ModBlocks.CRYSTALITE_DEEPSLATE_ORE);

                        entries.add(ModBlocks.MAGIC_BLOCK);
                        entries.add(ModBlocks.CRYSTALITE_LAMP);
                    }).build());

    public static void registerItemGroups() {
        HilMod.LOGGER.info("Register Mod Item Geoops for " + HilMod.MOD_ID);
    }
}
