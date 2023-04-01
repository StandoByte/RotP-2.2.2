package com.github.standobyte.jojo.client.renderer.player.anim;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PlayerRenderer;

@Deprecated
public class ModdedJointsPlayerRenderer extends ModdedPlayerRenderer<ModdedJointsPlayerModel> {

    public ModdedJointsPlayerRenderer(EntityRendererManager renderManager, boolean slim) {
        super(renderManager, slim);
    }

    @Override
    protected ModdedJointsPlayerModel createModel(boolean slim) {
        return new ModdedJointsPlayerModel(0, slim);
    }
    
    @Override
    public boolean hasCustomAnim(AbstractClientPlayerEntity entity) {
        return getModdedModel().hasCustomAnim(entity);
    }

    // FIXME (player anim) add layers added by other mods
    // FIXME (player anim) convert layer models to joints model
    @Override
    protected void acceptVanillaRenderer(PlayerRenderer renderer) {
        
    }
    
    @Override
    public void render(AbstractClientPlayerEntity entity, float yRotation, float partialTick, 
            MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
        getModdedModel().layer = false;
        super.render(entity, yRotation, partialTick, matrixStack, buffer, packedLight);
    }
}
