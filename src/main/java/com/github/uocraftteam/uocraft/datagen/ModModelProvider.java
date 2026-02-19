package com.github.uocraftteam.uocraft.datagen;

import com.github.uocraftteam.uocraft.Uocraft;
import com.github.uocraftteam.uocraft.block.ModBlocks;
import com.github.uocraftteam.uocraft.block.custom.ComputerBlock;
import com.github.uocraftteam.uocraft.item.ModItems;
import com.google.errorprone.annotations.Var;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.client.renderer.block.model.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, Uocraft.MODID);
    }

    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels, @NotNull ItemModelGenerators itemModels) {

        Block eii_block = ModBlocks.EII_BLOCK.get();
        blockModels.blockStateOutput.accept(createSimpleBlock(eii_block,
                BlockModelGenerators.plainVariant(TexturedModel.CUBE_TOP_BOTTOM.create(eii_block, blockModels.modelOutput))));
        blockModels.blockStateOutput.accept(createSimpleBlock(ModBlocks.COFFEE_MACHINE.get(),
                BlockModelGenerators.plainVariant(TexturedModel.CUBE.create(ModBlocks.COFFEE_MACHINE.get(), blockModels.modelOutput))
                ));

        generateComputerModel(blockModels);
        generateTableModels(blockModels);

        itemModels.generateFlatItem(ModItems.MUSIC_DISK_DEMASIADO_JAVA.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.KEYBOARD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MOUSE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MONITOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.COFFEE.get(), ModelTemplates.FLAT_ITEM);
    }

    private void generateComputerModel(@NotNull BlockModelGenerators blockModels) {
        Block computer = ModBlocks.COMPUTER.get();
        Identifier computer_tower_modelLoc = this.modLocation("block/computer_tower");
        Identifier computer_keyboard_modelLoc = this.modLocation("block/computer_keyboard");
        Identifier computer_mouse_modelLoc = this.modLocation("block/computer_mouse");
        Identifier computer_monitor_modelLoc = this.modLocation("block/computer_monitor");

        Variant computer_tower = new Variant(computer_tower_modelLoc);
        Variant computer_keyboard = new Variant(computer_keyboard_modelLoc);
        Variant computer_mouse = new Variant(computer_mouse_modelLoc);
        Variant computer_monitor = new Variant(computer_monitor_modelLoc);

        MultiPartGenerator computer_generator = MultiPartGenerator.multiPart(computer);

        for (Direction direction : ComputerBlock.FACING.getPossibleValues()) {
            VariantMutator rotation = getRotationMutator(direction);

            computer_generator.with(
                    BlockModelGenerators.condition().term(BlockStateProperties.HORIZONTAL_FACING, direction),
                    BlockModelGenerators.variant(computer_tower).with(rotation)
            );
            computer_generator.with(
                    BlockModelGenerators.condition()
                            .term(BlockStateProperties.HORIZONTAL_FACING, direction)
                            .term(ComputerBlock.KEYBOARD, true),
                    BlockModelGenerators.variant(computer_keyboard).with(rotation)
            );
            computer_generator.with(
                    BlockModelGenerators.condition()
                            .term(BlockStateProperties.HORIZONTAL_FACING, direction)
                            .term(ComputerBlock.MOUSE, true),
                    BlockModelGenerators.variant(computer_mouse).with(rotation)
            );
            computer_generator.with(
                    BlockModelGenerators.condition()
                            .term(BlockStateProperties.HORIZONTAL_FACING, direction)
                            .term(ComputerBlock.MONITOR, true),
                    BlockModelGenerators.variant(computer_monitor).with(rotation)
            );
        }

        blockModels.blockStateOutput.accept(computer_generator);
    }

    private void generateTableModels(BlockModelGenerators blockModels) {
        Block green_seminar_table = ModBlocks.GREEN_SEMINAR_TABLE.get();

        Identifier green_seminar_modelLoc = this.modLocation("block/green_seminar_table");

        Variant variant = new Variant(green_seminar_modelLoc);

        MultiVariantGenerator green_seminar_table_generator = MultiVariantGenerator.dispatch(
                green_seminar_table,
                BlockModelGenerators.variant(variant)
        ).with(
                PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
                        .select(Direction.NORTH, BlockModelGenerators.NOP)
                        .select(Direction.EAST,  BlockModelGenerators.Y_ROT_90)
                        .select(Direction.SOUTH, BlockModelGenerators.Y_ROT_180)
                        .select(Direction.WEST,  BlockModelGenerators.Y_ROT_270)
        );

        blockModels.blockStateOutput.accept(green_seminar_table_generator);
    }

    public static MultiVariantGenerator createSimpleBlock(Block block, MultiVariant variants) {
        return MultiVariantGenerator.dispatch(block, variants);
    }

    private VariantMutator getRotationMutator(Direction dir) {
        return switch (dir) {
            case EAST -> BlockModelGenerators.Y_ROT_90;
            case SOUTH -> BlockModelGenerators.Y_ROT_180;
            case WEST -> BlockModelGenerators.Y_ROT_270;
            default -> BlockModelGenerators.NOP; // NORTH
        };
    }
}
