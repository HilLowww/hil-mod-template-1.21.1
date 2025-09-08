package net.hil.hilmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.hil.hilmod.block.ModBlocks;
import net.hil.hilmod.block.custom.CrystaliteLampBlock;
import net.hil.hilmod.item.ModItems;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;

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

        Identifier lampOffIdentifier = TexturedModel.CUBE_ALL.upload(ModBlocks.CRYSTALITE_LAMP, blockStateModelGenerator.modelCollector);
        Identifier lampOnIdentifier = blockStateModelGenerator.createSubModel(ModBlocks.CRYSTALITE_LAMP, "_on", Models.CUBE_ALL, TextureMap::all);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.CRYSTALITE_LAMP)
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(CrystaliteLampBlock.CLICKED, lampOnIdentifier, lampOffIdentifier)));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.CRYSTALITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_CRYSTALITE, Models.GENERATED);

        itemModelGenerator.register(ModItems.CAULIFLOWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.BAR_BRAWL_MUSIC_DISC, Models.GENERATED);
        //itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_ROD, Models.GENERATED);

        itemModelGenerator.register(ModItems.CRYSTALITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CRYSTALITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CRYSTALITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CRYSTALITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CRYSTALITE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CRYSALITE_HAMMER, Models.HANDHELD);

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.CRYSTALITE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.CRYSTALITE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.CRYSTALITE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.CRYSTALITE_BOOTS));

        itemModelGenerator.register(ModItems.CRYSTALITE_HORSE_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.HIL_SMITHING_TEMPLATE, Models.GENERATED);
    }
}
