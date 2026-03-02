package com.github.uocraftteam.uocraft;

import com.github.uocraftteam.uocraft.entity.IpTeacher;
import com.github.uocraftteam.uocraft.entity.ModEntities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = Uocraft.MODID)
public final class ModEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.IP_TEACHER.get(), IpTeacher.createAttributes().build());
    }
}