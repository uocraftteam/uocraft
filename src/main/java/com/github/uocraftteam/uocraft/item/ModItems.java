package com.github.uocraftteam.uocraft.item;

import com.github.uocraftteam.uocraft.Uocraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.swing.*;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Uocraft.MODID);

    public static final DeferredItem<Item> MOUSE = ITEMS.register("mouse",
            (id) -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, id))
                    .jukeboxPlayable(
                    ResourceKey.create(
                            Registries.JUKEBOX_SONG,
                            Identifier.withDefaultNamespace("mall")
                    )
            )));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
