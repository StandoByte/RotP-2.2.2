package com.github.standobyte.jojo.entity.damaging.projectile;

import com.github.standobyte.jojo.init.ModEffects;
import com.github.standobyte.jojo.init.ModEntityTypes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class TuskAct4BulletEntity extends ModdedProjectileEntity {
    
    public TuskAct4BulletEntity(LivingEntity shooter, World world) {
        super(ModEntityTypes.TUSK_BULLET.get(), shooter, world);
    }

    public TuskAct4BulletEntity(EntityType<? extends TuskAct4BulletEntity> type, World world) {
        super(type, world);
    }

    @Override
    public boolean standDamage() {
        return true;
    }
    
    @Override
    public float getBaseDamage() {
        return 10.0F;
    }

    @Override
    public int ticksLifespan() {
        return 500;
    }

    @Override
    protected float getMaxHardnessBreakable() {
        return 10.0F;
    }
    
    @Override
    protected void afterEntityHit(EntityRayTraceResult entityRayTraceResult, boolean entityHurt) {
        if (entityHurt) {
            Entity target = entityRayTraceResult.getEntity();
            if (target instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity) target;
                entity.addEffect(new EffectInstance(ModEffects.INFINITE_SPIN.get(), 999999, 0, false, false, true));
            }
        }
    }

}
