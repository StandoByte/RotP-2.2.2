package com.github.standobyte.jojo.client.ui.screen.hamon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.standobyte.jojo.JojoModConfig;
import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.init.power.hamon.ModHamonSkills;
import com.github.standobyte.jojo.network.PacketManager;
import com.github.standobyte.jojo.network.packets.fromclient.ClHamonLearnButtonPacket;
import com.github.standobyte.jojo.network.packets.fromclient.ClHamonPickTechniquePacket;
import com.github.standobyte.jojo.network.packets.fromclient.ClHamonResetSkillsButtonPacket.HamonSkillsTab;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.AbstractHamonSkill;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.CharacterHamonTechnique;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.CharacterTechniqueHamonSkill;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.HamonTechniqueManager;
import com.github.standobyte.jojo.util.utils.JojoModUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class HamonTechniqueTabGui extends HamonSkillsTabGui {
    private CharacterHamonTechnique technique;
    private List<HamonCharacterTechniqueBox> availableHamonTechniques = Collections.emptyList();
    private List<HamonScreenButton> pickTechniqueButtons = Collections.emptyList();
    private final List<IReorderingProcessor> availableTechniqueSkillLines;
    private final List<IReorderingProcessor> techniqueSkillCanBeMasteredLines;
    private final List<IReorderingProcessor> tabLockedLines;
    private List<HamonTechniqueSlotElement> techniqueSkillSlots = Collections.emptyList();
    private HamonScreenButton masterSkillButton;
    
    HamonTechniqueTabGui(Minecraft minecraft, HamonScreen screen, int index, String title) {
        super(minecraft, screen, index, title, -1, -1);
        fillSkillLines();
        availableTechniqueSkillLines = minecraft.font.split(new TranslationTextComponent("hamon.technique_available"), 100);
        techniqueSkillCanBeMasteredLines = minecraft.font.split(new TranslationTextComponent("hamon.technique_not_mastered"), 100);
        tabLockedLines = minecraft.font.split(new TranslationTextComponent("hamon.techniques_locked", HamonTechniqueManager.techniqueLevelReq(0)), 200);
    }
    
    @Override
    protected ITextComponent createTabDescription(String key) {
        return new TranslationTextComponent(key, 
                JojoModConfig.getCommonConfigInstance(true).mixHamonTechniques.get() ? "" : new TranslationTextComponent("hamon.techniques.tab.desc.only_one"),
                HamonTechniqueManager.techniqueLevelReq(0), 
                HamonTechniqueManager.techniqueLevelReq(1), 
                HamonTechniqueManager.techniqueLevelReq(2));
    }

    @Override
    void addButtons() {
        super.addButtons();
        
        screen.addButton(masterSkillButton = new HamonScreenButton(learnButton.x, learnButton.y, learnButton.getWidth(), learnButton.getHeight(), 
                new TranslationTextComponent("hamon.master_technique_skill_button"), button -> {
                    if (getSelectedSkill() != null) {
                        PacketManager.sendToServer(ClHamonLearnButtonPacket.masterTechniqueSkill(getSelectedSkill().getHamonSkill()));
                        screen.clickedOnSkill = true;
                    }
                }));
    }
    
    private void fillSkillLines() {
        skills.clear();
        
        // available techniques
        List<CharacterHamonTechnique> techniques;
        this.technique = screen.hamon.getCharacterTechnique();
        if (technique != null && !JojoModConfig.getCommonConfigInstance(true).mixHamonTechniques.get()) {
            techniques = Util.make(new ArrayList<>(), list -> list.add(technique));
        }
        else {
            techniques = new ArrayList<>(ModHamonSkills.HAMON_CHARACTER_TECHNIQUES.getRegistry().getValues());
            Collections.sort(techniques, TECHNIQUES_ORDER);
        }
        
        List<HamonScreenButton> newButtons = new ArrayList<>();
        
        // technique skill slots
        float interval = (float) (HamonScreen.WINDOW_WIDTH - HamonScreen.WINDOW_THIN_BORDER * 2)
                / (float) HamonTechniqueManager.MAX_TECHNIQUE_SKILLS;
        techniqueSkillSlots = HamonTechniqueSlotElement.createSlots(screen, i -> new HamonTechniqueSlotElement(
                (int) ((i + 0.5F) * interval) - 14, 92));
        
        // technique names, buttons, skill squares and y coordinates
        availableHamonTechniques = new ArrayList<>();
        int techniqueY = 130;
        for (CharacterHamonTechnique technique : techniques) {
            List<CharacterTechniqueHamonSkill> skills = technique.getSkills().collect(Collectors.toList());
            List<IReorderingProcessor> name = minecraft.font.split(new TranslationTextComponent("hamon.technique." + technique.getName()), 192);
            int j = 0;
            for (CharacterTechniqueHamonSkill skill : skills) {
                int x = HamonScreen.WINDOW_WIDTH - 21 - (3 - j) * 30;
                int y = techniqueY + name.size() * 9 + 2;
                this.skills.put(skill, new HamonSkillElementLearnable(skill, 
                        screen.hamon, minecraft.player, screen.teacherSkills, 
                        false, x, y, minecraft.font));
                j++;
            }
            HamonCharacterTechniqueBox techniqueBox = new HamonCharacterTechniqueBox(technique, techniqueY, name, minecraft.font);
            availableHamonTechniques.add(techniqueBox);
            HamonScreenButton pickButton = new HamonScreenButton(
                    screen.windowPosX() + 16, screen.windowPosY() + techniqueY + techniqueBox.getHeight() - 1, 
                    80, 20, 
                    new TranslationTextComponent("hamon.pick_technique"), 
                    button -> {
                        PacketManager.sendToServer(new ClHamonPickTechniquePacket(technique));
                    });
            newButtons.add(pickButton);
            techniqueY += techniqueBox.getHeight() + 10;
        }
        
        maxY = techniqueY;
        int actualWindowHeight = HamonScreen.WINDOW_HEIGHT - HamonScreen.WINDOW_UPPER_BORDER - HamonScreen.WINDOW_THIN_BORDER;
        if (maxY < actualWindowHeight) {
            maxY = -1;
            scrollY = 0;
        }
        else {
            scrollY = Math.max(scrollY, -maxY + actualWindowHeight);
        }
        
        if (getSelectedSkill() != null) {
            selectSkill(skills.get(getSelectedSkill().getHamonSkill()));
        }
        
        pickTechniqueButtons.forEach(screen::removeButton);
        pickTechniqueButtons = newButtons;
        newButtons.forEach(screen::addButton);
    }
    
    @Override
    List<HamonScreenButton> getButtons() {
        return Stream.concat(Stream.concat(
                super.getButtons().stream(), 
                pickTechniqueButtons.stream()),
                Stream.of(this.masterSkillButton))
                .collect(Collectors.toList());
    }
    
    @Override
    protected HamonSkillsTab getSkillsType() {
        return HamonSkillsTab.TECHNIQUE;
    }
    
    @Override
    void drawTab(MatrixStack matrixStack, int windowX, int windowY, boolean isSelected) {
        super.drawTab(matrixStack, windowX, windowY, isSelected);
        if (screen.hamon.getTechniqueData().getNotMasteredSkill().isPresent()) {
            minecraft.getTextureManager().bind(HamonScreen.WINDOW);
            blit(matrixStack, windowX - 32 + 7, windowY + getTabY(tabIndex) + 3, 240, 156, 8, 8);
        }
        else if (screen.hamon.getTechniqueData().canLearnNewTechniqueSkill(screen.hamon)) {
            minecraft.getTextureManager().bind(HamonScreen.WINDOW);
            blit(matrixStack, windowX - 32 + 7, windowY + getTabY(tabIndex) + 3, 248, 156, 8, 8);
        }
    }

    @Override
    protected void drawDesc(MatrixStack matrixStack) {
        if (getSelectedSkill() != null) {
            drawSkillDesc(matrixStack);
        }
        else {
            for (int i = 0; i < descLines.size(); i++) {
                minecraft.font.draw(matrixStack, descLines.get(i), (float) scrollX + 6, (float) scrollY + 5 + i * 9, 0xFFFFFF);
            }
        }
    }
    
    @Override
    List<IReorderingProcessor> additionalTabNameTooltipInfo() {
        if (screen.hamon.getTechniqueData().getNotMasteredSkill().isPresent()) {
            return techniqueSkillCanBeMasteredLines;
        }
        else if (screen.hamon.getTechniqueData().canLearnNewTechniqueSkill(screen.hamon)) {
            return availableTechniqueSkillLines;
        }
        return super.additionalTabNameTooltipInfo();
    }
    
    @Override
    protected boolean isLocked() {
        return screen.hamon.getCharacterTechnique() == null && !screen.hamon.hasTechniqueLevel(0);
    }
    
    @Override
    protected void updateButton() {
        if (isLocked()) {
            learnButton.visible = false;
            creativeResetButton.visible = false;
            masterSkillButton.visible = false;
            pickTechniqueButtons.forEach(button -> button.visible = false);
        }
        else {
            super.updateButton();
            this.technique = screen.hamon.getCharacterTechnique();
            if (learnButton.visible && technique == null
                    && getSelectedSkill().getHamonSkill() instanceof CharacterTechniqueHamonSkill) {
                learnButton.visible = false;
            }
            pickTechniqueButtons.forEach(button -> button.visible = technique == null);

            masterSkillButton.visible = false;
            if (!learnButton.visible && getSelectedSkill() != null) {
                masterSkillButton.visible = getSelectedSkill().getState().techniqueNotMastered;
            }
            masterSkillButton.active = masterSkillButton.visible;
        }
    }
    
    @Override
    protected void drawText(MatrixStack matrixStack) {
        if (!isLocked()) {
            drawDesc(matrixStack);
            availableHamonTechniques.forEach(technique -> technique.drawText(matrixStack, minecraft.font, screen.hamon, intScrollX, intScrollY));
        }
        else {
            for (int i = 0; i < tabLockedLines.size(); i++) {
                ClientUtil.drawCenteredString(matrixStack, minecraft.font, tabLockedLines.get(i), 
                        (float) (scrollX - HamonScreen.WINDOW_THIN_BORDER + HamonScreen.WINDOW_WIDTH / 2), (float) (scrollY + 22 + i * 9), 0xFFFFFF);
            }
        }
    }

    @Override
    protected void drawActualContents(MatrixStack matrixStack, int mouseX, int mouseY) {
        if (!isLocked()) {
            availableHamonTechniques.forEach(technique -> technique.render(matrixStack, screen.hamon, intScrollX, intScrollY, mouseX, mouseY));
            super.drawActualContents(matrixStack, mouseX, mouseY);
        }
        drawTechniqueSlots(matrixStack, mouseX, mouseY);
    }
    
    private void drawTechniqueSlots(MatrixStack matrixStack, int mouseX, int mouseY) {
        for (HamonTechniqueSlotElement slot : techniqueSkillSlots) {
            slot.renderSlot(matrixStack, intScrollX, intScrollY);
        }
    }

    @Override
    boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (isLocked()) return false;
        
        if (mouseButton == 0) {
            for (HamonTechniqueSlotElement slot : techniqueSkillSlots) {
                if (JojoModUtil.booleanMapOrFalse(slot.getSkillElement(), skillSlot -> {
                    if (skillSlot.isMouseOver(intScrollX, intScrollY, (int) mouseX, (int) mouseY)) {
                        AbstractHamonSkill skill = skillSlot.getHamonSkill();
                        for (HamonTabGui tab : screen.selectableTabs) {
                            if (tab instanceof HamonSkillsTabGui) {
                                Map<AbstractHamonSkill, HamonSkillElementLearnable> tabSkills = ((HamonSkillsTabGui) tab).skills;
                                if (tabSkills.containsKey(skill)) {
                                    HamonSkillElementLearnable skillElement = tabSkills.get(skill);
                                    screen.selectTab(tab);
                                    ((HamonSkillsTabGui) tab).selectSkill(skillElement);
                                    screen.clickedOnSkill = true;
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                })) {
                    return true;
                }
            }
        }
        
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    boolean mouseReleased(double mouseX, double mouseY, int mouseButton) {
        return isLocked() ? false : super.mouseReleased(mouseX, mouseY, mouseButton);
    }
    
    @Override
    void scroll(double xMovement, double yMovement) {
        if (!isLocked()) {
            super.scroll(xMovement, yMovement);
        }
    }
    
    @Override
    void drawToolTips(MatrixStack matrixStack, int mouseX, int mouseY, int windowPosX, int windowPosY) {
        if (!isLocked()) {
            super.drawToolTips(matrixStack, mouseX, mouseY, windowPosX, windowPosY);
            availableHamonTechniques.forEach(technique -> technique.drawTooltip(
                    screen, matrixStack, intScrollX, intScrollY, mouseX, mouseY));
            
            for (HamonTechniqueSlotElement skillSlot : techniqueSkillSlots) {
                skillSlot.getSkillElement().ifPresent(skill -> {
                    if (skill.isMouseOver(intScrollX, intScrollY, mouseX, mouseY)) {
                        skill.drawTooltip(screen, matrixStack, mouseX, mouseY);
                    }
                });
            }
        }
    }

    @Override
    void updateTab() {
        if (!isLocked()) {
            super.updateTab();
            CharacterHamonTechnique newTechnique = screen.hamon.getCharacterTechnique();
            this.technique = newTechnique;
            fillSkillLines();
            updateButton();
            

//            for (HamonSkillElementLearnable skillElement : skills.values()) {
//                skillElement.updateState(screen.hamon, minecraft.player, screen.teacherSkills);
//            }
        }
    }

    
    private static final Comparator<CharacterHamonTechnique> TECHNIQUES_ORDER = (technique1, technique2) -> {
        int t1 = indexOfTechnique(technique1);
        int t2 = indexOfTechnique(technique2);
        // one is from base mod, other isn't, base mod techniques are first
        if (t1 > -1 ^ t2 > -1) {
            return t1 > -1 ? -1 : 1;
        }
        else {
            // specific order for base mod techniques
            if (t1 > -1 /*&& t2 > -1*/) {
                return t1 - t2;
            }
            // the rest are in alphabetical order
            else /*if (t1 == -1 && t2 == -1)*/ {
                return technique1.getRegistryName().toString().compareTo(technique2.getRegistryName().toString());
            }
        }
    };
    private static final List<Supplier<CharacterHamonTechnique>> MOD_TECHNIQUES_ORDER = Util.make(new ArrayList<>(), list -> {
        list.add(ModHamonSkills.CHARACTER_JONATHAN);
        list.add(ModHamonSkills.CHARACTER_ZEPPELI);
        list.add(ModHamonSkills.CHARACTER_JOSEPH);
        list.add(ModHamonSkills.CHARACTER_CAESAR);
        list.add(ModHamonSkills.CHARACTER_LISA_LISA);
    });
    private static List<CharacterHamonTechnique> MOD_TECHNIQUES_REGISTERED = null;
    private static int indexOfTechnique(CharacterHamonTechnique technique) {
        if (MOD_TECHNIQUES_REGISTERED == null) {
            MOD_TECHNIQUES_REGISTERED = Collections.unmodifiableList(MOD_TECHNIQUES_ORDER.stream().map(Supplier::get).collect(Collectors.toList()));
        }
        int index = MOD_TECHNIQUES_REGISTERED.indexOf(technique);
        return index;
    }
}
