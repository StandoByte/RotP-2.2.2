package com.github.standobyte.jojo.action.stand.qwe;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.entity.itemprojectile.KnifeEntity;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class MetallicaKnives extends StandAction {

    public MetallicaKnives(StandAction.Builder builder) {
        super(builder);
    }
    
    @Override
    protected void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            for (int i = 0; i < 8; i++) {
                KnifeEntity knifeEntity = new KnifeEntity(world, user);
                knifeEntity.setTimeStopFlightTicks(5);
                knifeEntity.shootFromRotation(user, 1.5F, i == 0 ? 1.0F : 16.0F);
                world.addFreshEntity(knifeEntity);
            }
        }
    }

}
