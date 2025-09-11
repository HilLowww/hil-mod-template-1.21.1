package net.hil.hilmod.enchantment;

import com.mojang.serialization.MapCodec;
import net.hil.hilmod.HilMod;
import net.hil.hilmod.enchantment.costom.LightningStrikeEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantmentsEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> LIGHTNING_STRIKE =
            registerEntityEffect("lighting_strike", LightningStrikeEnchantmentEffect.CODEC);


    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name,
                                                                                   MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(HilMod.MOD_ID, name), codec);
    }

    public static void registerEnchentmentsefects() {
        HilMod.LOGGER.info("REGISTERIng mod enchant effects"+ HilMod.MOD_ID);
    }
}
