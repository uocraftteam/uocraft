package com.github.uocraftteam.uocraft.datagen;

import com.github.uocraftteam.uocraft.block.ModBlocks;
import com.github.uocraftteam.uocraft.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class ModRecipeProvider extends RecipeProvider {
    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        var recipes = List.of(
            this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.EII_BLOCK.get(), 2)
                    .define('S', Blocks.SANDSTONE)
                    .pattern("S")
                    .pattern("S")
                    .unlockedBy("has_sandstone", has(Blocks.SANDSTONE)),

            this.shaped(RecipeCategory.REDSTONE, ModBlocks.COMPUTER.get(), 1)
                    .define('I', Items.IRON_INGOT)
                    .define('R', Items.REDSTONE_BLOCK)
                    .define('G', Items.GOLD_INGOT)
                    .pattern("GI")
                    .pattern("RI")
                    .pattern("GI")
                    .unlockedBy("has_redstone", has(Items.REDSTONE)),

            this.shaped(RecipeCategory.REDSTONE, ModItems.KEYBOARD.get(), 1)
                    .define('I', Items.IRON_INGOT)
                    .define('G', Items.GOLD_INGOT)
                    .define('R', Items.REDSTONE)
                    .define('K', Items.DRIED_KELP)
                    .pattern("IKI")
                    .pattern("GRG")
                    .unlockedBy("has_redstone", has(Items.REDSTONE)),

            this.shaped(RecipeCategory.REDSTONE, ModItems.MOUSE.get(), 1)
                    .define('I', Items.IRON_INGOT)
                    .define('G', Items.GOLD_INGOT)
                    .define('K', Items.DRIED_KELP)
                    .define('R', Items.REDSTONE)
                    .pattern("KI")
                    .pattern("RG")
                    .unlockedBy("has_redstone", has(Items.REDSTONE)),

            this.shaped(RecipeCategory.REDSTONE, ModItems.MONITOR.get(), 1)
                    .define('I', Items.IRON_INGOT)
                    .define('K', Items.DRIED_KELP)
                    .define('R', Items.REDSTONE)
                    .pattern("KKK")
                    .pattern("IRI")
                    .pattern(" I ")
                    .unlockedBy("has_redstone", has(Items.REDSTONE)),

            this.shaped(RecipeCategory.REDSTONE, ModBlocks.COFFEE_MACHINE.get(), 1)
                    .define('I', Items.IRON_BLOCK)
                    .define('L', Items.LAPIS_BLOCK)
                    .define('K', Items.DRIED_KELP)
                    .pattern("LK")
                    .pattern("IK")
                    .pattern("KK")
                    .unlockedBy("has_redstone", has(Items.REDSTONE)),

            this.shaped(RecipeCategory.DECORATIONS, ModBlocks.GREEN_SEMINAR_TABLE.get(), 3)
                    .define('I', Items.IRON_INGOT)
                    .define('G', Items.GREEN_DYE)
                    .define('P', ItemTags.PLANKS)
                    .pattern("PGP")
                    .pattern("I I")
                    .pattern("I I")
                    .unlockedBy("has_redstone", has(Items.REDSTONE))
        );

        for (var recipe: recipes) {
            recipe.save(this.output);
        }

    }

    public static class Runner extends RecipeProvider.Runner {
        // Get the parameters from the `GatherDataEvent`s.
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new ModRecipeProvider(provider, output);
        }

        @Override
        public String getName() {
            return "Uocraft Recipes";
        }
    }
}
