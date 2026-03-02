package com.github.uocraftteam.uocraft.item;

import com.github.uocraftteam.uocraft.Constants;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.ArrayList;
import java.util.List;

public final class ModConsumables {
    public static final Consumable COFFEE_CONSUMABLE = Consumables.defaultDrink().onConsume(
            new ApplyStatusEffectsConsumeEffect(new ArrayList<>(List.of(
                    new MobEffectInstance(MobEffects.SPEED,
                            Constants.COFFEE_SPEED_EFFECT_DURATION_IN_SECONDS * Constants.TICKS_PER_SECOND,
                            Constants.COFFEE_SPEED_EFFECT_INTENSITY
                    ),
                    new MobEffectInstance(MobEffects.NIGHT_VISION, Constants.COFFEE_NIGHT_VISION_EFFECT_DURATION_IN_SECONDS * Constants.TICKS_PER_SECOND)
                    )
            ))
    ).build();
}
