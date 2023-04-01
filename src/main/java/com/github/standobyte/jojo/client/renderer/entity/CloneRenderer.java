package com.github.standobyte.jojo.client.renderer.entity;

import com.github.standobyte.jojo.entity.CloneEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class CloneRenderer<T extends CloneEntity> extends EntityRenderer<T> {

    public CloneRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation getTextureLocation(T p_110775_1_) {
        return null;
    }

    @Override
    public void render(T entity, float yRotation, float partialTick, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
        Entity originEntity = entity.getOriginalEntity();
        if (originEntity != null) {
            entityRenderDispatcher.getRenderer(originEntity).render(originEntity, yRotation, partialTick, matrixStack, buffer, packedLight);
        }
        super.render(entity, yRotation, partialTick, matrixStack, buffer, packedLight);
    }

}
