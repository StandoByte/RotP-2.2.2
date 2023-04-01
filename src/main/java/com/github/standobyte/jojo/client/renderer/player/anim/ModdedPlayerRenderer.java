package com.github.standobyte.jojo.client.renderer.player.anim;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.github.standobyte.jojo.JojoMod;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = JojoMod.MOD_ID, value = Dist.CLIENT)
@Deprecated
public abstract class ModdedPlayerRenderer<M extends PlayerModel<AbstractClientPlayerEntity>> extends PlayerRenderer {
    private M moddedModel;

    public ModdedPlayerRenderer(EntityRendererManager renderManager, boolean slim) {
        super(renderManager);
        this.moddedModel = createModel(slim);
        this.model = moddedModel;
    }
    
    protected M getModdedModel() {
        return moddedModel;
    }
    
    protected abstract M createModel(boolean slim);
    protected abstract boolean hasCustomAnim(AbstractClientPlayerEntity entity);
    
    public boolean onPlayerRenderEvent(AbstractClientPlayerEntity player, 
            float partialTick, MatrixStack matrixStack, IRenderTypeBuffer buffers, int light) {
        // FIXME (player anim) ! disabled for now
        if (false && hasCustomAnim(player)) {
            render(player, MathHelper.lerp(partialTick, player.yRotO, player.yRot), partialTick, matrixStack, buffers, light);
            return true;
        }
        return false;
    }
    
    protected abstract void acceptVanillaRenderer(PlayerRenderer renderer);
    


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
        AbstractClientPlayerEntity player = (AbstractClientPlayerEntity) event.getPlayer();
        for (RenderersPair renderers : playerRenderers) {
            ModdedPlayerRenderer<?> renderer = renderers.getRenderer(player.getModelName());
            if (event.getRenderer() != null && event.getRenderer() != renderer && 
                    renderers.getRenderer(player.getModelName()).onPlayerRenderEvent(player, event.getPartialRenderTick(), 
                            event.getMatrixStack(), event.getBuffers(), event.getLight())) {
                event.setCanceled(true);
                break;
            }
        }
    }

    private static final List<RenderersPair> playerRenderers = new ArrayList<>();
    public static void addModdedPlayerRenderer(Function<Boolean, ModdedPlayerRenderer<?>> renderersNormalSlim) {
        playerRenderers.add(new RenderersPair(renderersNormalSlim.apply(false), renderersNormalSlim.apply(true)));
    }

    public static void addModdedLayers(PlayerRenderer normal, PlayerRenderer slim) {
        playerRenderers.forEach(pair -> {
            pair.defaultModel.acceptVanillaRenderer(normal);
            pair.slimModel.acceptVanillaRenderer(slim);
        });
    }
    
    private static class RenderersPair {
        private final ModdedPlayerRenderer<?> defaultModel;
        private final ModdedPlayerRenderer<?> slimModel;
        
        private RenderersPair(ModdedPlayerRenderer<?> defaultModel, ModdedPlayerRenderer<?> slimModel) {
            this.defaultModel = defaultModel;
            this.slimModel = slimModel;
        }
        
        private ModdedPlayerRenderer<?> getRenderer(String key) {
            return "slim".equals(key) ? slimModel : defaultModel;
        }
    }
}
