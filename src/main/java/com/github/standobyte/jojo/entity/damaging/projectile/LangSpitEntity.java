package com.github.standobyte.jojo.entity.damaging.projectile;

import com.github.standobyte.jojo.init.ModEntityTypes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class LangSpitEntity extends ModdedProjectileEntity {
    
    public LangSpitEntity(LivingEntity shooter, World world) {
        super(ModEntityTypes.LANG_SPIT.get(), shooter, world);
    }

    public LangSpitEntity(EntityType<? extends LangSpitEntity> type, World world) {
        super(type, world);
    }

    @Override
    public boolean standDamage() {
        return false;
    }
    
    @Override
    public float getBaseDamage() {
        return 1.0F;
    }

    @Override
    public int ticksLifespan() {
        return 500;
    }

    @Override
    protected float getMaxHardnessBreakable() {
        return 0;
    }
    
    @Override
    protected void afterEntityHit(EntityRayTraceResult entityRayTraceResult, boolean entityHurt) {
        if (entityHurt) {
            Entity target = entityRayTraceResult.getEntity();
            if (target instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity) target;
                EffectInstance eff = entity.getEffect(Effects.LEVITATION);
                entity.addEffect(new EffectInstance(Effects.LEVITATION, 200, eff != null ? eff.getAmplifier() + 1 : 0));
            }
        }
    }

}
