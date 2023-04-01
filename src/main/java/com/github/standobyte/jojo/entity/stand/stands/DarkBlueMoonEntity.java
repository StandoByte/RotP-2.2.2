package com.github.standobyte.jojo.entity.stand.stands;

import java.util.UUID;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;

public class DarkBlueMoonEntity extends StandEntity {

    private static final AttributeModifier SWIM_SPEED = new AttributeModifier(
            UUID.fromString("c6330313-c9a0-4d78-9923-c204a729e0f2"), "", 1.5D, AttributeModifier.Operation.ADDITION);
    
    public DarkBlueMoonEntity(StandEntityType<? extends StandEntity> type, World world) {
        super(type, world);
    }
    
    @Override
    public void tick() {
        super.tick();
        LivingEntity user = getUser();
        if (!level.isClientSide() && user != null) {
            user.addEffect(new EffectInstance(Effects.WATER_BREATHING, 10, 0, false, false, true));
            
            ModifiableAttributeInstance attributeInstance = user.getAttribute(ForgeMod.SWIM_SPEED.get());
            if (attributeInstance != null && !attributeInstance.hasModifier(SWIM_SPEED)) {
                attributeInstance.addTransientModifier(SWIM_SPEED);
            }
        }
    }
    
    @Override
    public void remove() {
        super.remove();
        LivingEntity user = getUser();
        if (!level.isClientSide() && user.isAlive() && user != null) {
            ModifiableAttributeInstance attributeInstance = user.getAttribute(ForgeMod.SWIM_SPEED.get());
            if (attributeInstance != null && attributeInstance.hasModifier(SWIM_SPEED)) {
                attributeInstance.removeModifier(SWIM_SPEED);
            }
        }
    }

}
