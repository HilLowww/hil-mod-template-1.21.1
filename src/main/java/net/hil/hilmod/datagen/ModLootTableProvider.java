package net.hil.hilmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.hil.hilmod.block.ModBlocks;
import net.hil.hilmod.block.custom.BlueBerryBushBlock;
import net.hil.hilmod.block.custom.CauliflowerCropBlock;
import net.hil.hilmod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        addDrop(ModBlocks.CRYSTALITE_BLOCK);
        addDrop(ModBlocks.MAGIC_BLOCK);
        addDrop(ModBlocks.RAW_CRYSTALITE_BLOCK);

        addDrop(ModBlocks.CRYSTALITE_ORE, oreDrops(ModBlocks.CRYSTALITE_ORE, ModItems.RAW_CRYSTALITE));
        addDrop(ModBlocks.CRYSTALITE_DEEPSLATE_ORE, multipleOreDrops(ModBlocks.CRYSTALITE_DEEPSLATE_ORE, ModItems.RAW_CRYSTALITE, 3, 7));

        addDrop(ModBlocks.CRYSTALITE_STAIRS);
        addDrop(ModBlocks.CRYSTALITE_SLAB, slabDrops(ModBlocks.CRYSTALITE_SLAB));

        addDrop(ModBlocks.CRYSTALITE_BUTTON);
        addDrop(ModBlocks.CRYSTALITE_PRESSURE_PLATE);

        addDrop(ModBlocks.CRYSTALITE_WALL);
        addDrop(ModBlocks.CRYSTALITE_FENCE);
        addDrop(ModBlocks.CRYSTALITE_FENCE_GATE);

        addDrop(ModBlocks.CRYSTALITE_DOOR, doorDrops(ModBlocks.CRYSTALITE_DOOR));
        addDrop(ModBlocks.CRYSTALITE_TRAPDOOR);

        BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(ModBlocks.CAULIFLOWER_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(CauliflowerCropBlock.AGE, CauliflowerCropBlock.MAX_AGE));
        this.addDrop(ModBlocks.CAULIFLOWER_CROP, this.cropDrops(ModBlocks.CAULIFLOWER_CROP, ModItems.CAULIFLOWER, ModItems.CAULIFLOWER_SEEDS, builder2));

        this.addDrop(
                ModBlocks.BLUE_BERRY_BUSH,
                block -> this.applyExplosionDecay(
                        block,
                        LootTable.builder()
                                .pool(
                                        LootPool.builder()
                                                .conditionally(
                                                        BlockStatePropertyLootCondition.builder(ModBlocks.BLUE_BERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(BlueBerryBushBlock.AGE, 3))
                                                )
                                                .with(ItemEntry.builder(ModItems.BLUE_BERRIES))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                                                .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                                )
                                .pool(
                                        LootPool.builder()
                                                .conditionally(
                                                        BlockStatePropertyLootCondition.builder(ModBlocks.BLUE_BERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(BlueBerryBushBlock.AGE, 2))
                                                )
                                                .with(ItemEntry.builder(ModItems.BLUE_BERRIES))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                                .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                                )
                )
        );


        addDrop(ModBlocks.CRYSTALITE_END_ORE, multipleOreDrops(ModBlocks.CRYSTALITE_END_ORE, ModItems.RAW_CRYSTALITE, 4, 9));
        addDrop(ModBlocks.CRYSTALITE_NETHER_ORE, multipleOreDrops(ModBlocks.CRYSTALITE_NETHER_ORE, ModItems.RAW_CRYSTALITE, 2, 8));

    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
