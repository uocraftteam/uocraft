package com.github.uocraftteam.uocraft.item;

import com.github.uocraftteam.uocraft.Uocraft;
import com.github.uocraftteam.uocraft.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Uocraft.MODID);

    public static final Supplier<CreativeModeTab> UOCRAFT = CREATIVE_MODE_TAB.register(
            "uocraft_creative_mode_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.MONITOR.get())) //planned to be the university logo
                    .title(Component.literal("UOCraft"))
                    .displayItems(
                            (itemDisplayParameters, output) -> {
                                output.accept(ModItems.KEYBOARD);
                                output.accept(ModItems.MONITOR);
                                output.accept(ModItems.MOUSE);
                                output.accept(ModItems.COFFEE);
                                output.accept(ModItems.MUSIC_DISK_DEMASIADO_JAVA);
                                output.accept(ModBlocks.COMPUTER);
                                output.accept(ModBlocks.EII_BLOCK);
                                output.accept(ModBlocks.GREEN_SEMINAR_TABLE);
                            })
                    .build()
    );

    public static void registerCreativeModeTabs(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
