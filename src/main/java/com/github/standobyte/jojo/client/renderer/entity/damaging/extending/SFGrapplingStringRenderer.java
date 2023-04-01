package com.github.standobyte.jojo.client.renderer.entity.damaging.extending;

import com.github.standobyte.jojo.JojoMod;
import com.github.standobyte.jojo.client.model.entity.ownerbound.repeating.HGStringModel;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.HGGrapplingStringEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SFGrapplingStringRenderer extends HGStringAbstractRenderer<HGGrapplingStringEntity> {
    private static final ResourceLocation STRING = new ResourceLocation(JojoMod.MOD_ID, "textures/entity/projectiles/sf_string.png");

    public SFGrapplingStringRenderer(EntityRendererManager renderManager) {
        super(renderManager, new HGStringModel<HGGrapplingStringEntity>());
    }
    
    @Override
    public ResourceLocation getTextureLocation(HGGrapplingStringEntity entity) {
        return STRING;
    }
}
