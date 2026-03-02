package com.github.uocraftteam.uocraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public final class ServerBlockEntity extends BlockEntity {
    public ServerBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SERVER_BLOCK_ENTITY.get(), pos, blockState);
    }
}
