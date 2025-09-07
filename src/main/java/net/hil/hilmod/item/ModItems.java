package net.hil.hilmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.hil.hilmod.HilMod;
import net.hil.hilmod.item.custom.ChiselItem;
import net.hil.hilmod.item.custom.HammerItem;
import net.hil.hilmod.item.custom.ModArmorItem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.lang.invoke.SwitchPoint;
import java.util.List;

public class ModItems {
    public static final Item CRYSTALITE = registerItem("crystalite", new Item(new Item.Settings()));
    public static final Item RAW_CRYSTALITE = registerItem("raw_crystalite", new Item(new Item.Settings()));

    public static final Item CHISEL = registerItem("chisel", new ChiselItem(new Item.Settings().maxDamage(32)));

    public static final Item CAULIFLOWER = registerItem("cauliflower", new Item(new Item.Settings().food(ModFoodComponents.CAULIFLOWER)));

    public static final Item ENDER_ROD = registerItem("ender_rod", new Item(new Item.Settings()) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Text.translatable("tooltip.hilmod.end_rod.shift_down"));
            } else {
                tooltip.add(Text.translatable("tooltip.hilmod.end_rod"));
            }
            super.appendTooltip(stack, context, tooltip, type);
        }
    });
    public static final Item CRYSTALITE_SWORD = registerItem("crystalite_sword",
            new SwordItem(ModToolMaterials.CRYSTALITE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.CRYSTALITE, 3, -2.4f))));
    public static final Item CRYSTALITE_PICKAXE = registerItem("crystalite_pickaxe",
            new PickaxeItem(ModToolMaterials.CRYSTALITE, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.CRYSTALITE, 1, -2.8f))));
    public static final Item CRYSTALITE_SHOVEL = registerItem("crystalite_shovel",
            new ShovelItem(ModToolMaterials.CRYSTALITE, new Item.Settings()
                    .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.CRYSTALITE, 1.5f, -3f))));
    public static final Item CRYSTALITE_AXE = registerItem("crystalite_axe",
            new AxeItem(ModToolMaterials.CRYSTALITE, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.CRYSTALITE, 6, -3.2f))));
    public static final Item CRYSTALITE_HOE = registerItem("crystalite_hoe",
            new HoeItem(ModToolMaterials.CRYSTALITE, new Item.Settings()
                    .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.CRYSTALITE, 0, -3f))));

    public static final Item CRYSALITE_HAMMER = registerItem("crystalite_hammer",
            new HammerItem(ModToolMaterials.CRYSTALITE, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.CRYSTALITE, 7, -3.4f))));

    public static final Item CRYSTALITE_HELMET = registerItem("crystalite_helmet",
            new ModArmorItem(ModArmorMaterials.CRYSTALITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(15))));
    public static final Item CRYSTALITE_CHESTPLATE = registerItem("crystalite_chestplate",
            new ArmorItem(ModArmorMaterials.CRYSTALITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(15))));
    public static final Item CRYSTALITE_LEGGINGS = registerItem("crystalite_leggings",
            new ArmorItem(ModArmorMaterials.CRYSTALITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(15))));
    public static final Item CRYSTALITE_BOOTS = registerItem("crystalite_boots",
            new ArmorItem(ModArmorMaterials.CRYSTALITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(15))));

    public static final Item CRYSTALITE_HORSE_ARMOR = registerItem("crystalite_horse_armor",
            new AnimalArmorItem(ModArmorMaterials.CRYSTALITE_ARMOR_MATERIAL, AnimalArmorItem.Type.EQUESTRIAN, false, new Item.Settings().maxCount(1)));

    public static final Item HIL_SMITHING_TEMPLATE = registerItem("hil_armor_trim_smithing_template",
            SmithingTemplateItem.of(Identifier.of(HilMod.MOD_ID, "hil"), FeatureFlags.VANILLA));

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
