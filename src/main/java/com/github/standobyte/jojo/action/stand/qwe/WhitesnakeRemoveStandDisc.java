package com.github.standobyte.jojo.action.stand.qwe;

import java.util.Optional;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.ActionTarget.TargetType;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.init.ModItems;
import com.github.standobyte.jojo.item.StandDiscItem;
import com.github.standobyte.jojo.power.stand.IStandPower;
import com.github.standobyte.jojo.power.stand.StandInstance;
import com.github.standobyte.jojo.util.utils.JojoModUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WhitesnakeRemoveStandDisc extends StandEntityAction {

    public WhitesnakeRemoveStandDisc(StandEntityAction.Builder builder) {
        super(builder);
    }
    
    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            ActionTarget t = task.getTarget();
            if (t.getType() == TargetType.ENTITY && t.getEntity() instanceof LivingEntity) {
                LivingEntity target = (LivingEntity) t.getEntity();
                IStandPower.getStandPowerOptional(target).ifPresent(power -> {
                    if (power.hasPower()) {
                        Optional<StandInstance> previousDiscStand = power.putOutStand();
                        previousDiscStand.ifPresent(prevStand -> 
                        JojoModUtil.giveItemTo(userPower.getUser(), StandDiscItem.withStand(new ItemStack(ModItems.STAND_DISC.get()), prevStand), true));
                    }
                });
            }
        }
    }
    
    @Override
    protected boolean standKeepsTarget(ActionTarget target) {
        return target.getType() == TargetType.ENTITY && target.getEntity() instanceof LivingEntity && !(target.getEntity() instanceof StandEntity);
    }
    
    @Override
    public TargetRequirement getTargetRequirement() {
        return TargetRequirement.ENTITY;
    }

}
