package net.hil.hilmod.world.gen;

import net.hil.hilmod.block.ModBlocks;

public class ModWorldGeneration {
    public static void generateModWorldGen() {
        ModOreGeneration.generateOres();

        ModTreeGeneration.genorateTrees();
        ModBushGeneration.generateBushes();
    }

}
