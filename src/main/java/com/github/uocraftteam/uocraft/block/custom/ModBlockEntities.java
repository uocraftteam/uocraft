package com.github.uocraftteam.uocraft.block.custom;

import com.github.uocraftteam.uocraft.Uocraft;
import com.github.uocraftteam.uocraft.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Uocraft.MODID);

    public static final Supplier<BlockEntityType<ServerBlockEntity>> SERVER_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("server", () ->

            new BlockEntityType<>(
                    ServerBlockEntity::new,
                    false,
                    ModBlocks.SERVER_BLOCK.get()
            )
    );

    public static void registerBlockEntities(IEventBus bus) {
        BLOCK_ENTITY_TYPES.register(bus);
    }
}
