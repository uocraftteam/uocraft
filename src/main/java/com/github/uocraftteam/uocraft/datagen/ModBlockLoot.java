package com.github.uocraftteam.uocraft.datagen;

import com.github.uocraftteam.uocraft.block.ModBlocks;
import com.github.uocraftteam.uocraft.block.custom.CoffeeMachineBlock;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.jspecify.annotations.NonNull;

import java.util.Set;

public final class ModBlockLoot extends BlockLootSubProvider {

    ModBlockLoot(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.EII_BLOCK.get());
        this.dropSelf(ModBlocks.COMPUTER.get());
        this.dropSelf(ModBlocks.GREEN_SEMINAR_TABLE.get());
        this.dropSelf(ModBlocks.SERVER_BLOCK.get());
        this.add(
                ModBlocks.COFFEE_MACHINE.get(),
                block -> createSinglePropConditionTable(block, CoffeeMachineBlock.HALF, DoubleBlockHalf.LOWER)
        );
    }
    @Override
    protected @NonNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries()
                .stream()
                .map(Holder::value)::iterator;
    }
}
