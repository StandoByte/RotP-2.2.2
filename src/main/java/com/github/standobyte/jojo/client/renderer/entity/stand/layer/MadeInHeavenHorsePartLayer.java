package com.github.standobyte.jojo.client.renderer.entity.stand.layer;

import com.github.standobyte.jojo.JojoMod;
import com.github.standobyte.jojo.client.model.entity.stand.MadeInHeavenHorsePartModel;
import com.github.standobyte.jojo.client.model.entity.stand.StandEntityModel;
import com.github.standobyte.jojo.entity.stand.StandEntity;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.util.ResourceLocation;

public class MadeInHeavenHorsePartLayer<T extends StandEntity, M extends StandEntityModel<T>> extends StandModelLayerRenderer<T, M> {
    private static final ResourceLocation WHITE_HORSE = new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/af/made_in_heaven_horse_white.png");

    public MadeInHeavenHorsePartLayer(IEntityRenderer<T, M> entityRenderer) {
        super(entityRenderer, (M) new MadeInHeavenHorsePartModel<T>());
    }

    @Override
    protected ResourceLocation getLayerTexture() {
        return WHITE_HORSE;
    }

}
