package com.github.uocraftteam.uocraft;

import com.github.uocraftteam.uocraft.block.ModBlocks;
import com.github.uocraftteam.uocraft.item.ModItems;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Uocraft.MODID)
public class Uocraft {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "uocraft";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Uocraft(IEventBus modEventBus, ModContainer modContainer) {

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        ModBlocks.registerBlocks(modEventBus);
        ModItems.registerItems(modEventBus);
        ModSounds.registerSounds(modEventBus);
    }
}
