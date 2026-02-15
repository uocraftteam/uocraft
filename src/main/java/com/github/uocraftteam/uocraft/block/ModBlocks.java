package com.github.uocraftteam.uocraft.block;

import com.github.uocraftteam.uocraft.Uocraft;
import com.github.uocraftteam.uocraft.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Uocraft.MODID);

    public static final DeferredBlock<@NotNull Block> EII_BLOCK = registerBlock("eii_block", registryName -> new Block(
            BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    .destroyTime(1.5f)
                    .explosionResistance(6.0f)
                    .sound(SoundType.STONE)
                    .lightLevel(state -> 0)
    ));

    private static DeferredBlock<@NotNull Block> registerBlock(String name, Function<Identifier, ? extends Block> func){
        DeferredBlock<@NotNull Block> block =  BLOCKS.register(name, func);
        registerBlockItem(name, block);
        return block;
    }

    private static void registerBlockItem(String name, DeferredBlock<@NotNull Block> block) {
        ModItems.ITEMS.registerSimpleBlockItem(name, block);
    }

    public static void registerBlocks(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
