package com.github.standobyte.jojo.entity.stand.stands;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class ChariotRequiemEntity extends StandEntity {

    public ChariotRequiemEntity(StandEntityType<? extends StandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide()) {
            level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(32), entity -> !entity.is(this) && !entity.is(getUser()))
            .forEach(entity -> {
                if (!entity.isSleeping()) {
                    entity.startSleeping(entity.blockPosition());
                }
            });
        }
    }
}
