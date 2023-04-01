package com.github.standobyte.jojo.action.non_stand;

import java.util.List;

import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.capability.entity.LivingUtilCap;
import com.github.standobyte.jojo.capability.entity.LivingUtilCapProvider;
import com.github.standobyte.jojo.init.power.hamon.ModHamon;
import com.github.standobyte.jojo.power.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.BaseHamonSkill.HamonStat;
import com.github.standobyte.jojo.util.damage.DamageUtil;
import com.github.standobyte.jojo.util.utils.MathUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class HamonSendoWaveKick extends HamonAction {

    public HamonSendoWaveKick(HamonAction.Builder builder) {
        super(builder);
    }
    
    @Override
    protected Action<INonStandPower> replaceAction(INonStandPower power) {
        if (power.getUser().getCapability(LivingUtilCapProvider.CAPABILITY).map(LivingUtilCap::isInSendoWaveKick).orElse(false)) {
            return ModHamon.ZEPPELI_TORNADO_OVERDRIVE_HORIZONTAL.get();
        }
        return super.replaceAction(power);
    }
    
    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, INonStandPower power, ActionTarget target) {
        return ActionConditionResult.noMessage(user.isOnGround());
    }

    @Override
    protected void perform(World world, LivingEntity user, INonStandPower power, ActionTarget target) {
        if (!user.level.isClientSide()) {
            user.setOnGround(false);
            user.getCapability(LivingUtilCapProvider.CAPABILITY).ifPresent(cap -> cap.setSendoWaveKick(true));
        }
        else {
            user.setOnGround(false);
            user.hasImpulse = true;
            Vector3d leap = Vector3d.directionFromRotation(MathHelper.clamp(user.xRot, -45F, -18F), user.yRot)
                    .scale(1 + user.getAttributeValue(Attributes.MOVEMENT_SPEED) * 5);
            user.setDeltaMovement(leap.x, leap.y * 0.5, leap.z);
        }
    }
    
    

    private static final int USUAL_SENDO_WAVE_KICK_DURATION = 10;
    public static boolean sendoWaveKickTick(LivingEntity user, SendoWaveKickData data) {
        if (!user.level.isClientSide()) {
            if (data.sendoWaveKickPositionWaitingTimer >= 0) {
                // FIXME ! (hamon 2) check if the client sent position
                boolean clientSentPosition = true;
                if (clientSentPosition) {
                    data.sendoWaveKickPositionWaitingTimer = -1;
                }
                else {
                    data.sendoWaveKickPositionWaitingTimer++;
                }
            }
            if (data.sendoWaveKickPositionWaitingTimer < 0 && user.isOnGround()
                    || data.sendoWaveKickPositionWaitingTimer >= USUAL_SENDO_WAVE_KICK_DURATION) {
                return false;
            }
            
            List<LivingEntity> targets = user.level.getEntitiesOfClass(LivingEntity.class, kickHitbox(user), 
                    entity -> !entity.is(user) && user.canAttack(entity));
            boolean points = false;
            for (LivingEntity target : targets) {
                boolean kickDamage = dealPhysicalDamage(user, target);
                boolean hamonDamage = DamageUtil.dealHamonDamage(target, 0.5F, user, null);
                if (kickDamage || hamonDamage) {
                    Vector3d vecToTarget = target.position().subtract(user.position());
                    boolean left = MathHelper.wrapDegrees(
                            user.yBodyRot - MathUtil.yRotDegFromVec(vecToTarget))
                            < 0;
                    float knockbackYRot = (60F + user.getRandom().nextFloat() * 30F) * (left ? 1 : -1);
                    knockbackYRot += (float) -MathHelper.atan2(vecToTarget.x, vecToTarget.z) * MathUtil.RAD_TO_DEG;
                    DamageUtil.knockback((LivingEntity) target, 0.75F, knockbackYRot);
                    
                    if (hamonDamage) {
                        points = true;
                    }
                }
            }

            if (!data.gaveThisSendoWaveKickPoints && points) {
                INonStandPower.getNonStandPowerOptional(user).ifPresent(power -> {
                    power.getTypeSpecificData(ModHamon.HAMON.get()).ifPresent(hamon -> {
                        hamon.hamonPointsFromAction(HamonStat.STRENGTH, ModHamon.ZEPPELI_SENDO_WAVE_KICK.get().getEnergyCost(power)); 
                    });
                });
            }
        }
        
        // FIXME ! (hamon) sound & particles
        else {
        }
        
        user.fallDistance = 0;
        data.sendoWaveKickTimer++;

        return true;
    }
    
    private static boolean dealPhysicalDamage(LivingEntity user, Entity target) {
        return target.hurt(new EntityDamageSource(user instanceof PlayerEntity ? "player" : "mob", user), 
                DamageUtil.getDamageWithoutHeldItem(user));
    }
    
    public static boolean protectFromMeleeAttackInKick(LivingEntity user, DamageSource dmgSource, float dmgAmount) {
        return user.getCapability(LivingUtilCapProvider.CAPABILITY).map(cap -> {
            return cap.isInSendoWaveKick() && 
                    dmgSource.getEntity() != null && dmgSource.getDirectEntity() != null && dmgSource.getEntity().is(dmgSource.getDirectEntity());
        }).orElse(false);
    }
    
    private static AxisAlignedBB kickHitbox(LivingEntity user) {
        float xzAngle = -user.yRot * MathUtil.DEG_TO_RAD;
        Vector3d lookVec = new Vector3d(Math.sin(xzAngle), 0, Math.cos(xzAngle));
        Vector3d hitboxXZCenter = user.position().add(lookVec.scale(user.getBbWidth() * 0.75F));
        return new AxisAlignedBB(hitboxXZCenter, hitboxXZCenter)
                .inflate(user.getBbWidth() * 0.6F, 0, user.getBbWidth() * 0.6F)
                .expandTowards(0, user.getBbHeight() / 2, 0);
    }
    
    public static class SendoWaveKickData {
        private int sendoWaveKickTimer = 0;
        private int sendoWaveKickPositionWaitingTimer = 0;
        private boolean gaveThisSendoWaveKickPoints = false;
        public final float userYRot;
        
        public SendoWaveKickData(LivingEntity user) {
            this.userYRot = user.yRot;
        }
    }
}

