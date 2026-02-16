package com.github.uocraftteam.uocraft.datagen;

import com.github.uocraftteam.uocraft.Uocraft;
import com.github.uocraftteam.uocraft.block.ModBlocks;
import com.github.uocraftteam.uocraft.block.custom.ComputerBlock;
import com.github.uocraftteam.uocraft.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.client.renderer.block.model.multipart.CombinedCondition;
import net.minecraft.client.renderer.block.model.multipart.Condition;
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

        Block computer = ModBlocks.COMPUTER.get();
        Identifier computer_tower_modelLoc = this.modLocation("block/computer_tower");
        Identifier computer_keyboard_modelLoc = this.modLocation("block/computer_keyboard");
        Identifier computer_mouse_modelLoc = this.modLocation("block/computer_mouse");
        Identifier computer_monitor_modelLoc = this.modLocation("block/computer_monitor");

        Variant computer_tower = new Variant(computer_tower_modelLoc);
        Variant computer_keyboard = new Variant(computer_keyboard_modelLoc);
        Variant computer_mouse = new Variant(computer_mouse_modelLoc);
        Variant computer_monitor = new Variant(computer_monitor_modelLoc);

        blockModels.blockStateOutput.accept(
                MultiPartGenerator.multiPart(computer)
                        .with(
                                BlockModelGenerators.variant(computer_tower)
                        ).with(
                                BlockModelGenerators.condition().term(ComputerBlock.MONITOR, true),
                                BlockModelGenerators.variant(computer_keyboard)
                        ).with(
                                BlockModelGenerators.condition().term(ComputerBlock.KEYBOARD, true),
                                BlockModelGenerators.variant(computer_monitor)
                        ).with(
                                BlockModelGenerators.condition().term(ComputerBlock.MOUSE, true),
                                BlockModelGenerators.variant(computer_mouse)
                        )
        );

        itemModels.generateFlatItem(ModItems.MUSIC_DISK_DEMASIADO_JAVA.get(), ModelTemplates.FLAT_ITEM);
    }


}
