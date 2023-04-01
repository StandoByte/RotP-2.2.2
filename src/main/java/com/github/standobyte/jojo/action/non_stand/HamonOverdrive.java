package com.github.standobyte.jojo.action.non_stand;

import javax.annotation.Nullable;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.init.ModParticles;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.init.power.hamon.ModHamon;
import com.github.standobyte.jojo.init.power.hamon.ModHamonSkills;
import com.github.standobyte.jojo.power.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.nonstand.type.hamon.HamonData;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.BaseHamonSkill.HamonStat;
import com.github.standobyte.jojo.util.damage.DamageUtil;
import com.github.standobyte.jojo.util.reflection.CommonReflection;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TieredItem;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class HamonOverdrive extends HamonAction {

    public HamonOverdrive(HamonAction.Builder builder) {
        super(builder);
    }
    
    @Override
    public ActionConditionResult checkHeldItems(LivingEntity user, INonStandPower power) {
        ItemStack heldItemStack = user.getMainHandItem();
        if (!heldItemStack.isEmpty() && !metalSilverOverdrive(power.getTypeSpecificData(ModHamon.HAMON.get()).get(), heldItemStack)) {
            return ActionConditionResult.NEGATIVE;
        }
        return ActionConditionResult.POSITIVE;
    }
    
    @Override
    public ActionConditionResult checkSpecificConditions(LivingEntity user, INonStandPower power, ActionTarget target) {
        if (user instanceof PlayerEntity && ((PlayerEntity) user).getAttackStrengthScale(0.5F) < 0.9F) {
            return ActionConditionResult.NEGATIVE;
        }
        return ActionConditionResult.POSITIVE;
    }
    
    @Override
    public boolean sendsConditionMessage() {
        return false;
    }

    @Override
    @Nullable
    protected SoundEvent getShout(LivingEntity user, INonStandPower power, ActionTarget target, boolean wasActive) {
        HamonData hamon = power.getTypeSpecificData(ModHamon.HAMON.get()).get();
        ItemStack heldItemStack = user.getMainHandItem();
        if (metalSilverOverdrive(hamon, heldItemStack)) {
            if (heldItemStack.getItem() instanceof SwordItem && heldItemStack.hasCustomHoverName() && "pluck".equals(heldItemStack.getHoverName().getString().toLowerCase())) {
                return ModSounds.JONATHAN_PLUCK_SWORD.get();
            }
        }
        return null;
    }
    
    @Override
    protected void perform(World world, LivingEntity user, INonStandPower power, ActionTarget target) {
        if (!world.isClientSide()) {
            Entity entity = target.getEntity();
            if (entity instanceof LivingEntity) {
                LivingEntity targetEntity = (LivingEntity) entity;
                HamonData hamon = power.getTypeSpecificData(ModHamon.HAMON.get()).get();
                boolean metalSilverOverdrive = metalSilverOverdrive(hamon, user.getMainHandItem());
                
                float damage = 3F;
                float efficiency = hamon.getHamonEfficiency(getEnergyCost(power));
                
                damage *= efficiency;
                
                int attackStrengthTicker = CommonReflection.getAttackStrengthTicker(user);
                if (DamageUtil.dealHamonDamage(targetEntity, damage, user, null, 
                        metalSilverOverdrive ? attack -> attack.hamonParticle(ModParticles.HAMON_SPARK_SILVER.get()) : null)) {
                    hamon.hamonPointsFromAction(HamonStat.STRENGTH, getEnergyCost(power) * efficiency);
                }
                // FIXME (hamon) !! ClientPlayerEntity#swing sends the packet to server, and THEN ServerPlayerEntity#swing resets attackStrengthTicker
                /*
                 * ⣀⣠⣤⣤⣤⣤⢤⣤⣄⣀⣀⣀⣀⡀⡀⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄
                 * ⠄⠉⠹⣾⣿⣛⣿⣿⣞⣿⣛⣺⣻⢾⣾⣿⣿⣿⣶⣶⣶⣄⡀⠄⠄⠄
                 * ⠄⠄⠠⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣿⣿⣿⣿⣿⣿⣆⠄⠄
                 * ⠄⠄⠘⠛⠛⠛⠛⠋⠿⣷⣿⣿⡿⣿⢿⠟⠟⠟⠻⠻⣿⣿⣿⣿⡀⠄
                 * ⠄⢀⠄⠄⠄⠄⠄⠄⠄⠄⢛⣿⣁⠄⠄⠒⠂⠄⠄⣀⣰⣿⣿⣿⣿⡀
                 * ⠄⠉⠛⠺⢶⣷⡶⠃⠄⠄⠨⣿⣿⡇⠄⡺⣾⣾⣾⣿⣿⣿⣿⣽⣿⣿
                 * ⠄⠄⠄⠄⠄⠛⠁⠄⠄⠄⢀⣿⣿⣧⡀⠄⠹⣿⣿⣿⣿⣿⡿⣿⣻⣿
                 * ⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠉⠛⠟⠇⢀⢰⣿⣿⣿⣏⠉⢿⣽⢿⡏
                 * ⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠠⠤⣤⣴⣾⣿⣿⣾⣿⣿⣦⠄⢹⡿⠄
                 * ⠄⠄⠄⠄⠄⠄⠄⠄⠒⣳⣶⣤⣤⣄⣀⣀⡈⣀⢁⢁⢁⣈⣄⢐⠃⠄
                 * ⠄⠄⠄⠄⠄⠄⠄⠄⠄⣰⣿⣛⣻⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡯⠄⠄
                 * ⠄⠄⠄⠄⠄⠄⠄⠄⠄⣬⣽⣿⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⠄⠄
                 * ⠄⠄⠄⠄⠄⠄⠄⠄⠄⢘⣿⣿⣻⣛⣿⡿⣟⣻⣿⣿⣿⣿⡟⠄⠄⠄
                 * ⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠛⢛⢿⣿⣿⣿⣿⣿⣿⣷⡿⠁⠄⠄⠄
                 * ⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠉⠉⠉⠉⠈⠄⠄⠄⠄⠄⠄
                 */
                CommonReflection.setAttackStrengthTicker(user, attackStrengthTicker);
            }
        }
    }
    
    public boolean metalSilverOverdrive(HamonData hamon, ItemStack heldItemStack) {
        return hamon.isSkillLearned(ModHamonSkills.METAL_SILVER_OVERDRIVE.get()) && heldItemStack.getItem() instanceof TieredItem;
    }
}
