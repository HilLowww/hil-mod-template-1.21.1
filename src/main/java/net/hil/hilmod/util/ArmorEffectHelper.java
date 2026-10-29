package net.hil.hilmod.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.hil.hilmod.basics.item.ModArmorMaterials;
import net.hil.hilmod.magic.mana.ManaRegenManager;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;

public class ArmorEffectHelper {
    public static void registerCustomArmorEffects() {
        ServerEntityEvents.EQUIPMENT_CHANGE.register((entity, slot, previousStack, currentStack) -> {
            if (!(entity instanceof ServerPlayerEntity player)) return;

            // Only check armor slots
            if (slot.getType() != EquipmentSlot.Type.HUMANOID_ARMOR) return;

            // Check if player has full Crystallite set
            if (ArmorEffectHelper.hasFullSetOfMaterial(player, ModArmorMaterials.CRYSTALITE_ARMOR_MATERIAL)) {
                ManaRegenManager.setRegenSource((ServerPlayerEntity) player, "crystalite_full_set", 2f);
            } else {
                ManaRegenManager.removeRegenSource((ServerPlayerEntity) player, "crystalite_full_set");
            }
        });
    }

    public static boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack chestplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty() && !chestplate.isEmpty() &&
                !leggings.isEmpty() && !boots.isEmpty();
    }

    public static boolean hasFullSetOfMaterial(PlayerEntity player, RegistryEntry<ArmorMaterial> material) {
        if (!hasFullSuitOfArmorOn(player)) return false;

        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack chestplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return allMatchMaterial(boots, leggings, chestplate, helmet, material);
    }

    private static boolean allMatchMaterial(ItemStack boots, ItemStack leggings, ItemStack chestplate, ItemStack helmet, RegistryEntry<ArmorMaterial> material) {
        return boots.getItem() instanceof ArmorItem bootsItem && bootsItem.getMaterial() == material &&
                leggings.getItem() instanceof ArmorItem leggingsItem && leggingsItem.getMaterial() == material &&
                chestplate.getItem() instanceof ArmorItem chestItem && chestItem.getMaterial() == material &&
                helmet.getItem() instanceof ArmorItem helmetItem && helmetItem.getMaterial() == material;
    }
}
