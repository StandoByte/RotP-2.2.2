package com.github.standobyte.jojo.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class InfiniteSpinEffect extends UncurableEffect {
    
    public InfiniteSpinEffect() {
        super(EffectType.HARMFUL, 0xFF5488);
        addAttributeModifier(Attributes.MOVEMENT_SPEED, "e30ee41c-6ea2-468c-99ab-fd0a7d6be8c3", -0.75, AttributeModifier.Operation.MULTIPLY_TOTAL).
        addAttributeModifier(Attributes.ATTACK_SPEED, "e4d278d8-a38b-434f-9c65-20c944abcff9", -0.75, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
    
    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        livingEntity.yRot += 10F;
        livingEntity.xRot += 5F;
        if (!livingEntity.level.isClientSide()) {
            livingEntity.hurt(new DamageSource("spin").bypassArmor().bypassInvul().bypassMagic(), 1.0F);
        }
    }
    
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) { return true; }
}
