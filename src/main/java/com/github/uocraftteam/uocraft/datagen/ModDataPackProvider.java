package com.github.uocraftteam.uocraft.datagen;

import com.github.uocraftteam.uocraft.Uocraft;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public final class ModDataPackProvider extends DatapackBuiltinEntriesProvider {

    public ModDataPackProvider(PackOutput output, CompletableFuture<RegistrySetBuilder.PatchedRegistries> registries, Set<String> modIds) {
        super(output, registries, Set.of(Uocraft.MODID));
    }
}
