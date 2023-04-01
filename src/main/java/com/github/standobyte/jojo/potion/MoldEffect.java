package com.github.standobyte.jojo.potion;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.init.power.stand.ModStandActions;
import com.github.standobyte.jojo.power.stand.IStandPower;
import com.github.standobyte.jojo.util.reflection.CommonReflection;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class MoldEffect extends Effect {
    
    public MoldEffect() {
        super(EffectType.HARMFUL, 0x1D5648);
    }
    
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level.isClientSide()) {
            if (entity.getDeltaMovement().y < 0 && !entity.isOnGround() && !CommonReflection.isJumping(entity)) {
                entity.hurt(new DamageSource("mold").bypassArmor(), 4);
                entity.level.getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(8), e -> 
                !IStandPower.getStandPowerOptional(e).map(power -> power.getType() == ModStandActions.STAND_GREEN_DAY.getStandType()).orElse(false)
                && !(e instanceof StandEntity))
                .forEach(nearbyEntity -> nearbyEntity.addEffect(new EffectInstance(this, 999999, 0)));
            }
        }
    }
    
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) { return true; }
}
