package com.github.standobyte.jojo.client.model.pose.anim.barrage;

import com.github.standobyte.jojo.client.model.pose.IModelPose;
import com.github.standobyte.jojo.client.renderer.player.anim.ModdedJointsPlayerModel;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.util.HandSide;

public class PlayerArmsBarrageAnimation extends TwoHandedBarrageAnimation<AbstractClientPlayerEntity, ModdedJointsPlayerModel> {

    public PlayerArmsBarrageAnimation(ModdedJointsPlayerModel model, IModelPose<AbstractClientPlayerEntity> loop, 
            IModelPose<AbstractClientPlayerEntity> recovery, float loopLen) {
        super(model, loop, recovery, (m, side) -> m.getArm(side));
    }

    // FIXME (player anim) move to player client cap
    private static final BarrageSwingsHolder<AbstractClientPlayerEntity, ModdedJointsPlayerModel> SWINGS = 
            new BarrageSwingsHolder<>();
    
    @Override
    public BarrageSwingsHolder<AbstractClientPlayerEntity, ModdedJointsPlayerModel> getBarrageSwingsHolder(
            AbstractClientPlayerEntity entity) {
        return SWINGS;
    }

    @Override
    protected float swingsToAdd(AbstractClientPlayerEntity entity, float loop, float lastLoop) {
        return 3 * Math.min(loop - lastLoop, 1) * getLoopLen();
    }

    @Override
    protected double maxSwingOffset(AbstractClientPlayerEntity entity) {
        return 0.625;
    }

    @Override
    protected void addSwing(AbstractClientPlayerEntity entity, BarrageSwingsHolder<AbstractClientPlayerEntity, ModdedJointsPlayerModel> swings, 
            HandSide side, float f, double maxOffset) {
        swings.addSwing(new PlayerArmBarrageSwing(this, f, getLoopLen(), side, maxOffset));
    }
}
