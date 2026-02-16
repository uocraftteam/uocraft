package com.github.uocraftteam.uocraft.block.custom;

import com.github.uocraftteam.uocraft.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class ComputerBlock extends Block {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty MOUSE = BooleanProperty.create("mouse");
    public static final BooleanProperty KEYBOARD = BooleanProperty.create("keyboard");
    public static final BooleanProperty MONITOR = BooleanProperty.create("monitor");

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

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide() && stack.is(ModItems.KEYBOARD.get())) {
            level.setBlock(pos, state.setValue(KEYBOARD, true), Block.UPDATE_ALL);
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }

        if (!level.isClientSide() && stack.is(ModItems.MOUSE.get())) {
            level.setBlock(pos, state.setValue(MOUSE, true), Block.UPDATE_ALL);
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }

        if (!level.isClientSide() && stack.is(ModItems.MONITOR.get())) {
            level.setBlock(pos, state.setValue(MONITOR, true), Block.UPDATE_ALL);
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            if (state.getValue(MOUSE)) {
                ItemStack mouseToDrop = new ItemStack(ModItems.MOUSE.get());
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), mouseToDrop);
                level.setBlock(pos, state.setValue(MOUSE, false), Block.UPDATE_ALL);
                level.playSound(null, pos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
            }

            else if (state.getValue(KEYBOARD)) {
                ItemStack mouseToDrop = new ItemStack(ModItems.KEYBOARD.get());
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), mouseToDrop);
                level.setBlock(pos, state.setValue(KEYBOARD, false), Block.UPDATE_ALL);
                level.playSound(null, pos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            else if (state.getValue(MONITOR)) {
                ItemStack mouseToDrop = new ItemStack(ModItems.MONITOR.get());
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), mouseToDrop);
                level.setBlock(pos, state.setValue(MONITOR, false), Block.UPDATE_ALL);
                level.playSound(null, pos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        if (!level.isClientSide()) {
            if (state.getValue(ComputerBlock.KEYBOARD)) {
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.MONITOR.get()));
            }
            if (state.getValue(ComputerBlock.MOUSE)) {
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.MOUSE.get()));
            }
            if (state.getValue(ComputerBlock.MONITOR)) {
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.MONITOR.get()));
            }
        }
        super.playerDestroy(level, player, pos, state, blockEntity, tool);
    }
}