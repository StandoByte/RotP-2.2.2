package com.github.standobyte.jojo.action.stand.qwe;

import java.util.Optional;
import java.util.UUID;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.ActionTarget.TargetType;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.power.stand.IStandPower;
import com.github.standobyte.jojo.util.utils.JojoModUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class KissDupe extends StandAction {

    public KissDupe(StandAction.Builder builder) {
        super(builder);
    }
    
    @Override
    protected void perform(World world, LivingEntity user, IStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            ItemStack item = ItemStack.EMPTY;
            if (target.getType() == TargetType.ENTITY) {
                Entity entity = target.getEntity();
                if (!(entity instanceof PlayerEntity)) {
                    if (JojoModUtil.orElseFalse(EntityType.create(entity.serializeNBT(), world), e -> {
                        e.setUUID(UUID.randomUUID());
                        world.addFreshEntity(e);
                        return true;
                    })) {
                        return;
                    }
                }
            }
            
            if (target.getType() == TargetType.BLOCK) {
                Item blockItem = world.getBlockState(target.getBlockPos()).getBlock().asItem();
                if (blockItem != null && blockItem != Items.AIR) {
                    item = new ItemStack(blockItem);
                }
            }
            
            if (item.isEmpty()) {
                ItemStack heldItem = user.getOffhandItem();
                if (heldItem.isEmpty()) {
                    heldItem = user.getMainHandItem();
                }
                if (!heldItem.isEmpty()) {
                    item = heldItem.copy();
                    item.setCount(1);
                }
            }
            
            if (!item.isEmpty()) {
                JojoModUtil.giveItemTo(user, item, true);
            }
        }
    }

}
