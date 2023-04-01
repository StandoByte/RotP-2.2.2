package com.github.standobyte.jojo.client.renderer.entity.stand;

import com.github.standobyte.jojo.client.model.entity.stand.StandEntityModel;
import com.github.standobyte.jojo.client.renderer.entity.stand.layer.IronSwordLayer;
import com.github.standobyte.jojo.entity.stand.stands.SilverChariotEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SCAFRenderer<M extends StandEntityModel<SilverChariotEntity>> extends StandEntityRenderer<SilverChariotEntity, M> {
    private final ResourceLocation textureNoArmor;

    public SCAFRenderer(EntityRendererManager rendererManager, M entityModel, ResourceLocation texture, ResourceLocation textureNoArmor,
            float shadowRadius) {
        super(rendererManager, entityModel, texture, shadowRadius);
        this.textureNoArmor = textureNoArmor;
        addLayer(new IronSwordLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(SilverChariotEntity entity) {
        return entity.hasArmor() ? super.getTextureLocation(entity) : textureNoArmor;
    }
}
