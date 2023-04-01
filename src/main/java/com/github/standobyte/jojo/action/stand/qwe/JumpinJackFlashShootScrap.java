package com.github.standobyte.jojo.action.stand.qwe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.ActionTarget.TargetType;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.damaging.projectile.TommyGunBulletEntity;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.stand.IStandPower;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class JumpinJackFlashShootScrap extends StandEntityAction {
    private static final Random RANDOM = new Random();

    public JumpinJackFlashShootScrap(StandEntityAction.Builder builder) {
        super(builder);
    }
    
    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide() && userPower.getUser() instanceof PlayerEntity) {
            PlayerInventory inventory = ((PlayerEntity) userPower.getUser()).inventory;
            List<ItemStack> nuggets = new ArrayList<>();
            int count = 0;
            for (int i = 0; i < inventory.getContainerSize(); i++) {
                ItemStack item = inventory.getItem(i);
                if (!item.isEmpty() && item.getItem() == Items.IRON_NUGGET) {
                    nuggets.add(item);
                    count += item.getCount();
                    if (count >= 32) {
                        count = 32;
                        break;
                    }
                }
            }
            
            int shot = 0;
            for (ItemStack nuggetItem : nuggets) {
                int toShoot = Math.min(nuggetItem.getCount(), count - shot);
                nuggetItem.shrink(toShoot);
                shot += toShoot;
                if (shot >= count) break;
            }
            
            for (int i = 0; i < shot; i++) {
                TommyGunBulletEntity knifeEntity = new TommyGunBulletEntity(standEntity, world);
                knifeEntity.shootFromRotation(standEntity, 4.0F, 2.0F);
                world.addFreshEntity(knifeEntity);
            }
        }
    }
    
    @Override
    protected boolean standKeepsTarget(ActionTarget target) {
        return target.getType() == TargetType.BLOCK;
    }

}
