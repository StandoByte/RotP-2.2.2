package com.github.standobyte.jojo.client.model.pose.anim.barrage;

import com.github.standobyte.jojo.client.model.pose.anim.IActionAnimation;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.HandSide;

public interface IBarrageAnimation<T extends Entity, M extends EntityModel<T>> extends IActionAnimation<T> {

    public void animateSwing(T entity, M model, float loopCompletion, HandSide side, float yRotationOffset, float xRotation, float zRotationOffset);
}
