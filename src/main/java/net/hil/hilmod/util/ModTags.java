package net.hil.hilmod.util;

import net.hil.hilmod.HilMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_CRYSTALITE_TOOLS = createTag("needs_crystalite_tools");
        public static final TagKey<Block> INCORECT_FOR_CRYSTALITE_TOOL = createTag("incorect_for_crystalite_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(HilMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        public static final TagKey<Item> LIGHTNING_ENCHANTABLE = createTag("lightning_enchantable");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(HilMod.MOD_ID, name));
        }
    }
}
