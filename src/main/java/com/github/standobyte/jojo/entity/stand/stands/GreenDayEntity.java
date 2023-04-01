package com.github.standobyte.jojo.entity.stand.stands;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.init.ModEffects;
import com.github.standobyte.jojo.init.power.stand.ModStandActions;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class GreenDayEntity extends StandEntity {

    public GreenDayEntity(StandEntityType<? extends StandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide()) {
            level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(16), e -> 
            !IStandPower.getStandPowerOptional(e).map(power -> power.getType() == ModStandActions.STAND_GREEN_DAY.getStandType()).orElse(false)
            && !(e instanceof StandEntity))
            .forEach(nearbyEntity -> nearbyEntity.addEffect(new EffectInstance(ModEffects.MOLD.get(), 999999, 0)));
        }
    }
}
