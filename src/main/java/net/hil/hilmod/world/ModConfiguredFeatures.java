package net.hil.hilmod.world;

import net.hil.hilmod.HilMod;
import net.hil.hilmod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> CRYSTALITE_ORE_KEY = registerKey("crystalite_ore_key");
    public static final RegistryKey<ConfiguredFeature<?, ?>> NETHER_CRYSTALITE_ORE_KEY = registerKey("nether_crystalite_ore_key");
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_CRYSTALITE_ORE_KEY = registerKey("end_crystalite_ore_key");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplaceables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> overworldCrystaliteOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.CRYSTALITE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.CRYSTALITE_DEEPSLATE_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> netherCrystaliteOres =
                List.of(OreFeatureConfig.createTarget(netherReplaceables, ModBlocks.CRYSTALITE_NETHER_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> endCrystaliteOres =
                List.of(OreFeatureConfig.createTarget(endReplaceables, ModBlocks.CRYSTALITE_END_ORE.getDefaultState()));

        register(context, CRYSTALITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldCrystaliteOres, 12));
        register(context, NETHER_CRYSTALITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherCrystaliteOres, 6));
        register(context, END_CRYSTALITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(endCrystaliteOres, 7));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(HilMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}