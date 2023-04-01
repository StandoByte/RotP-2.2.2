package com.github.standobyte.jojo.action.stand.qwe;

import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.damaging.projectile.SoftAndWetBubbleEntity;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.entity.stand.StandStatFormulas;
import com.github.standobyte.jojo.power.stand.IStandPower;
import com.github.standobyte.jojo.util.utils.JojoModUtil;

import net.minecraft.world.World;

public class SoftAndWetBubbles extends StandEntityAction {

    public SoftAndWetBubbles(StandEntityAction.Builder builder) {
        super(builder);
    }
    
    @Override
    public void standTickPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            double fireRate = 2.5 * StandStatFormulas.projectileFireRateScaling(standEntity, userPower);
            JojoModUtil.doFractionTimes(() -> {
                SoftAndWetBubbleEntity bubble = new SoftAndWetBubbleEntity(standEntity, world);
                standEntity.shootProjectile(bubble, 0.1F + standEntity.getRandom().nextFloat() * 0.5F, 16.0F);
            }, fireRate);
        }
    }

}
