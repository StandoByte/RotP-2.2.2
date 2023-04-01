package com.github.standobyte.jojo.action.stand.qwe;

import java.util.List;

import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.KQBombEntity;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.init.ModEntityTypes;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.world.World;

public class KillerQueenDetonate extends StandEntityAction {

    public KillerQueenDetonate(StandEntityAction.Builder builder) {
        super(builder);
    }
    
    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            List<KQBombEntity> bombs = world.getEntities(ModEntityTypes.KQ_BOMB.get(), standEntity.getBoundingBox().inflate(64), bomb -> bomb.standUser == userPower.getUser());
            if (!bombs.isEmpty()) {
                world.playSound(null, 
                        standEntity.getX(), standEntity.getEyeY(), standEntity.getZ(), 
                        ModSounds.killer_queen_detonate.get(), standEntity.getSoundSource(), 1.0F, 1.0F);
                bombs.forEach(KQBombEntity::detonate);
            }
        }
    }

}
