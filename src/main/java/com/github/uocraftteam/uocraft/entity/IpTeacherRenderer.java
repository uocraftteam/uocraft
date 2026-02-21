package com.github.uocraftteam.uocraft.entity;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class IpTeacherRenderer extends HumanoidMobRenderer {
    public IpTeacherRenderer(EntityRendererProvider.Context context, HumanoidModel model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    public IpTeacherRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
    }

    @Override
    protected boolean shouldShowName(Entity entity, double distanceToCameraSq) {
        return distanceToCameraSq <= 100.0;
    }

    @Override
    public Identifier getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return Identifier.fromNamespaceAndPath("uocraft", "textures/entity/ip_teacher_1.png");
    }

    @Override
    public EntityRenderState createRenderState() {
        return new HumanoidRenderState();
    }
}

