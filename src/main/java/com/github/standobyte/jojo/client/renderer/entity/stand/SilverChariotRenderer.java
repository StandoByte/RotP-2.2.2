package com.github.standobyte.jojo.client.renderer.entity.stand;

import com.github.standobyte.jojo.JojoMod;
import com.github.standobyte.jojo.client.model.entity.stand.SilverChariotModel;
import com.github.standobyte.jojo.client.renderer.entity.stand.layer.SilverChariotArmorLayer;
import com.github.standobyte.jojo.client.renderer.entity.stand.layer.SilverChariotRapierFlameLayer;
import com.github.standobyte.jojo.entity.stand.stands.SilverChariotEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SilverChariotRenderer extends StandEntityRenderer<SilverChariotEntity, SilverChariotModel> {
    
    public SilverChariotRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SilverChariotModel(), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/silver_chariot.png"), 0);
        addLayer(new SilverChariotArmorLayer(this));
        addLayer(new SilverChariotRapierFlameLayer(this));
    }
}
