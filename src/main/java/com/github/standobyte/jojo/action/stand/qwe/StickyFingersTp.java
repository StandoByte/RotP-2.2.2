package com.github.standobyte.jojo.action.stand.qwe;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.ActionTarget.TargetType;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.power.stand.IStandPower;
import com.github.standobyte.jojo.util.utils.JojoModUtil;
import com.github.standobyte.jojo.util.utils.MathUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class StickyFingersTp extends StandAction {

    public StickyFingersTp(StandAction.Builder builder) {
        super(builder);
    }
    
    @Override
    protected void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            Vector3d blinkPos = null;
            if (target.getType() == TargetType.EMPTY) {
                RayTraceResult rayTrace = JojoModUtil.rayTrace(user, 12, null);
                if (rayTrace.getType() == RayTraceResult.Type.MISS) {
                    blinkPos = rayTrace.getLocation();
                }
                target = ActionTarget.fromRayTraceResult(rayTrace);
            }
            switch (target.getType()) {
            case ENTITY:
                Entity targetEntity = target.getEntity();
                blinkPos = targetEntity.position().subtract(targetEntity.getLookAngle().scale(targetEntity.getBbWidth() + user.getBbWidth()));
                Vector3d toTarget = target.getEntity().position().subtract(blinkPos);
                user.yRot = MathUtil.yRotDegFromVec(toTarget);
                user.yRotO = user.yRot;
                break;
            case BLOCK:
                BlockPos blockPosTargeted = target.getBlockPos();
                blinkPos = Vector3d.atBottomCenterOf(world.isEmptyBlock(blockPosTargeted.above()) ? blockPosTargeted.above() : blockPosTargeted.relative(target.getFace()));
                break;
            default:
                Vector3d pos = blinkPos;
                BlockPos blockPos = new BlockPos(pos);
                while (world.isEmptyBlock(blockPos.below()) && blockPos.getY() > 0) {
                    blockPos = blockPos.below();
                }
                blinkPos = new Vector3d(pos.x, blockPos.getY() > 0 ? blockPos.getY() : user.position().y, pos.z);
                break;
            }
            
            if (blinkPos != null) {
                world.playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.sticky_fingers_zipper.get(), user.getSoundSource(), 1.0F, 1.0F);
                user.teleportTo(blinkPos.x, blinkPos.y, blinkPos.z);
                world.playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.sticky_fingers_zipper.get(), user.getSoundSource(), 1.0F, 1.0F);
            }
        }
    }
}
