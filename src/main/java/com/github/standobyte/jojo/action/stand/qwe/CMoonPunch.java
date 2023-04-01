package com.github.standobyte.jojo.action.stand.qwe;

import com.github.standobyte.jojo.action.stand.StandEntityLightAttack;
import com.github.standobyte.jojo.action.stand.punch.StandEntityPunch;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.init.ModEffects;
import com.github.standobyte.jojo.util.damage.StandEntityDamageSource;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class CMoonPunch extends StandEntityLightAttack {

    public CMoonPunch(Builder builder) {
        super(builder);
    }

    @Override
    public StandEntityPunch punchEntity(StandEntity stand, Entity target, StandEntityDamageSource dmgSource) {
        return new CMoonPunchInstance(stand, target, dmgSource)
                .copyProperties(super.punchEntity(stand, target, dmgSource))
                .damage(2F);
    }

    
    
    public static class CMoonPunchInstance extends StandEntityPunch {

        public CMoonPunchInstance(StandEntity stand, Entity target, StandEntityDamageSource dmgSource) {
            super(stand, target, dmgSource);
        }

        @Override
        protected void afterAttack(StandEntity stand, Entity target, StandEntityDamageSource dmgSource, StandEntityTask task, boolean hurt, boolean killed) {
            if (!stand.level.isClientSide() && hurt && target instanceof LivingEntity) {
                LivingEntity livingTarget = (LivingEntity) target;
                boolean wasInverted = livingTarget.hasEffect(ModEffects.INVERSION.get());
                inversionEffect(livingTarget, ModEffects.INVERSION.get(), wasInverted, true);
                if (target instanceof PlayerEntity) {
                    inversionEffect(livingTarget, ModEffects.MISSHAPEN_FACE.get(), wasInverted, false);
                    inversionEffect(livingTarget, ModEffects.MISSHAPEN_ARMS.get(), wasInverted, false);
                    inversionEffect(livingTarget, ModEffects.MISSHAPEN_LEGS.get(), wasInverted, false);
                }
            }
        }
        
        private void inversionEffect(LivingEntity entity, Effect effect, boolean hadEffects, boolean showIcon) {
            if (hadEffects) {
                entity.removeEffect(effect);
            }
            else {
                entity.addEffect(new EffectInstance(effect, 3600, 0, false, false, showIcon));
            }
        }
        
    }
}
