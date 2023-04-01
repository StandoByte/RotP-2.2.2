package com.github.standobyte.jojo.client.model.pose.anim.barrage;

import com.github.standobyte.jojo.client.renderer.player.anim.ModdedJointsPlayerModel;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.util.HandSide;

public class PlayerArmBarrageSwing extends ArmBarrageSwing<AbstractClientPlayerEntity, ModdedJointsPlayerModel> {

    public PlayerArmBarrageSwing(IBarrageAnimation<AbstractClientPlayerEntity, ModdedJointsPlayerModel> barrageAnim, 
            float ticks, float ticksMax, HandSide side, double maxOffset) {
        super(barrageAnim, ticks, ticksMax, side, maxOffset);
    }

    @Override
    protected void setArmOnlyModelVisibility(AbstractClientPlayerEntity entity, ModdedJointsPlayerModel model, HandSide side) {
        model.setArmOnlyVisibility(side);
    }

}
