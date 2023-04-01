package com.github.standobyte.jojo.client.ui.screen.hamon;

import java.util.Collection;
import java.util.List;

import com.github.standobyte.jojo.power.nonstand.type.hamon.HamonData;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.AbstractHamonSkill;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.HamonTechniqueManager;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.TranslationTextComponent;

public class HamonSkillElementLearnable extends HamonSkillGuiElement {
    private final List<IReorderingProcessor> description;
    private final boolean isFinal;
    private int stateIndex;
    private State state;
    
    public HamonSkillElementLearnable(AbstractHamonSkill skill, 
            HamonData hamon, LivingEntity user, Collection<? extends AbstractHamonSkill> teacherSkills, 
            boolean isFinal, int x, int y, FontRenderer font) {
        super(skill, x, y, 26, 26);
        description = font.split(new TranslationTextComponent("hamonSkill." + skill.getName() + ".desc"), 200);
        this.isFinal = isFinal;
        updateState(hamon, user, teacherSkills);
    }
    
    List<IReorderingProcessor> getDescription() {
        return description;
    }
    
    void updateState(HamonData hamon, LivingEntity user, Collection<? extends AbstractHamonSkill> teacherSkills) {
        boolean canBeLearned = hamon.canLearnSkill(user, skill, teacherSkills).isPositive();
        boolean isLearned = hamon.isSkillLearned(skill);
        HamonTechniqueManager.Accessor techniqueData = hamon.getTechniqueData();
        boolean techniqueSkillNotMastered = techniqueData.getLearnedSkills().contains(skill) && !techniqueData.isSkillMastered(skill);
        updateState(canBeLearned, isLearned, techniqueSkillNotMastered);
    }
    
    State getState() {
        return state;
    }
    
    private void updateState(boolean canBeLearned, boolean isLearned, boolean techniqueNotMastered) {
        this.state = State.getState(isFinal, canBeLearned, isLearned, techniqueNotMastered);
    }
    
    void blitBgSquare(MatrixStack matrixStack, int x, int y) {
        AbstractGui.blit(matrixStack, getX() + x, getY() + y, 
                state.textureX, state.textureY, 26, 26, 256, 256);
    }
    
    void blitBgSquareSelection(MatrixStack matrixStack, int x, int y) {
        int texY = state.isFinal ? 190 : 164;
        AbstractGui.blit(matrixStack, getX() + x, getY() + y, 
                0, texY, 26, 26, 256, 256);
    }
    
    enum State {
        CLOSED_NORMAL(0, 0,         false,  false,  false,  false),
        CLOSED_FINAL(0, 78,         true,   false,  false,  false),
        OPENED_NORMAL(0, 26,        false,  true,   false,  false),
        OPENED_FINAL(0, 104,        true,   true,   false,  false),
        LEARNED_NORMAL(0, 52,       false,  false,  true,   false),
        LEARNED_FINAL(0, 130,       true,   false,  true,   false),
        NOT_MASTERED_NORMAL(32, 52, false,  false,  false,  true),
        NOT_MASTERED_FINAL(32, 130, true,   false,  false,  true);
        
        public final boolean isFinal;
        public final boolean canBeLearned;
        public final boolean isLearned;
        public final boolean techniqueNotMastered;
        private final int textureX;
        private final int textureY;
        
        private State(int textureX, int textureY, 
                boolean isFinal, boolean canBeLearned, boolean isLearned, boolean techniqueNotMastered) {
            this.textureX = textureX;
            this.textureY = textureY;
            this.isFinal = isFinal;
            this.canBeLearned = canBeLearned;
            this.isLearned = isLearned;
            this.techniqueNotMastered = techniqueNotMastered;
        }
        
        private static State getState(boolean isFinal, boolean canBeLearned, boolean isLearned, boolean techniqueNotMastered) {
            int stateIndex = isFinal ? 1 : 0;
            stateIndex += techniqueNotMastered ? 6 : isLearned ? 4 : canBeLearned ? 2 : 0;
            return State.values()[stateIndex];
        }
    }
}
