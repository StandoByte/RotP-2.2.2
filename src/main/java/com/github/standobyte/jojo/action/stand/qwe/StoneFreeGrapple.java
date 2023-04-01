package com.github.standobyte.jojo.action.stand.qwe;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.ActionTarget.TargetType;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.damaging.projectile.ownerbound.HGGrapplingStringEntity;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.init.ModEntityTypes;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.world.World;

public class StoneFreeGrapple extends StandEntityAction {
    
    public StoneFreeGrapple(StandEntityAction.Builder builder) {
        super(builder);
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            HGGrapplingStringEntity string = new HGGrapplingStringEntity(ModEntityTypes.STONE_FREE_GRAPPLING_STRING.get(), 
                    world, standEntity, userPower);
            if (isShiftVariation()) {
                string.setBindEntities(true);
            }
            string.setHeldAction(this);
            world.addFreshEntity(string);
        }
    }
    
    @Override
    public boolean standRetractsAfterTask(IStandPower standPower, StandEntity standEntity) {
        return isShiftVariation();
    }
    
    @Override
    protected boolean standKeepsTarget(ActionTarget target) {
        return this.isShiftVariation() && target.getType() == TargetType.ENTITY;
    }

}
