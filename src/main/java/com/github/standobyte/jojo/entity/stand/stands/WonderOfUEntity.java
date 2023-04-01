package com.github.standobyte.jojo.entity.stand.stands;

import java.util.List;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class WonderOfUEntity extends StandEntity {
    
    public WonderOfUEntity(StandEntityType<? extends StandEntity> type, World world) {
        super(type, world);
    }
    
    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide()) {
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(32), e -> !e.is(this) && !e.is(getUser()) && !(e instanceof StandEntity));
            for (LivingEntity entity : entities) {
                Vector3d vecToWou = position().subtract(entity.position());
                
                if (vecToWou.lengthSqr() < 36) {
                    calamity(entity);
                    entity.knockback(2.0F, vecToWou.x, vecToWou.z);
                }
                else {
                    vecToWou = vecToWou.normalize();
                    if (entity.getLookAngle().normalize().dot(vecToWou) > 0.8886) {
                        if (random.nextInt(60) == 0) {
                            calamity(entity);
                        }
                    }
                }
            }
        }
    }
    
    private void calamity(LivingEntity target) {
        target.hurt(new EntityDamageSource("random_shit", this), 2F + (random.nextFloat() % 18F));
    }

}
