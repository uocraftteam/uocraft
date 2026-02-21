package com.github.uocraftteam.uocraft;

import com.github.uocraftteam.uocraft.entity.IpTeacherRenderer;
import com.github.uocraftteam.uocraft.entity.ModEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Uocraft.MODID, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // This tells the game to use the Villager model/renderer for your teacher
        event.registerEntityRenderer(ModEntities.IP_TEACHER.get(), (context) -> new IpTeacherRenderer(context));
    }
}