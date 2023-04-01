package com.github.standobyte.jojo.action.stand.qwe;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.ActionTarget.TargetType;
import com.github.standobyte.jojo.action.non_stand.HamonHealing;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

public class GoldExperienceCreateLife extends StandEntityAction {
    private static final Random RANDOM = new Random();

    public GoldExperienceCreateLife(StandEntityAction.Builder builder) {
        super(builder);
    }
    
    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            PlayerEntity player = standEntity.getUser() instanceof PlayerEntity ? (PlayerEntity) standEntity.getUser() : null;
            List<EntityType<?>> animals = ForgeRegistries.ENTITIES.getValues().stream()
                    .filter(type -> type.getCategory() == EntityClassification.CREATURE)
                    .collect(Collectors.toList());
            if (!animals.isEmpty()) {
                animals.get(RANDOM.nextInt(animals.size())).spawn((ServerWorld)world, null, player, 
                        new BlockPos(standEntity.getEyePosition(1.0F).add(standEntity.getLookAngle())), SpawnReason.SPAWN_EGG, false, false);
            }
            if (player != null && task.getTarget().getType() == TargetType.BLOCK) {
                Direction face = task.getTarget().getFace();
                HamonHealing.bonemealEffect(world, player, task.getTarget().getBlockPos(), face);
            }
        }
    }
    
    @Override
    protected boolean standKeepsTarget(ActionTarget target) {
        return target.getType() == TargetType.BLOCK;
    }

}
