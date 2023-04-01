package com.github.standobyte.jojo.power.nonstand.type.hamon.skill;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.github.standobyte.jojo.action.non_stand.HamonAction;
import com.github.standobyte.jojo.power.nonstand.type.hamon.HamonData;

import net.minecraft.entity.LivingEntity;

public class CharacterTechniqueHamonSkill extends AbstractHamonSkill {
    private CharacterHamonTechnique technique;
    
    public CharacterTechniqueHamonSkill(Builder builder) {
        super(builder.name, builder.rewardType, builder.rewardAction, builder.requiredSkills);
        HamonAction action = getRewardAction();
        if (action != null) {
            action.setSkillToMaster(this);
            if (action.hasShiftVariation()) {
                ((HamonAction) action.getShiftVariationIfPresent()).setSkillToMaster(this);
            }
        }
    }
    
    @Override
    public SkillType getSkillType() {
        return SkillType.CHARACTER_TECHNIQUE;
    }
    
    void setTechnique(CharacterHamonTechnique technique) {
        this.technique = technique;
    }
    
    public CharacterHamonTechnique getTechnique() {
        return technique;
    }
    
    public void learnNewSkill(HamonData hamon, LivingEntity user) {
        hamon.getTechniqueData().getNotMasteredSkill().ifPresent(prevSkill -> {
            hamon.removeHamonSkill(prevSkill);
        });
        super.learnNewSkill(hamon, user);
    }
    
    
    
    public static class Builder {
        private final String name;
        private final RewardType rewardType;
        private @Nullable Supplier<HamonAction> rewardAction = null;
        private final List<Supplier<? extends AbstractHamonSkill>> requiredSkills = new ArrayList<>();
        
        public Builder(String name, RewardType rewardType) {
            this.name = name;
            this.rewardType = rewardType;
        }
        
        public Builder unlocks(Supplier<HamonAction> rewardAction) {
            this.rewardAction = rewardAction;
            return this;
        }
        
        public Builder requiredSkill(Supplier<? extends AbstractHamonSkill> parentSkill) {
            if (parentSkill != null && !requiredSkills.contains(parentSkill)) {
                requiredSkills.add(parentSkill);
            }
            return this;
        }
        
        public CharacterTechniqueHamonSkill build() {
            return new CharacterTechniqueHamonSkill(this);
        }
    }
}
