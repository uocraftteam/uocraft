package com.github.uocraftteam.uocraft.block;

import com.github.uocraftteam.uocraft.Uocraft;
import com.github.uocraftteam.uocraft.block.custom.CoffeeMachineBlock;
import com.github.uocraftteam.uocraft.block.custom.ComputerBlock;
import com.github.uocraftteam.uocraft.block.custom.ServerBlock;
import com.github.uocraftteam.uocraft.block.custom.TableBlock;
import com.github.uocraftteam.uocraft.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
            BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
    ));
    public static final DeferredBlock<Block> COMPUTER = registerBlock("computer",
            identifier -> new ComputerBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, identifier))
                    .destroyTime(1.5f)
                    .explosionResistance(6.0f)
                    .sound(SoundType.STONE)
                    .lightLevel(state -> state.getValue(ComputerBlock.KEYBOARD) && state.getValue(ComputerBlock.MOUSE) && state.getValue(ComputerBlock.MONITOR)? 2 : 0)
                    .noOcclusion()
            ));

    public static final DeferredBlock<Block> GREEN_SEMINAR_TABLE = registerBlock("green_seminar_table",
            identifier -> new TableBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, identifier))
                    .destroyTime(1.0f)
                    .explosionResistance(3.0f)
                    .sound(SoundType.WOOD)
                    .lightLevel(state -> 2)
                    .noOcclusion()
            ));

    public static final DeferredBlock<Block> COFFEE_MACHINE = registerBlock("coffee_machine",
            identifier -> new CoffeeMachineBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, identifier))
                    .destroyTime(1.5f)
                    .explosionResistance(6.0f)
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> SERVER_BLOCK = registerBlock("server",
            identifier -> new ServerBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, identifier))
                    .destroyTime(1.5f)
                    .explosionResistance(6.0f)
                    .sound(SoundType.STONE)
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
