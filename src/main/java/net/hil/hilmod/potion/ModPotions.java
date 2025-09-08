package net.hil.hilmod.potion;

import net.hil.hilmod.HilMod;
import net.hil.hilmod.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModPotions {

    public static final RegistryEntry<Potion> SLIMEY_POTION = registerPostion("slimey_potion",
            new Potion(new StatusEffectInstance(ModEffects.SLIMEY, 1200, 0)));


    private static RegistryEntry<Potion> registerPostion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(HilMod.MOD_ID, name), potion);
    }


    public static void registerPotions() {
        HilMod.LOGGER.info("Registering potions" + HilMod.MOD_ID);
    }
}
