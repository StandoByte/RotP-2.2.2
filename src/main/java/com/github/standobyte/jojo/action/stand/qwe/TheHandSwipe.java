package com.github.standobyte.jojo.action.stand.qwe;

import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.action.stand.StandEntityAction.Builder;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.stand.IStandPower;
import com.github.standobyte.jojo.util.utils.JojoModUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class TheHandSwipe extends StandEntityAction {
    private static final double TP_RANGE = 4;
    private static final double PULL_TRACKING_RANGE = 16;

    public TheHandSwipe(StandEntityAction.Builder builder) {
        super(builder);
    }
    
    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        LivingEntity user = userPower.getUser();
        LivingEntity entity = standEntity.isManuallyControlled() ? standEntity : user;
        if (user.isShiftKeyDown()) {
            if (entity.isControlledByLocalInstance()) {
                RayTraceResult rayTrace = JojoModUtil.rayTrace(entity.getEyePosition(1.0F), entity.getLookAngle(), PULL_TRACKING_RANGE, 
                        world, entity, e -> !(e.is(standEntity) || e.is(user)), 0, 0);
                if (rayTrace.getType() == RayTraceResult.Type.ENTITY) {
                    Entity targetEntity = ((EntityRayTraceResult) rayTrace).getEntity();
                    Vector3d tpDest = entity.getEyePosition(1.0F).add(entity.getLookAngle().scale(entity.getBbWidth()));
                    Vector3d tpVec = tpDest.subtract(targetEntity.position());
                    if (tpVec.lengthSqr() > TP_RANGE * TP_RANGE) {
                        tpVec = tpVec.normalize().scale(TP_RANGE);
                    }
                    Vector3d tpPos = targetEntity.position().add(tpVec);
                    targetEntity.teleportTo(tpPos.x, tpPos.y, tpPos.z);
                }
            }
        }
        else {
            RayTraceResult rayTrace = JojoModUtil.rayTrace(entity.getEyePosition(1.0F), entity.getLookAngle(), TP_RANGE, 
                    world, entity, e -> !(e.is(standEntity) || e.is(user)), 0, 0);
            Vector3d tpPos = rayTrace.getLocation();
            entity.teleportTo(tpPos.x, tpPos.y, tpPos.z);
        }
    }
    
    protected Vector3d getEntityTargetTeleportPos(Entity user, Entity target) {
        double distance = target.getBbWidth() + user.getBbWidth();
        return user.distanceToSqr(target) > distance * distance ? target.position().subtract(user.getLookAngle().scale(distance)) : user.position();
    }

}
