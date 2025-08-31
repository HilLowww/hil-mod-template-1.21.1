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

                        entries.add(ModItems.ENDER_ROD);
                    }).build());

    public static final ItemGroup CRYSTELITE_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(HilMod.MOD_ID, "crystalite_blocks_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.CRYSTALITE_BLOCK))
                    .displayName(Text.translatable("itemgroup.hilmod.crystalite_blocks_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.CRYSTALITE_BLOCK);
                        entries.add(ModBlocks.RAW_CRYSTALITE_BLOCK);

                        entries.add(ModBlocks.CRYSTALITE_ORE);
                        entries.add(ModBlocks.CRYSTALITE_DEEPSLATE_ORE);

                        entries.add(ModBlocks.MAGIC_BLOCK);
                    }).build());

    public static void registerItemGroups() {
        HilMod.LOGGER.info("Register Mod Item Geoops for " + HilMod.MOD_ID);
    }
}
