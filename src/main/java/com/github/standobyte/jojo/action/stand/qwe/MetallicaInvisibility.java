package com.github.standobyte.jojo.action.stand.qwe;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class MetallicaInvisibility extends StandAction {

    public MetallicaInvisibility(StandAction.Builder builder) {
        super(builder);
    }
    
    @Override
    protected void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            if (!user.hasEffect(Effects.INVISIBILITY)) {
                user.addEffect(new EffectInstance(Effects.INVISIBILITY, 999999, 0, false, false, true));
            }
            else {
                user.removeEffect(Effects.INVISIBILITY);
            }
        }
    }

}
