package com.github.uocraftteam.uocraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class ServerBlock extends Block implements EntityBlock {
    public ServerBlock(Properties p) {
        super(p);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ServerBlockEntity(blockPos, blockState);
    }
}
