package com.github.standobyte.jojo.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class CMoonInversionEffect extends Effect {
    
    public CMoonInversionEffect() {
        super(EffectType.HARMFUL, 0x8EDD68);
    }
    
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level.isClientSide()) {
            entity.hurt(new DamageSource("c_moon").bypassArmor(), 2.0F);
        }
    }
    
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) { return true; }
}
