package com.github.standobyte.jojo.action.non_stand;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.init.power.hamon.ModHamon;
import com.github.standobyte.jojo.power.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.nonstand.type.hamon.HamonData;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.AbstractHamonSkill;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.CharacterHamonTechnique;
import com.github.standobyte.jojo.util.utils.JojoModUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvent;

public abstract class HamonAction extends NonStandAction {
    private final Map<Supplier<CharacterHamonTechnique>, Supplier<SoundEvent>> voiceLinesUnregistered;
    private Map<CharacterHamonTechnique, Supplier<SoundEvent>> voiceLines = null;
    private Optional<AbstractHamonSkill> skillToMaster = Optional.empty();
    
    public HamonAction(HamonAction.Builder builder) {
        super(builder);
        voiceLinesUnregistered = builder.voiceLines;
    }
    
    @Override
    public ActionConditionResult checkConditions(LivingEntity user, INonStandPower power, ActionTarget target) {
        ActionConditionResult hamonCheck = power.getTypeSpecificData(ModHamon.HAMON.get()).map(hamon -> {
            if (hamon.getBloodstreamEfficiency() <= 0F) {
                return conditionMessage("hamon_no_bloodstream");
            }
            if (!isMastered(hamon) && 
                    (power.getHeldAction() != this && power.getEnergy() < power.getMaxEnergy()
                    || power.getEnergy() <= 0)) {
                return conditionMessage("full_energy");
            }
            return ActionConditionResult.POSITIVE;
        }).orElse(conditionMessage("no_hamon_wtf"));
        if (!hamonCheck.isPositive()) {
            return hamonCheck;
        }
        
        return super.checkConditions(user, power, target);
    }
    
    @Override
    public float getEnergyCost(INonStandPower power) {
        float cost = super.getEnergyCost(power);
        if (JojoModUtil.booleanMapOrFalse(power.getTypeSpecificData(ModHamon.HAMON.get()), 
                hamon -> !isMastered(hamon))) {
            cost = Math.min(power.getMaxEnergy(), cost * 10);
        }
        return cost;
    }
    
    @Override
    public float getHeldTickEnergyCost(INonStandPower power) {
        float cost = super.getHeldTickEnergyCost(power);
        if (JojoModUtil.booleanMapOrFalse(power.getTypeSpecificData(ModHamon.HAMON.get()), 
                hamon -> !isMastered(hamon))) {
            cost = Math.min(power.getMaxEnergy() / 20, cost * 10);
        }
        return cost;
    }
    
    public void setSkillToMaster(AbstractHamonSkill skill) {
        this.skillToMaster = Optional.ofNullable(skill);
    }
    
    protected boolean isMastered(HamonData hamon) {
        return skillToMaster.map(skill -> hamon.getTechniqueData().isSkillMastered(skill)).orElse(true);
    }

    @Override
    @Nullable
    protected SoundEvent getShout(LivingEntity user, INonStandPower power, ActionTarget target, boolean wasActive) {
        SoundEvent shout = null;
        CharacterHamonTechnique technique = power.getTypeSpecificData(ModHamon.HAMON.get()).get().getCharacterTechnique();
        if (technique != null) {
            if (voiceLines == null) {
                voiceLines = voiceLinesUnregistered.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey().get(), entry -> entry.getValue()));
            }
            Supplier<SoundEvent> shoutSupplier = voiceLines.get(technique);
            if (shoutSupplier != null) {
                shout = shoutSupplier.get();
            }
        }
        if (shout == null) {
            shout = super.getShout(user, power, target, wasActive);
        }
        return shout;
    }
    
    
    
    public static class Builder extends NonStandAction.AbstractBuilder<HamonAction.Builder> {
        private Map<Supplier<CharacterHamonTechnique>, Supplier<SoundEvent>> voiceLines = new HashMap<>();

        @Override
        protected HamonAction.Builder getThis() {
            return this;
        }
        
        public HamonAction.Builder shout(Supplier<CharacterHamonTechnique> technique, Supplier<SoundEvent> shoutSupplier) {
            if (technique != null) {
                voiceLines.put(technique, shoutSupplier);
            }
            return getThis();
        }
    }
}
