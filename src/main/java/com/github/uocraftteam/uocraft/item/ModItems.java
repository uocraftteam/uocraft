package com.github.uocraftteam.uocraft.item;

import com.github.uocraftteam.uocraft.Uocraft;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class ModItems {
        public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Uocraft.MODID);

        public static final DeferredItem<@NotNull Item> MUSIC_DISK_DEMASIADO_JAVA = ITEMS.registerSimpleItem(
                "music_disk_demasiado_java",
                properties -> properties
        );

        public static void registerItems(IEventBus eventBus) {
            ITEMS.register(eventBus);
        }
}