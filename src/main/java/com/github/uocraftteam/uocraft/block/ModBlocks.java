package com.github.uocraftteam.uocraft.block;

import com.github.uocraftteam.uocraft.Uocraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Uocraft.MODID);

    public static final DeferredBlock<Block> EII_BLOCK = BLOCKS.register("eii_block", registryName -> new Block(
            BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    .destroyTime(1.5f)
                    .explosionResistance(6.0f)
                    .sound(SoundType.STONE)
                    .lightLevel(state -> 0)
    ));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
