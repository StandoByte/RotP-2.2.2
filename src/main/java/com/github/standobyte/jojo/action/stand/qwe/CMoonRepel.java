package com.github.standobyte.jojo.action.stand.qwe;

import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class CMoonRepel extends StandEntityAction {

    public CMoonRepel(StandEntityAction.Builder builder) {
        super(builder);
    }
    
    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        LivingEntity user = userPower.getUser();
        Vector3d userPos = user.getBoundingBox().getCenter();
        world.getEntities(user, user.getBoundingBox().inflate(64), entity -> !(entity instanceof StandEntity)).forEach(target -> {
            if (!world.isClientSide() || target.isControlledByLocalInstance()) {
                Vector3d vec = target.getBoundingBox().getCenter().subtract(userPos).normalize().scale(20);
                target.setDeltaMovement(vec);
            }
        });
    }

}
