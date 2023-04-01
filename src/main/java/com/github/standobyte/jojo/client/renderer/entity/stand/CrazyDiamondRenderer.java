package com.github.standobyte.jojo.client.renderer.entity.stand;

import com.github.standobyte.jojo.JojoMod;
import com.github.standobyte.jojo.client.model.entity.stand.CrazyDiamondModel;
import com.github.standobyte.jojo.entity.stand.stands.CrazyDiamondEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class CrazyDiamondRenderer extends StandEntityRenderer<CrazyDiamondEntity, CrazyDiamondModel> {

    public CrazyDiamondRenderer(EntityRendererManager renderManager) {
        super(renderManager, new CrazyDiamondModel(), new ResourceLocation(JojoMod.MOD_ID, "textures/entity/stand/crazy_diamond.png"), 0);
    }
}
