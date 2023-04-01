package com.github.standobyte.jojo.power.nonstand.type.hamon.skill;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import com.github.standobyte.jojo.JojoModConfig;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.init.power.hamon.ModHamonSkills;
import com.github.standobyte.jojo.network.PacketManager;
import com.github.standobyte.jojo.network.packets.fromserver.HamonSkillAddPacket;
import com.github.standobyte.jojo.network.packets.fromserver.TrHamonCharacterTechniquePacket;
import com.github.standobyte.jojo.power.nonstand.type.hamon.HamonData;
import com.github.standobyte.jojo.power.nonstand.type.hamon.HamonSkillsManager;
import com.github.standobyte.jojo.util.utils.JojoModUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.IForgeRegistry;

public class HamonTechniqueManager implements IHamonSkillsManager<CharacterTechniqueHamonSkill> {
    public static final int MAX_TECHNIQUE_SKILLS = 3;
    public static final int[] TECHNIQUE_SKILLS_STAT_REQUIREMENT = {20, 30, 40};
    private final Map<CharacterTechniqueHamonSkill, SkillLearningProgress> wrappedSkillMap = new LinkedHashMap<>();
    private CharacterHamonTechnique technique = null;
    
    public HamonTechniqueManager() {}

    public Collection<CharacterTechniqueHamonSkill> getLearnedSkills() {
        return Collections.unmodifiableSet(wrappedSkillMap.keySet());
    }

    @Override
    public void addSkill(CharacterTechniqueHamonSkill skill) {
        if (!isCurrentTechniquePerk(skill)) {
            wrappedSkillMap.put(skill, SkillLearningProgress.LEARNED);
        }
    }

    @Override
    public void removeSkill(CharacterTechniqueHamonSkill skill) {
        wrappedSkillMap.remove(skill);
    }
    
    @Override
    public boolean containsSkill(CharacterTechniqueHamonSkill skill) {
        return isCurrentTechniquePerk(skill) || wrappedSkillMap.containsKey(skill);
    }

    private static final ActionConditionResult TECHNIQUE_LOCKED = ActionConditionResult.createNegative(new TranslationTextComponent("hamon.closed.technique.locked"));
    private static final ActionConditionResult TECHNIQUE_MAX = ActionConditionResult.createNegative(new TranslationTextComponent("hamon.closed.technique.max"));
    private static final ActionConditionResult WRONG_TECHNIQUE = ActionConditionResult.createNegative(new TranslationTextComponent("hamon.closed.technique.bug"));
    @Override
    public ActionConditionResult canLearnSkill(LivingEntity user, HamonData hamon, CharacterTechniqueHamonSkill skill) {
        if (!JojoModConfig.getCommonConfigInstance(user.level.isClientSide()).mixHamonTechniques.get()
                && skill.getTechnique() != null && skill.getTechnique() != this.technique) {
            return WRONG_TECHNIQUE;
        }
        
        if (getNotMasteredSkill().isPresent()) {
            return ActionConditionResult.POSITIVE;
        }
        
        if (atMaxTechniqueSkills()) {
            return TECHNIQUE_MAX;
        }
        
        if (!hamon.hasTechniqueLevel(wrappedSkillMap.size())) {
            return TECHNIQUE_LOCKED;
        }

        return ActionConditionResult.POSITIVE;
    }
    
    public Optional<CharacterTechniqueHamonSkill> getNotMasteredSkill() {
        return wrappedSkillMap.entrySet().stream()
                .filter(entry -> entry.getValue() == SkillLearningProgress.LEARNED)
                .map(Map.Entry::getKey).findFirst();
    }
    
    public boolean isSkillMastered(AbstractHamonSkill skill) {
        return wrappedSkillMap.get(skill) == SkillLearningProgress.MASTERED;
    }
    
    private boolean atMaxTechniqueSkills() {
        return (int) wrappedSkillMap.entrySet().stream()
                .filter(entry -> entry.getValue() == SkillLearningProgress.MASTERED)
                .count() >= MAX_TECHNIQUE_SKILLS;
    }
    
    public boolean masterSkill(CharacterTechniqueHamonSkill skill) {
        if (wrappedSkillMap.get(skill) == SkillLearningProgress.LEARNED) {
            wrappedSkillMap.put(skill, SkillLearningProgress.MASTERED);
            return true;
        }
        return false;
    }

    public static int techniqueLevelReq(int skillsLearned) {
        return TECHNIQUE_SKILLS_STAT_REQUIREMENT[skillsLearned];
    }
    
    public CharacterHamonTechnique getTechnique() {
        return technique;
    }
    
    public boolean canPickTechnique() {
        return this.technique == null;
    }
    
    public void setTechnique(CharacterHamonTechnique technique) {
        this.technique = technique;
    }
    
    public void resetTechnique() {
        technique = null;
    }
    
    public void addPerks(LivingEntity user, HamonData hamon) {
        if (getTechnique() != null && !user.level.isClientSide() || user.is(ClientUtil.getClientPlayer())) {
            getTechnique().getPerksOnPick().forEach(perk -> {
                if (perk != null) {
                    hamon.addHamonSkill(user, perk, false, false);
                }
            });
        }
    }
    
    private boolean isCurrentTechniquePerk(CharacterTechniqueHamonSkill skill) {
        return technique != null && technique.getPerksOnPick().anyMatch(perk -> perk == skill);
    }
    
    
    
    public CompoundNBT toNBT() {
        CompoundNBT nbt = new CompoundNBT();
        
        ListNBT skillsList = new ListNBT();
        wrappedSkillMap.forEach((skill, state) -> {
            CompoundNBT skillNbt = new CompoundNBT();
            skillNbt.putString("Name", skill.getRegistryName().toString());
            skillNbt.putByte("Progress", (byte) (state.ordinal() + 1));
            skillsList.add(skillNbt);
        });
        nbt.put("Skills", skillsList);
        
        if (technique != null) {
            nbt.putString("CharacterTechnique", technique.getRegistryName().toString());
        }
        
        return nbt;
    }
    
    public void fromNBT(HamonSkillsManager mainSkillsHolder, CompoundNBT nbt) {
        ListNBT skillsNbt = nbt.getList("Skills", JojoModUtil.getNbtId(CompoundNBT.class));
        skillsNbt.forEach(entry -> {
            if (entry instanceof CompoundNBT) {
                CompoundNBT skillNbt = (CompoundNBT) entry;
                if (skillNbt.contains("Name", JojoModUtil.getNbtId(StringNBT.class))) {
                    AbstractHamonSkill s = ModHamonSkills.HAMON_SKILLS.getRegistry().getValue(new ResourceLocation(skillNbt.getString("Name")));
                    if (s instanceof CharacterTechniqueHamonSkill) {
                        CharacterTechniqueHamonSkill skill = (CharacterTechniqueHamonSkill) s;
                        byte val = skillNbt.getByte("Progress");
                        if (val > 0) {
                            mainSkillsHolder.addSkill(skill);
                            SkillLearningProgress state = SkillLearningProgress.values()[Math.min(val, SkillLearningProgress.values().length) - 1];
                            wrappedSkillMap.put(skill, state);
                        }
                    }
                }
            }
        });
        
        if (nbt.contains("CharacterTechnique", JojoModUtil.getNbtId(StringNBT.class))) {
            ResourceLocation techniqueId = new ResourceLocation(nbt.getString("CharacterTechnique"));
            IForgeRegistry<CharacterHamonTechnique> registry = ModHamonSkills.HAMON_CHARACTER_TECHNIQUES.getRegistry();
            if (registry.containsKey(techniqueId)) {
                this.technique = registry.getValue(techniqueId);
                
                if (technique != null) {
                    technique.getPerksOnPick().forEach(perk -> {
                        mainSkillsHolder.addSkill(perk);
                    });
                }
            }
        }
    }
    
    public void syncWithUser(ServerPlayerEntity user, HamonData hamon) {
        wrappedSkillMap.entrySet().stream()
        .filter(entry -> entry.getValue() == SkillLearningProgress.MASTERED)
        .map(Map.Entry::getKey)
        .forEach(skill -> PacketManager.sendToClient(HamonSkillAddPacket.masterTechniqueSkill(skill), user));
    }
    
    public void syncWithTrackingOrUser(LivingEntity user, ServerPlayerEntity tracking, HamonData hamon) {
        if (getTechnique() != null) {
            PacketManager.sendToClient(new TrHamonCharacterTechniquePacket(user.getId(), getTechnique()), tracking);
        }
    }
    
    
    
    private enum SkillLearningProgress {
        LEARNED,
        MASTERED;
    }
    
    public static class Accessor {
        private final HamonTechniqueManager techniqueData;
        
        public Accessor(HamonTechniqueManager techniqueData) {
            this.techniqueData = techniqueData;
        }

        public Collection<CharacterTechniqueHamonSkill> getLearnedSkills() {
            return techniqueData.getLearnedSkills();
        }
        
        public boolean isSkillMastered(AbstractHamonSkill skill) {
            return techniqueData.isSkillMastered(skill);
        }
        
        public Optional<CharacterTechniqueHamonSkill> getNotMasteredSkill() {
            return techniqueData.getNotMasteredSkill();
        }
        
        public boolean atMaxTechniqueSkills() {
            return techniqueData.atMaxTechniqueSkills();
        }
        
        public boolean canLearnNewTechniqueSkill(HamonData hamon) {
            return !atMaxTechniqueSkills() && hamon.hasTechniqueLevel(getLearnedSkills().size());
        }
    }
}
