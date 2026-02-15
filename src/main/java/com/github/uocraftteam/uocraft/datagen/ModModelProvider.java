package com.github.uocraftteam.uocraft.datagen;

import com.github.uocraftteam.uocraft.Uocraft;
import com.github.uocraftteam.uocraft.block.ModBlocks;
import com.github.uocraftteam.uocraft.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, Uocraft.MODID);
    }

    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels, @NotNull ItemModelGenerators itemModels) {

        Block eii_block = ModBlocks.EII_BLOCK.get();
        Identifier eii_block_modelLoc = this.modLocation("block/eii_block");
        blockModels.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(
                        eii_block,
                        BlockModelGenerators.variant(new Variant(eii_block_modelLoc))
                )
        );
        itemModels.generateFlatItem(ModItems.MUSIC_DISK_DEMASIADO_JAVA.get(), ModelTemplates.FLAT_ITEM);
    }


}
