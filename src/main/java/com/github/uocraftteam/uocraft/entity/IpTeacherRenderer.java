package com.github.uocraftteam.uocraft.entity;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class IpTeacherRenderer extends HumanoidMobRenderer {
    public IpTeacherRenderer(EntityRendererProvider.Context context, HumanoidModel model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    public IpTeacherRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
    }

    @Override
    public Identifier getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return Identifier.fromNamespaceAndPath("minecraft", "textures/entity/player/wide/alex.png");
    }

    @Override
    public EntityRenderState createRenderState() {
        return new HumanoidRenderState();
    }
}

