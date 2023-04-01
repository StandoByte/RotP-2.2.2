package com.github.standobyte.jojo.action.non_stand;

import java.util.function.Predicate;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.capability.entity.LivingUtilCapProvider;
import com.github.standobyte.jojo.init.ModEffects;
import com.github.standobyte.jojo.init.power.hamon.ModHamon;
import com.github.standobyte.jojo.power.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.nonstand.type.hamon.HamonData;
import com.github.standobyte.jojo.power.nonstand.type.hamon.HamonPowerType;
import com.github.standobyte.jojo.util.damage.DamageUtil;
import com.github.standobyte.jojo.util.utils.MathUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class HamonRepellingOverdrive extends HamonAction {

    public HamonRepellingOverdrive(HamonAction.Builder builder) {
        super(builder);
    }
    
    @Override
    protected void perform(World world, LivingEntity user, INonStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            HamonData hamon = power.getTypeSpecificData(ModHamon.HAMON.get()).get();
            float effectStr = (float) hamon.getHamonControlLevel() / (float) HamonData.MAX_STAT_LEVEL * hamon.getHamonEfficiency(getEnergyCost(power));

            Predicate<LivingEntity> filter = EntityPredicates.LIVING_ENTITY_STILL_ALIVE.and(EntityPredicates.NO_CREATIVE_OR_SPECTATOR);
            if (user instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) user;
                filter = filter.and(entity -> player.canAttack(entity));
            }
            world.getEntitiesOfClass(LivingEntity.class, user.getBoundingBox().inflate(2, 0, 2), filter).forEach(targetEntity -> {
                Vector3d lookVec = user.getLookAngle();
                Vector3d targetVec = targetEntity.position().subtract(user.position()).normalize();
                double cos = lookVec.dot(targetVec);
                if (cos > 0) {
                    float knockback = 0.75F + 1.25F * effectStr;
                    if (targetEntity.getCapability(LivingUtilCapProvider.CAPABILITY).map(cap -> {
                        if (!cap.isImmuneToHamonShock()) {
                            targetEntity.addEffect(new EffectInstance(ModEffects.HAMON_SHOCK.get(), 
                                    20 + (int) (effectStr * 40F), 
                                    0, false, false, true));
                            cap.setHamonShockImmunity(100);
                            if (targetEntity instanceof MobEntity) {
                                ((MobEntity) targetEntity).setTarget(null);
                            }
                            return false;
                        }
                        return true;
                    }).orElse(false)) {
                        knockback *= 0.5F;
                    }
                    DamageUtil.knockback(targetEntity, knockback, MathUtil.yRotDegFromVec(targetVec));
                    DamageUtil.dealHamonDamage(targetEntity, 0.05F, user, null);
                }
            });
            HamonPowerType.createHamonSparkParticlesEmitter(user, effectStr / 2F);
        }
    }

}
