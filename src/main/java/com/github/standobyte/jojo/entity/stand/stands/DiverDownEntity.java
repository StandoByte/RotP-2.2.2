package com.github.standobyte.jojo.entity.stand.stands;

import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityType;

import net.minecraft.world.World;

public class DiverDownEntity extends StandEntity {

    public DiverDownEntity(StandEntityType<? extends StandEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected boolean shouldHaveNoPhysics() {
        return true;
    }
    
    @Override
    protected void moveFromBlocks() {}
}
