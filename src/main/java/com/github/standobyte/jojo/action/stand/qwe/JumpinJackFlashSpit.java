package com.github.standobyte.jojo.action.stand.qwe;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.entity.damaging.projectile.LangSpitEntity;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class JumpinJackFlashSpit extends StandAction {

    public JumpinJackFlashSpit(StandAction.Builder builder) {
        super(builder);
    }
    
    @Override
    protected void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            LangSpitEntity spit = new LangSpitEntity(user, world);
            spit.shootFromRotation(user, 1.5F, 10.0F);
            if (!user.isSilent()) {
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.LLAMA_SPIT, user.getSoundSource(), 
                        1.0F, 1.0F + (user.getRandom().nextFloat() - user.getRandom().nextFloat()) * 0.2F);
            }
            world.addFreshEntity(spit);
        }
    }

}
