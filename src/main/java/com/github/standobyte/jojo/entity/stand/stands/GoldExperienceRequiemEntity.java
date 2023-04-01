package com.github.standobyte.jojo.entity.stand.stands;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class GoldExperienceRequiemEntity extends StandEntity {

    public GoldExperienceRequiemEntity(StandEntityType<? extends StandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity user = getUser();
        if (user instanceof PlayerEntity) {
            ((PlayerEntity) user).abilities.mayfly = true;
        }
    }
}
