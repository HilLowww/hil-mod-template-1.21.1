package net.hil.hilmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.hil.hilmod.block.ModBlocks;
import net.hil.hilmod.item.ModItems;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        List<ItemConvertible> CRYSTALITE_SMELTABLES = List.of(ModItems.RAW_CRYSTALITE, ModBlocks.CRYSTALITE_ORE,
        ModBlocks.CRYSTALITE_DEEPSLATE_ORE);

        offerSmelting(recipeExporter, CRYSTALITE_SMELTABLES, RecipeCategory.MISC, ModItems.CRYSTALITE, 0.25f, 200, "crystalite");
        offerBlasting(recipeExporter, CRYSTALITE_SMELTABLES, RecipeCategory.MISC, ModItems.CRYSTALITE, 0.25f, 100, "crystalite");

        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModItems.CRYSTALITE, RecipeCategory.DECORATIONS, ModBlocks.CRYSTALITE_BLOCK);
        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_CRYSTALITE, RecipeCategory.DECORATIONS, ModBlocks.RAW_CRYSTALITE_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CRYSTALITE_SWORD)
                .pattern(" C ")
                .pattern(" C ")
                .pattern(" S ")
                .input('C', ModItems.CRYSTALITE)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.CRYSTALITE), conditionsFromItem(ModItems.CRYSTALITE))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CRYSTALITE_PICKAXE)
                .pattern("CCC")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', ModItems.CRYSTALITE)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.CRYSTALITE), conditionsFromItem(ModItems.CRYSTALITE))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CRYSTALITE_SHOVEL)
                .pattern(" C ")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', ModItems.CRYSTALITE)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.CRYSTALITE), conditionsFromItem(ModItems.CRYSTALITE))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CRYSTALITE_AXE)
                .pattern("CC ")
                .pattern("CS ")
                .pattern(" S ")
                .input('C', ModItems.CRYSTALITE)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.CRYSTALITE), conditionsFromItem(ModItems.CRYSTALITE))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CRYSTALITE_HOE)
                .pattern("CC ")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', ModItems.CRYSTALITE)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.CRYSTALITE), conditionsFromItem(ModItems.CRYSTALITE))
                .offerTo(recipeExporter);
    }
}
