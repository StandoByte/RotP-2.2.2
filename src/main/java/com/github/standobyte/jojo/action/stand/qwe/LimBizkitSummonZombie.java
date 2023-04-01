package com.github.standobyte.jojo.action.stand.qwe;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.init.ModEntityTypes;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class LimBizkitSummonZombie extends StandAction {
    private static final List<Supplier<EntityType<? extends ZombieEntity>>> types =  Util.make(new ArrayList<>(), list -> {
        list.add(() -> EntityType.ZOMBIE);
        list.add(() -> EntityType.ZOMBIE);
        list.add(() -> EntityType.ZOMBIE_VILLAGER);
        list.add(() -> EntityType.HUSK);
        list.add(() -> EntityType.DROWNED);
        list.add(() -> EntityType.ZOMBIFIED_PIGLIN);
        list.add(() -> ModEntityTypes.HUNGRY_ZOMBIE.get());
    });

    public LimBizkitSummonZombie(StandAction.Builder builder) {
        super(builder);
    }
    
    @Override
    protected void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            PlayerEntity player = user instanceof PlayerEntity ? (PlayerEntity) user : null;
            EntityType<? extends ZombieEntity> type = types.get(user.getRandom().nextInt(types.size())).get();
            ZombieEntity zombie = (ZombieEntity) type.spawn((ServerWorld)world, null, player, 
                    new BlockPos(user.getEyePosition(1.0F).add(user.getLookAngle())), SpawnReason.SPAWN_EGG, false, false);
            zombie.addEffect(new EffectInstance(Effects.INVISIBILITY, 999999, 0, false, false, true));
        }
    }

}
