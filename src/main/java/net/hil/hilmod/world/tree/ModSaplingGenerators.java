package net.hil.hilmod.world.tree;

import net.hil.hilmod.HilMod;
import net.hil.hilmod.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator DRIFTWOOD = new SaplingGenerator(HilMod.MOD_ID + ":driftwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.DRIFTWOOD_KEY), Optional.empty());
}
