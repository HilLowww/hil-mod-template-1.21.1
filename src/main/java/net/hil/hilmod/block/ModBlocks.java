package net.hil.hilmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.hil.hilmod.HilMod;
import net.hil.hilmod.block.custom.MagicBlock;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block CRYSTALITE_BLOCK = registerBlock("crystalite_block",
            new Block(AbstractBlock.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block RAW_CRYSTALITE_BLOCK = registerBlock("raw_crystalite_block",
            new Block(AbstractBlock.Settings.create().strength(3f)
                    .requiresTool()));

    public static final Block CRYSTALITE_ORE = registerBlock("crystalite_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5),
                    AbstractBlock.Settings.create().strength(3f).requiresTool()));

    public static final Block CRYSTALITE_DEEPSLATE_ORE = registerBlock("crystalite_deepslate_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 6),
                    AbstractBlock.Settings.create().strength(4f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE)));

    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            new MagicBlock(AbstractBlock.Settings.create().strength(1f).requiresTool()));

    public static final Block CRYSTALITE_STAIRS = registerBlock("crystalite_stairs",
            new StairsBlock(ModBlocks.CRYSTALITE_BLOCK.getDefaultState(),
            AbstractBlock.Settings.create().strength(2f).requiresTool()));
        public static final Block CRYSTALITE_SLAB = registerBlock("crystalite_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));

        public static final Block CRYSTALITE_BUTTON = registerBlock("crystalite_button",
            new ButtonBlock(BlockSetType.IRON,2, AbstractBlock.Settings.create().strength(2f).requiresTool().noCollision()));
        public static final Block CRYSTALITE_PRESSURE_PLATE = registerBlock("crystalite_pressure_plate",
            new PressurePlateBlock(BlockSetType.IRON, AbstractBlock.Settings.create().strength(2f).requiresTool()));

        public static final Block CRYSTALITE_FENCE = registerBlock("crystalite_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));
        public static final Block CRYSTALITE_FENCE_GATE = registerBlock("crystalite_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(2f).requiresTool()));
        public static final Block CRYSTALITE_WALL = registerBlock("crystalite_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(2f).requiresTool()));

        public static final Block CRYSTALITE_DOOR = registerBlock("crystalite_door",
            new DoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create().strength(2f).requiresTool().nonOpaque()));
        public static final Block CRYSTALITE_TRAPDOOR = registerBlock("crystalite_trapdoor",
            new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create().strength(2f).requiresTool().nonOpaque()));


    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(HilMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(HilMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        HilMod.LOGGER.info("Registering Blocks for" + HilMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModBlocks.CRYSTALITE_BLOCK);
            fabricItemGroupEntries.add(ModBlocks.RAW_CRYSTALITE_BLOCK);
        });
    }
}
