package com.github.standobyte.jojo.client.renderer.player.anim;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PlayerRenderer;

@Deprecated
public class ModdedRotationsPlayerRenderer extends ModdedPlayerRenderer<ModdedRotationsPlayerModel> {

    public ModdedRotationsPlayerRenderer(EntityRendererManager renderManager, boolean slim) {
        super(renderManager, slim);
    }

    @Override
    protected ModdedRotationsPlayerModel createModel(boolean slim) {
        return new ModdedRotationsPlayerModel(0, slim);
    }

    @Override
    public boolean hasCustomAnim(AbstractClientPlayerEntity entity) {
        return getModdedModel().hasCustomAnim(entity);
    }

    // FIXME (player anim) add layers added by other mods
    @Override
    protected void acceptVanillaRenderer(PlayerRenderer renderer) {
        
    }

}
