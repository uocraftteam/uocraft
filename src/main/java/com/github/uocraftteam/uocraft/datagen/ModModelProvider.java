package com.github.uocraftteam.uocraft.datagen;

import com.github.uocraftteam.uocraft.Uocraft;
import com.github.uocraftteam.uocraft.block.ModBlocks;
import com.github.uocraftteam.uocraft.block.custom.CoffeeMachineBlock;
import com.github.uocraftteam.uocraft.block.custom.ComputerBlock;
import com.github.uocraftteam.uocraft.item.ModItems;
import net.minecraft.client.data.models.*;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.client.renderer.block.model.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.jetbrains.annotations.NotNull;

public final class ModModelProvider extends ModelProvider {
    public static final PropertyDispatch<VariantMutator> HORIZONTAL_ROTATOR = PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
            .select(Direction.NORTH, BlockModelGenerators.NOP)
            .select(Direction.WEST, BlockModelGenerators.Y_ROT_270)
            .select(Direction.SOUTH, BlockModelGenerators.Y_ROT_180)
            .select(Direction.EAST, BlockModelGenerators.Y_ROT_90);

    BlockModelGenerators blockModels;
    ItemModelGenerators itemModels;

    public ModModelProvider(PackOutput output) {
        super(output, Uocraft.MODID);
    }

    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels, @NotNull ItemModelGenerators itemModels) {
        this.blockModels = blockModels;
        this.itemModels = itemModels;

        Block eii_block = ModBlocks.EII_BLOCK.get();
        blockModels.blockStateOutput.accept(createSimpleBlock(eii_block,
                BlockModelGenerators.plainVariant(TexturedModel.CUBE_TOP_BOTTOM.create(eii_block, blockModels.modelOutput))));

        generateComputerModel(blockModels);
        generateTableModels(blockModels);
        generateCoffeeMachineModel(blockModels, itemModels);

        itemModels.generateFlatItem(ModItems.MUSIC_DISK_DEMASIADO_JAVA.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.KEYBOARD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MOUSE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MONITOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.COFFEE.get(), ModelTemplates.FLAT_ITEM);
    }

    private void generateCoffeeMachineModel(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        Block coffeeMachine = ModBlocks.COFFEE_MACHINE.get();

        TextureMapping common_mapping = new TextureMapping()
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(ModBlocks.COFFEE_MACHINE.get(), "_front_top"))
                .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(ModBlocks.COFFEE_MACHINE.get(), "_bottom"))
                .put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(ModBlocks.COFFEE_MACHINE.get(), "_side"))
                .put(TextureSlot.EAST, TextureMapping.getBlockTexture(ModBlocks.COFFEE_MACHINE.get(), "_side"))
                .put(TextureSlot.WEST, TextureMapping.getBlockTexture(ModBlocks.COFFEE_MACHINE.get(), "_side"))
                .put(TextureSlot.UP, TextureMapping.getBlockTexture(ModBlocks.COFFEE_MACHINE.get(), "_top"));
        TextureMapping bottom_mapping = common_mapping.copy()
                .put(TextureSlot.NORTH, TextureMapping.getBlockTexture(ModBlocks.COFFEE_MACHINE.get(), "_front_bottom"));
        TextureMapping top_mapping = common_mapping.copy()
                .put(TextureSlot.NORTH, TextureMapping.getBlockTexture(ModBlocks.COFFEE_MACHINE.get(), "_front_top"));

        Identifier bottomId = ModelTemplates.CUBE.createWithSuffix(
                coffeeMachine,
                "_bottom",
                bottom_mapping,
                blockModels.modelOutput
        );
        Identifier topId = ModelTemplates.CUBE.createWithSuffix(
                coffeeMachine,
                "_top",
                top_mapping,
                blockModels.modelOutput
        );
        PropertyDispatch.C1<VariantMutator, Integer> coffeesRemainingDispatch = PropertyDispatch.modify(CoffeeMachineBlock.COFFEES_REMAINING);
        for (int i = 0; i <= CoffeeMachineBlock.MAX_COFFEES; i++) {
            coffeesRemainingDispatch.select(i, BlockModelGenerators.NOP);
        }

        blockModels.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(
                        coffeeMachine
                ).with(PropertyDispatch.initial( CoffeeMachineBlock.HALF)
                                .select(DoubleBlockHalf.UPPER, BlockModelGenerators.variant(new Variant(topId)))
                                .select(DoubleBlockHalf.LOWER, BlockModelGenerators.variant(new Variant(bottomId)))
                ).with(HORIZONTAL_ROTATOR
                ).with(coffeesRemainingDispatch));
        this.itemModels.generateFlatItem(coffeeMachine.asItem(), ModelTemplates.FLAT_ITEM);
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
        this.itemModels.generateFlatItem(computer.asItem(), ModelTemplates.FLAT_ITEM);
    }

    private void generateTableModels(BlockModelGenerators blockModels) {
        Block green_seminar_table = ModBlocks.GREEN_SEMINAR_TABLE.get();

        Identifier green_seminar_id = this.modLocation("block/green_seminar_table");

        Variant variant = new Variant(green_seminar_id);

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


    private void generateBlockItem(Block block, Identifier id) {
        this.itemModels.itemModelOutput.accept(
                block.asItem(),
                ItemModelUtils.plainModel(id)
        );
    }
}
