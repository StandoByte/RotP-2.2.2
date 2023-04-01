package com.github.standobyte.jojo.entity.stand.stands;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;

import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class KraftWorkEntity extends StandEntity {

    public KraftWorkEntity(StandEntityType<? extends StandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void tick() {
        super.tick();
        level.getEntitiesOfClass(ProjectileEntity.class, getUser().getBoundingBox().inflate(8)).forEach(projectile -> {
            if (getUser().distanceToSqr(projectile) < projectile.getDeltaMovement().lengthSqr() * 4) {
                projectile.canUpdate(false);
                projectile.setDeltaMovement(Vector3d.ZERO);
            }
        });
    }
}
