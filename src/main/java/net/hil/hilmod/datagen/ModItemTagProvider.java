package net.hil.hilmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.hil.hilmod.item.ModItems;
import net.hil.hilmod.util.ModTags;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.CRYSTALITE)
                .add(Items.STICK);

        getOrCreateTagBuilder(ItemTags.SWORDS).add(ModItems.CRYSTALITE_SWORD);
        getOrCreateTagBuilder(ItemTags.PICKAXES).add(ModItems.CRYSTALITE_PICKAXE);
        getOrCreateTagBuilder(ItemTags.SHOVELS).add(ModItems.CRYSTALITE_SHOVEL);
        getOrCreateTagBuilder(ItemTags.AXES).add(ModItems.CRYSTALITE_AXE);
        getOrCreateTagBuilder(ItemTags.HOES).add(ModItems.CRYSTALITE_HOE);

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.CRYSTALITE_HELMET)
                .add(ModItems.CRYSTALITE_CHESTPLATE)
                .add(ModItems.CRYSTALITE_LEGGINGS)
                .add(ModItems.CRYSTALITE_BOOTS);

        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.CRYSTALITE);

        getOrCreateTagBuilder(ItemTags.TRIM_TEMPLATES)
                .add(ModItems.HIL_SMITHING_TEMPLATE);

        getOrCreateTagBuilder(ModTags.Items.LIGHTNING_ENCHANTABLE)
                .add(ModItems.CRYSTALISED_BOW)
                .addTag(ItemTags.SWORDS);
    }
}
