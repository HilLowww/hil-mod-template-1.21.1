package net.hil.hilmod.trim;

import net.hil.hilmod.HilMod;
import net.hil.hilmod.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.trim.ArmorTrimPattern;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModTrimPatterns {
    public static final RegistryKey<ArmorTrimPattern> HIL = RegistryKey.of(RegistryKeys.TRIM_PATTERN,
            Identifier.of(HilMod.MOD_ID, "hil"));

    public static void bootstrap(Registerable<ArmorTrimPattern> context) {
        register(context, ModItems.HIL_SMITHING_TEMPLATE, HIL);
    }

    private static void register(Registerable<ArmorTrimPattern> context, Item item, RegistryKey<ArmorTrimPattern> key) {
        ArmorTrimPattern trimPattern = new ArmorTrimPattern(key.getValue(), Registries.ITEM.getEntry(item),
                Text.translatable(Util.createTranslationKey("trim_pattern", key.getValue())), false);

        context.register(key, trimPattern);
    }
}