package com.github.uocraftteam.uocraft.block;

import com.github.uocraftteam.uocraft.Uocraft;
import com.github.uocraftteam.uocraft.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Uocraft.MODID);

    // 1. Use the helper method so the Item is registered too!
    public static final DeferredBlock<Block> EII_BLOCK = registerBlock("eii_block", (id) ->
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE)
                    .setId(ResourceKey.create(Registries.BLOCK, id))
            )
    );

    // 2. Updated helper to match the 1.21.1 requirements
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<Identifier, T> factory) {
        DeferredBlock<T> block = BLOCKS.register(name, factory);
        // This ensures every block registered through here gets a corresponding BlockItem
        registerBlockItem(name, (DeferredBlock<Block>) block);
        return block;
    }

    private static void registerBlockItem(String name, DeferredBlock<Block> block) {
        ModItems.ITEMS.register(name, (id) ->
                new BlockItem(block.get(), new Item.Properties()
                        .setId(ResourceKey.create(Registries.ITEM, id)) // Mandatory for 1.21.1
                )
        );
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}