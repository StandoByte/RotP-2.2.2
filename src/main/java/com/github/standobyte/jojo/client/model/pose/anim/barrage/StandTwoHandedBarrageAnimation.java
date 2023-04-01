package com.github.standobyte.jojo.client.model.pose.anim.barrage;

import com.github.standobyte.jojo.client.model.entity.stand.StandEntityModel;
import com.github.standobyte.jojo.client.model.pose.IModelPose;
import com.github.standobyte.jojo.entity.stand.StandEntity;

import net.minecraft.util.HandSide;

public class StandTwoHandedBarrageAnimation<T extends StandEntity> extends TwoHandedBarrageAnimation<T, StandEntityModel<T>> {

    public StandTwoHandedBarrageAnimation(StandEntityModel<T> model, IModelPose<T> loop, 
            IModelPose<T> recovery) {
        super(model, loop, recovery, (m, side) -> m.getArm(side));
    }

    @Override
    public BarrageSwingsHolder<T, StandEntityModel<T>> getBarrageSwingsHolder(T entity) {
        return (BarrageSwingsHolder<T, StandEntityModel<T>>) entity.getBarrageSwingsHolder();
    }

    @Override
    protected float swingsToAdd(T entity, float loop, float lastLoop) {
        return standEntitySwings(entity, loop, lastLoop, getLoopLen());
    }

    @Override
    protected double maxSwingOffset(T entity) {
        return maxStandSwingOffset(entity);
    }

    @Override
    protected void addSwing(T entity, BarrageSwingsHolder<T, StandEntityModel<T>> swings, HandSide side, float f,
            double maxOffset) {
        swings.addSwing(new StandArmBarrageSwing<>(this, f, getLoopLen(), side, maxOffset));
    }
}
