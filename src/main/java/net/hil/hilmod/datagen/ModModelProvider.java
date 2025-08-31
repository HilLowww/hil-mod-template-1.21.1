package net.hil.hilmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.hil.hilmod.block.ModBlocks;
import net.hil.hilmod.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool crytalitePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CRYSTALITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_CRYSTALITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRYSTALITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRYSTALITE_DEEPSLATE_ORE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MAGIC_BLOCK);

        crytalitePool.stairs(ModBlocks.CRYSTALITE_STAIRS);
        crytalitePool.slab(ModBlocks.CRYSTALITE_SLAB);

        crytalitePool.button(ModBlocks.CRYSTALITE_BUTTON);
        crytalitePool.pressurePlate(ModBlocks.CRYSTALITE_PRESSURE_PLATE);

        crytalitePool.fence(ModBlocks.CRYSTALITE_FENCE);
        crytalitePool.fenceGate(ModBlocks.CRYSTALITE_FENCE_GATE);
        crytalitePool.wall(ModBlocks.CRYSTALITE_WALL);

        blockStateModelGenerator.registerDoor(ModBlocks.CRYSTALITE_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.CRYSTALITE_TRAPDOOR);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.CRYSTALITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_CRYSTALITE, Models.GENERATED);

        itemModelGenerator.register(ModItems.CAULIFLOWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_ROD, Models.GENERATED);
    }
}
