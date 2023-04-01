package com.github.standobyte.jojo.client.model.pose.anim.barrage;

import java.util.function.BiFunction;

import com.github.standobyte.jojo.action.stand.StandEntityAction.Phase;
import com.github.standobyte.jojo.client.model.entity.stand.HumanoidStandModel;
import com.github.standobyte.jojo.client.model.pose.IModelPose;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;

public abstract class TwoHandedBarrageAnimation<T extends LivingEntity, M extends EntityModel<T>> extends ArmsBarrageAnimation<T, M> {
    private final BiFunction<M, HandSide, ModelRenderer> getArm;

    public TwoHandedBarrageAnimation(M model, IModelPose<T> loop, IModelPose<T> recovery, 
            BiFunction<M, HandSide, ModelRenderer> getArm) {
        super(model, loop, recovery, 4);
        this.getArm = getArm;
    }
    
    @Override
    public void animateSwing(T entity, M model, float loopCompletion, HandSide side, float yRotationOffset, float xRotation, float zRotationOffset) {
        super.animateSwing(entity, model, loopCompletion, side, yRotationOffset, xRotation, zRotationOffset);
        ModelRenderer arm = getArm.apply(model, side);
        arm.zRot = arm.zRot + HumanoidStandModel.barrageHitEasing(loopCompletion) * zRotationOffset;
    }

    @Override
    protected HandSide getHandSide(Phase phase, T entity, float ticks) {
        return HandSide.RIGHT;
    }
    
    @Override
    protected boolean switchesArms() {
        return true;
    }
}
