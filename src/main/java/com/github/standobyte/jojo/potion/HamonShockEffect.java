package com.github.standobyte.jojo.potion;

import java.util.Random;

import com.github.standobyte.jojo.power.nonstand.type.hamon.HamonPowerType;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;

public class HamonShockEffect extends StunEffect {
    private final Random random = new Random();

    public HamonShockEffect(EffectType type, int liquidColor) {
        super(type, liquidColor);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        super.applyEffectTick(entity, amplifier);
        entity.yHeadRot += (random.nextFloat() - 0.5F) * (amplifier + 1) * 4F;
        entity.yRot += (random.nextFloat() - 0.5F) * (amplifier + 1) * 4F;
        entity.xRot += (random.nextFloat() - 0.5F) * (amplifier + 1) * 2F;
        if (entity.level.isClientSide() && entity.tickCount % (16 >> Math.min(amplifier, 3)) == 0) {
            HamonPowerType.createHamonSparkParticlesEmitter(entity, 0.2F);
        }
    }

    @Override
    protected boolean resetsDeltaMovement() {
        return false;
    }
}
