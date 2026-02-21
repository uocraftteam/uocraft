package com.github.uocraftteam.uocraft.item;

import com.github.uocraftteam.uocraft.Constants;
import com.github.uocraftteam.uocraft.Uocraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ClearAllStatusEffectsConsumeEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class ModItems {
        public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Uocraft.MODID);

        public static final DeferredItem<@NotNull Item> MUSIC_DISK_DEMASIADO_JAVA = ITEMS.registerSimpleItem(
                "music_disk_demasiado_java",
                properties -> properties
                        .stacksTo(1)
                        .jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG,
                        Identifier.fromNamespaceAndPath(Uocraft.MODID, "demasiado_java")))
        );
    public static final DeferredItem<@NotNull Item> KEYBOARD = ITEMS.registerSimpleItem(
            "keyboard",
            properties -> properties);

    public static final DeferredItem<@NotNull Item> MOUSE = ITEMS.registerSimpleItem(
            "mouse",
            properties -> properties);

    public static final DeferredItem<@NotNull Item> MONITOR = ITEMS.registerSimpleItem(
            "monitor",
            properties -> properties);

    public static final Consumable COFFEE_CONSUMABLE = Consumables.defaultDrink().onConsume(
            new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED,
                    Constants.COFFEE_SPEED_EFFECT_DURATION_IN_SECONDS * Constants.TICKS_PER_SECOND,
                    Constants.COFFEE_SPEED_EFFECT_INTENSITY
                    ))
    ).build();
    public static final DeferredItem<@NotNull Item> COFFEE = ITEMS.registerSimpleItem(
            "coffee",
            properties -> properties
                    .stacksTo(16).component(DataComponents.CONSUMABLE, COFFEE_CONSUMABLE)
    );


    public static void registerItems(IEventBus eventBus) {
            ITEMS.register(eventBus);
        }
}