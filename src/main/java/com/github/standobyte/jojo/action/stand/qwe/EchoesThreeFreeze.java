package com.github.standobyte.jojo.action.stand.qwe;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.ActionTarget.TargetType;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.init.ModEffects;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.power.stand.IStandPower;
import com.github.standobyte.jojo.util.utils.JojoModUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EchoesThreeFreeze extends StandEntityAction {

    public EchoesThreeFreeze(StandEntityAction.Builder builder) {
        super(builder);
    }
    
    @Override
    public void standTickPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (task.getTarget().getType() == TargetType.ENTITY && task.getTarget().getEntity() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) task.getTarget().getEntity();
            double distance = standEntity.distanceTo(target);
            if (task.getTick() == 0) {
                if (!world.isClientSide() || target instanceof PlayerEntity) {
                    target.setDeltaMovement(0, -10, 0);
                    target.moveTo(target.position().subtract(0, 1, 0));
                }
                
                if (!world.isClientSide()) {
                    standEntity.playSound(ModSounds.echoes_three_freeze.get(), 1.0F, 1.0F);
                    JojoModUtil.sayVoiceLine(userPower.getUser(), ModSounds.koichi_three_freeze.get());
                }
            }
            
            if (distance < 5) {
                if (!world.isClientSide()) {
                    target.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 10, 10 - MathHelper.floor(distance)));
                    target.addEffect(new EffectInstance(Effects.DIG_SLOWDOWN, 10, 15 - MathHelper.floor(distance * 1.5)));
                    target.addEffect(new EffectInstance(ModEffects.STUN.get(), 10, 0));
                }
            }
        }
    }
    
    @Override
    protected boolean standKeepsTarget(ActionTarget target) {
        return target.getType() == TargetType.ENTITY && target.getEntity() instanceof LivingEntity;
    }
    
    @Override
    public TargetRequirement getTargetRequirement() {
        return TargetRequirement.ENTITY;
    }

}
