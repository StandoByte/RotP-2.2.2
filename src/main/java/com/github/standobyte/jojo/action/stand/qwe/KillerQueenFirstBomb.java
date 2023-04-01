package com.github.standobyte.jojo.action.stand.qwe;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.ActionTarget.TargetType;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.KQBombEntity;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class KillerQueenFirstBomb extends StandEntityAction {

    public KillerQueenFirstBomb(StandEntityAction.Builder builder) {
        super(builder);
    }
    
    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            KQBombEntity bomb = new KQBombEntity(userPower.getUser(), world);
            ActionTarget target = task.getTarget();
            if (target.getType() == TargetType.BLOCK) {
                bomb.moveTo(Vector3d.atBottomCenterOf(target.getBlockPos()));
                world.addFreshEntity(bomb);
            }
            else if (target.getType() == TargetType.ENTITY) {
                bomb.setHost(target.getEntity());
                bomb.moveTo(target.getEntity().getBoundingBox().getCenter().subtract(0, 0.5, 0));
                world.addFreshEntity(bomb);
            }
        }
    }
    
    @Override
    protected boolean standKeepsTarget(ActionTarget target) {
        return true;
    }
    
    @Override
    public TargetRequirement getTargetRequirement() {
        return TargetRequirement.ANY;
    }

}
