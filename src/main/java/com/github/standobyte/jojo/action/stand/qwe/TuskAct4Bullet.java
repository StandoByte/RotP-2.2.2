package com.github.standobyte.jojo.action.stand.qwe;

import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.damaging.projectile.TuskAct4BulletEntity;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.world.World;

public class TuskAct4Bullet extends StandEntityAction {

    public TuskAct4Bullet(StandEntityAction.Builder builder) {
        super(builder);
    }
    
    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            TuskAct4BulletEntity bullet = new TuskAct4BulletEntity(userPower.getUser(), world);
            bullet.shootFromRotation(userPower.getUser(), 4.0F, 0.0F);
            world.addFreshEntity(bullet);
        }
    }

}
