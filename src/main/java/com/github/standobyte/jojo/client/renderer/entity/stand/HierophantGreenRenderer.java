package com.github.standobyte.jojo.client.renderer.entity.stand;

import com.github.standobyte.jojo.JojoMod;
import com.github.standobyte.jojo.client.model.entity.stand.HierophantGreenModel;
import com.github.standobyte.jojo.client.renderer.entity.stand.layer.HierophantGreenGlowLayer;
import com.github.standobyte.jojo.entity.stand.stands.HierophantGreenEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class HierophantGreenRenderer extends StandEntityRenderer<HierophantGreenEntity, HierophantGreenModel> {

    public HierophantGreenRenderer(EntityRendererManager renderManager) {
        super(renderManager, new HierophantGreenModel(), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/hierophant_green.png"), 0);
        addLayer(new HierophantGreenGlowLayer(this));
    }
}
