package com.github.uocraftteam.uocraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class ComputerBlock extends Block {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty MOUSE =  BooleanProperty.create("mouse");
    public static final BooleanProperty KEYBOARD =  BooleanProperty.create("keyboard");
    public static final BooleanProperty MONITOR  =  BooleanProperty.create("monitor");

    public static final VoxelShape SHAPE_NORTH = Shapes.or(
            Block.box(10.0, 0.0, 1.0, 16.0, 12.0, 15.0)
    );


    public ComputerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(MOUSE, false)
                .setValue(KEYBOARD, false)
                .setValue(MONITOR, false)
        );
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(MOUSE).add(KEYBOARD).add(MONITOR);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {

        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(MOUSE, false)
                .setValue(KEYBOARD, false)
                .setValue(MONITOR, false);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_NORTH;
    }

}