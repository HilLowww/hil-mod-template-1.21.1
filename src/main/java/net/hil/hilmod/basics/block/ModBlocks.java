package net.hil.hilmod.basics.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.hil.hilmod.HilMod;
import net.hil.hilmod.basics.block.custom.*;
import net.hil.hilmod.basics.sound.ModSounds;
import net.hil.hilmod.world.tree.ModSaplingGenerators;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
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

    public static final Block CRYSTALITE_END_ORE = registerBlock("crystalite_end_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(4, 8),
                    AbstractBlock.Settings.create().strength(7f).requiresTool()));
    public static final Block CRYSTALITE_NETHER_ORE = registerBlock("crystalite_nether_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(1, 5),
                    AbstractBlock.Settings.create().strength(3f).requiresTool()));


    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            new MagicBlock(AbstractBlock.Settings.create().strength(1f).requiresTool().sounds(ModSounds.MAGIC_BLOCK_SOUNDS)));

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

        public static final Block CRYSTALITE_LAMP = registerBlock("crystalite_lamp",
                new CrystaliteLampBlock(AbstractBlock.Settings.create()
                        .strength(1f).requiresTool().luminance(state ->state.get(CrystaliteLampBlock.CLICKED) ? 15 : 0)));

        public static final Block DRIFTWOOD_LOG = registerBlock("driftwood_log",
                new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block DRIFTWOOD_WOOD = registerBlock("driftwood_wood",
                new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_DRIFTWOOD_LOG = registerBlock("stripped_driftwood_log",
                new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final Block STRIPPED_DRIFTWOOD_WOOD = registerBlock("striped_driftwood_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)));

     public static final Block DRIFTWOOD_PLANKS = registerBlock("driftwood_planks",
           new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
     public static final Block DRIFTWOOD_LEAVES = registerBlock("driftwood_leaves",
        new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));

        public static final Block DRIFTWOOD_SAPLING = registerBlock("driftwood_sapling",
                new ModMagicSaplingBlock(ModSaplingGenerators.DRIFTWOOD, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING), ModBlocks.MAGIC_BLOCK));

        public static final Block CAULIFLOWER_CROP = registerBlockNoItem("cauliflower_crop",
                new CauliflowerCropBlock(AbstractBlock.Settings.create().noCollision()
                        .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.DARK_GREEN)));

        public static final Block BLUE_BERRY_BUSH = registerBlockNoItem("blue_berry_bush",
                new BlueBerryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));

    public static final Block CHAIR = registerBlock("chair",
            new ChairBlock(AbstractBlock.Settings.create().nonOpaque()));


    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(HilMod.MOD_ID, name), block);
    }
    public static Block registerBlockNoItem(String name, Block block) {
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
