package com.github.standobyte.jojo.action.non_stand;

import java.util.List;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.capability.entity.LivingUtilCapProvider;
import com.github.standobyte.jojo.init.power.hamon.ModHamon;
import com.github.standobyte.jojo.power.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.nonstand.type.hamon.HamonData;
import com.github.standobyte.jojo.power.nonstand.type.hamon.HamonPowerType;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.BaseHamonSkill.HamonStat;
import com.github.standobyte.jojo.util.damage.DamageUtil;
import com.github.standobyte.jojo.util.utils.JojoModUtil;
import com.github.standobyte.jojo.util.utils.MathUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;

public class HamonTornadoOverdrive extends HamonAction {
    private final boolean isHorizontal;

    public HamonTornadoOverdrive(HamonAction.Builder builder, boolean isHorizontal) {
        super(builder.holdType());
        this.isHorizontal = isHorizontal;
    }
    
    @Override
    public boolean isUnlocked(INonStandPower power) {
        return this == ModHamon.ZEPPELI_TORNADO_OVERDRIVE.get() ? super.isUnlocked(power) : ModHamon.ZEPPELI_TORNADO_OVERDRIVE.get().isUnlocked(power);
    }
     
    @Override
    protected void holdTick(World world, LivingEntity user, INonStandPower power, int ticksHeld, ActionTarget target, boolean requirementsFulfilled) {
        if (requirementsFulfilled) {
            user.fallDistance = 0;
            user.getCapability(LivingUtilCapProvider.CAPABILITY).ifPresent(cap -> cap.setSendoWaveKick(false));
            Vector3d movement = user.getDeltaMovement();
            HamonData hamon = power.getTypeSpecificData(ModHamon.HAMON.get()).get();
            if (!world.isClientSide()) {
                AxisAlignedBB aabb = user.getBoundingBox().expandTowards(movement).inflate(1.0D);
                float damage = 0.1F;
                if (isHorizontal()) {
                    // FIXME ! (hamon 2) horizontal tornado damage
                }
                else {
                    double gravity = user.getAttributeValue(ForgeMod.ENTITY_GRAVITY.get());
                    if (movement.y < -gravity) {
                        damage *= (-movement.y / gravity) * 0.75F;
                    }
                }
                List<Entity> targets = user.level.getEntities(user, aabb);
                boolean points = false;
                for (Entity entity : targets) {
                    if (DamageUtil.dealHamonDamage(entity, damage, user, null)) {
                        points = true;
                    }
                }
                if (points) {
                    hamon.hamonPointsFromAction(HamonStat.STRENGTH, getHeldTickEnergyCost(power));
                }
            }
            if (isHorizontal()) {
                float yBodyRot = user.getCapability(LivingUtilCapProvider.CAPABILITY).map(cap -> {
                    return cap.getYRotOnLastSendoWaveKick();
                }).orElse(user.yRot);
                user.setDeltaMovement(new Vector3d(0.75 + user.getAttributeValue(Attributes.MOVEMENT_SPEED) * 2, 0, 0)
                        .yRot((270 - yBodyRot) * MathUtil.DEG_TO_RAD));
            }
            else if (user.isShiftKeyDown()) {
                user.setDeltaMovement(0, movement.y < 0 ? movement.y * 1.05 : 0, 0);
            }

            world.getEntitiesOfClass(ProjectileEntity.class, user.getBoundingBox().inflate(4), 
                    entity -> entity.isAlive()).forEach(projectile -> {
                        if (user.getBoundingBox().contains(projectile.position().add(projectile.getDeltaMovement()))) {
                            JojoModUtil.deflectProjectile(projectile, null);
                        }
                        else {
                            RayTraceResult rayTrace = ProjectileHelper.getHitResult(projectile, 
                                    entity -> !entity.isSpectator() && entity.isAlive() && !entity.is(projectile.getOwner()));
                            if (rayTrace.getType() == RayTraceResult.Type.ENTITY && ((EntityRayTraceResult) rayTrace).getEntity() == user) {
                                JojoModUtil.deflectProjectile(projectile, null);
                            }
                        }
                    });
            
            HamonPowerType.createHamonSparkParticles(world, user instanceof PlayerEntity ? (PlayerEntity) user : null, user.position(), 
                    hamon.getHamonDamageMultiplier() / HamonData.MAX_HAMON_STRENGTH_MULTIPLIER * 0.25F);
        }
    }
    
    // FIXME ! (hamon 2) horizontal user hitbox
    public boolean isHorizontal() {
        return isHorizontal;
    }
    
    @Override
    public boolean isHeldSentToTracking() {
        return true;
    }
}
