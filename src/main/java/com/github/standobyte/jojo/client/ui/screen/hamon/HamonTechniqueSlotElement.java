package com.github.standobyte.jojo.client.ui.screen.hamon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.github.standobyte.jojo.power.nonstand.type.hamon.HamonData;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.CharacterTechniqueHamonSkill;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.HamonTechniqueManager;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;

public class HamonTechniqueSlotElement {
    private final int x;
    private final int y;
    private Optional<HamonSkillElementLearnable> skillElement = Optional.empty();
    private State state;

    public HamonTechniqueSlotElement(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setSkill(HamonSkillElementLearnable skillElement) {
        this.skillElement = Optional.ofNullable(skillElement);
    }
    
    public static List<HamonTechniqueSlotElement> createSlots(HamonScreen screen, Function<Integer, HamonTechniqueSlotElement> createSlot) {
        HamonData hamon = screen.hamon;
        Minecraft mc = screen.getMinecraft();
        boolean freeSlot = true;
        List<HamonTechniqueSlotElement> slots = new ArrayList<>();
        Iterator<CharacterTechniqueHamonSkill> learnedSkills = hamon.getTechniqueData().getLearnedSkills().iterator();
        for (int i = 0; i < HamonTechniqueManager.MAX_TECHNIQUE_SKILLS; i++) {
            HamonTechniqueSlotElement slot = createSlot.apply(i);
            slots.add(slot);
            
            State state;
            if (!hamon.hasTechniqueLevel(i)) {
                state = State.LOCKED;
            }
            else if (learnedSkills.hasNext()) {
                CharacterTechniqueHamonSkill skill = learnedSkills.next();
                
                state = State.HAS_SKILL;
                slot.setSkill(new HamonSkillElementLearnable(skill, 
                        hamon, mc.player, screen.teacherSkills, 
                        false, slot.x, slot.y, mc.font));
                if (!learnedSkills.hasNext()) {
                    freeSlot = !hamon.getTechniqueData().getNotMasteredSkill().isPresent();
                }
            }
            else {
                state = freeSlot ? State.EMPTY : State.NEEDS_PREV_MASTERED;
                freeSlot = false;
            }
            
            slot.state = state;
        }
        return slots;
    }
    
    void renderSlot(MatrixStack matrixStack, int x, int y) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        x += this.x;
        y += this.y;
        Minecraft.getInstance().getTextureManager().bind(HamonSkillsTabGui.HAMON_SKILLS);
        AbstractGui.blit(matrixStack, x, y, 48, 0, 28, 28, 256, 256);
        
        x++;
        y++;
        switch (state) {
        case LOCKED:
            AbstractGui.blit(matrixStack, x, y, 
                    81, 1, 26, 26, 256, 256);
            break;
        case EMPTY:
            AbstractGui.blit(matrixStack, x, y, 
                    0, 26, 26, 26, 256, 256);
            break;
        case HAS_SKILL:
            HamonSkillElementLearnable skillIcon = skillElement.get();
            x -= skillIcon.getX();
            y -= skillIcon.getY();
            skillIcon.blitBgSquare(matrixStack, x, y);
            skillIcon.renderSkillIcon(matrixStack, x + 5, y + 5);
            break;
        case NEEDS_PREV_MASTERED:
            AbstractGui.blit(matrixStack, x, y, 
                    0, 0, 26, 26, 256, 256);
            break;
        }

        RenderSystem.disableBlend();
    }
    
    Optional<HamonSkillElementLearnable> getSkillElement() {
        return skillElement;
    }
    
    
    
    private enum State {
        LOCKED,
        EMPTY,
        HAS_SKILL,
        NEEDS_PREV_MASTERED
    }
}
