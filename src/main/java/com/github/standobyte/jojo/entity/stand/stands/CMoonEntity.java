package com.github.standobyte.jojo.entity.stand.stands;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.init.power.stand.ModStandActions;
import com.github.standobyte.jojo.power.stand.IStandPower;
import com.github.standobyte.jojo.util.damage.StandEntityDamageSource;

import net.minecraft.world.World;

public class CMoonEntity extends StandEntity {

    public CMoonEntity(StandEntityType<? extends StandEntity> type, World world) {
        super(type, world);
    }
    
    @Override
    public StandEntityDamageSource getDamageSource() {
        return new StandEntityDamageSource("c_moon", this, getUserPower());
    }
    
    @Override
    public void tick() {
        super.tick();
        
        if (!level.isClientSide() && getUser().position().y > 200) {
            IStandPower.getStandPowerOptional(getUser()).ifPresent(power -> {
                power.clear();
                power.givePower(ModStandActions.STAND_MADE_IN_HEAVEN.getStandType());
            });
        }
    }
}
