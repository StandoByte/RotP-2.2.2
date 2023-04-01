package com.github.standobyte.jojo.client.renderer.entity.stand;

import com.github.standobyte.jojo.client.model.entity.stand.StandEntityModel;
import com.github.standobyte.jojo.client.renderer.entity.stand.layer.MadeInHeavenHorsePartLayer;
import com.github.standobyte.jojo.entity.stand.StandEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class MadeInHeavenRenderer<T extends StandEntity, M extends StandEntityModel<T>> extends StandEntityRenderer<T, M> {

    public MadeInHeavenRenderer(EntityRendererManager rendererManager, M entityModel, ResourceLocation texture,
            float shadowRadius) {
        super(rendererManager, entityModel, texture, shadowRadius);
        addLayer(new MadeInHeavenHorsePartLayer<>(this));
    }

}
