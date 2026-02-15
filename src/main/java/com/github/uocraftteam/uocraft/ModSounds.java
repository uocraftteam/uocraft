package com.github.uocraftteam.uocraft;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class ModSounds {
    public static final DeferredRegister<@NotNull SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, Uocraft.MODID);

    public static final DeferredHolder<@NotNull SoundEvent, @NotNull SoundEvent> DEMASIADO_JAVA =
            SOUND_EVENTS.register("demasiado_java",
                    () -> SoundEvent.createVariableRangeEvent(
                            Identifier.fromNamespaceAndPath(Uocraft.MODID, "demasiado_java")
                            ));

    public static void registerSounds(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
