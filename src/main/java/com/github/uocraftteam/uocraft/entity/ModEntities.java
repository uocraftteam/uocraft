package com.github.uocraftteam.uocraft.entity;

import com.github.uocraftteam.uocraft.Uocraft;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.swing.text.html.parser.Entity;
import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister.Entities ENTITY_TYPES =
            DeferredRegister.createEntities(Uocraft.MODID);

    public static final Supplier<EntityType<IpTeacher>> IP_TEACHER =
            ENTITY_TYPES.registerEntityType(
                    "ip_teacher", (EntityType.EntityFactory<IpTeacher>) IpTeacher::new, MobCategory.MISC,
                    builder -> builder.sized(0.65f, 1.8f).eyeHeight(1.6f)
            );
}
