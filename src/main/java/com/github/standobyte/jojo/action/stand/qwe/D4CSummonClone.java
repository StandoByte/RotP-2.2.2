package com.github.standobyte.jojo.action.stand.qwe;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.ActionTarget.TargetType;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.CloneEntity;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class D4CSummonClone extends StandEntityAction {

    public D4CSummonClone(StandEntityAction.Builder builder) {
        super(builder);
    }
    
    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            ActionTarget t = task.getTarget();
            if (t.getType() == TargetType.ENTITY && t.getEntity() instanceof LivingEntity) {
                LivingEntity target = (LivingEntity) t.getEntity();
                CloneEntity clone = new CloneEntity(target, world);
                clone.copyPosition(standEntity);
                world.addFreshEntity(clone);
            }
        }
    }
    
    @Override
    protected boolean standKeepsTarget(ActionTarget target) {
        return target.getType() == TargetType.ENTITY && target.getEntity() instanceof LivingEntity && !(target.getEntity() instanceof StandEntity);
    }
    
    @Override
    public TargetRequirement getTargetRequirement() {
        return TargetRequirement.ENTITY;
    }

}
