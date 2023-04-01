package com.github.standobyte.jojo.client.ui.screen.hamon;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.github.standobyte.jojo.JojoMod;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.client.ClientUtil;
import com.github.standobyte.jojo.client.resources.CustomResources;
import com.github.standobyte.jojo.network.PacketManager;
import com.github.standobyte.jojo.network.packets.fromclient.ClHamonLearnButtonPacket;
import com.github.standobyte.jojo.network.packets.fromclient.ClHamonResetSkillsButtonPacket;
import com.github.standobyte.jojo.network.packets.fromclient.ClHamonResetSkillsButtonPacket.HamonSkillsTab;
import com.github.standobyte.jojo.power.nonstand.type.hamon.skill.AbstractHamonSkill;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Streams;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

// FIXME (hamon skills) shorter skill decriptions for:
/*
 * rope trap
 * cheat death
 * bubble cutter
 * satiporoja beetle scarf
 * snake muffler
 */
@SuppressWarnings("deprecation")
public abstract class HamonSkillsTabGui extends HamonTabGui {
    public static final ResourceLocation HAMON_SKILLS = new ResourceLocation(JojoMod.MOD_ID, "textures/gui/hamon_window_2.png");
    
    private final List<IReorderingProcessor> creativeResetButtonTooltip;
    protected final Map<AbstractHamonSkill, HamonSkillElementLearnable> skills = new HashMap<>();
    protected HamonScreenButton learnButton;
    protected HamonScreenButton creativeResetButton;
    @Nullable private HamonSkillElementLearnable selectedSkill = null;
    protected List<HamonSkillGuiElement> skillRequirements = Collections.emptyList();
    protected List<IReorderingProcessor> skillClosedReason = Collections.emptyList();
    @Nullable private HamonSkillElementLearnable lastClickedSkill = null;
    private int lastClickDelay;
    
    HamonSkillsTabGui(Minecraft minecraft, HamonScreen screen, int index, String title, int scrollWidth, int scrollHeight) {
        super(minecraft, screen, index, title, scrollWidth, scrollHeight);
        creativeResetButtonTooltip = minecraft.font.split(new TranslationTextComponent("hamon.reset_creative_only"), 100);
    }

    @Override
    void addButtons() {
        learnButton = new HamonScreenButton(screen.windowPosX() + 150, screen.windowPosY() + 82, 64, 20, new TranslationTextComponent("hamon.learnButton"), button -> {
            if (selectedSkill != null) {
                PacketManager.sendToServer(ClHamonLearnButtonPacket.learnNewSkill(selectedSkill.getHamonSkill()));
                screen.clickedOnSkill = true;
            }
        });
        screen.addButton(learnButton);

        creativeResetButton = new HamonScreenButton(screen.windowPosX() + 16, screen.windowPosY() + 85, 64, 20, new TranslationTextComponent("hamon.resetButton"), button -> {
            PacketManager.sendToServer(new ClHamonResetSkillsButtonPacket(getSkillsType()));
        });
        screen.addButton(creativeResetButton);
    }
    
    protected abstract HamonSkillsTab getSkillsType();
    
    @Override
    List<HamonScreenButton> getButtons() {
        return ImmutableList.of(learnButton, creativeResetButton);
    }
    
    protected boolean isLocked() {
        return false;
    }

    @Override
    protected void drawActualContents(MatrixStack matrixStack, int mouseX, int mouseY) {
        renderSkillTrees(matrixStack, mouseX, mouseY);
        renderSelectedSkillRequirements(matrixStack, mouseX, mouseY);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
    }
    
    protected void renderSkillTrees(MatrixStack matrixStack, int mouseX, int mouseY) {
        // skill squares
        this.minecraft.getTextureManager().bind(HAMON_SKILLS);
        for (HamonSkillElementLearnable skillElement : skills.values()) {
            skillElement.blitBgSquare(matrixStack, intScrollX, intScrollY);
        }
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        // selected skill
        if (selectedSkill != null) {
            selectedSkill.blitBgSquareSelection(matrixStack, intScrollX, intScrollY);
            renderHamonSkillIcon(matrixStack, selectedSkill.getHamonSkill(), intScrollX + 4, intScrollY + 4);
        }
        // skill icons
        for (HamonSkillGuiElement skillElement : skills.values()) {
            skillElement.renderSkillIcon(matrixStack, intScrollX + 5, intScrollY + 5);
        }
    }
    
    protected void renderSelectedSkillRequirements(MatrixStack matrixStack, int mouseX, int mouseY) {
        for (HamonSkillGuiElement requirement : skillRequirements) {
            boolean mouseOver = requirement.isMouseOver(intScrollX, intScrollY, mouseX, mouseY);
            boolean learned = screen.hamon.isSkillLearned(requirement.getHamonSkill());
            if (mouseOver) {
                RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            }
            else if (!learned) {
                RenderSystem.color4f(0.0F, 0.0F, 0.0F, 1.0F);
            }
            else {
                RenderSystem.color4f(1.0F, 1.0F, 1.0F, 0.4F);
            }
            requirement.renderSkillIcon(matrixStack, intScrollX, intScrollY);
        }
    }
    
    public static void renderHamonSkillIcon(MatrixStack matrixStack, AbstractHamonSkill skill, int x, int y) {
        TextureAtlasSprite textureAtlasSprite = CustomResources.getHamonSkillSprites().getSprite(skill);
        Minecraft.getInstance().getTextureManager().bind(textureAtlasSprite.atlas().location());
        blit(matrixStack, x, y, 0, 16, 16, textureAtlasSprite);
    }
    
    @Override
    protected void drawDesc(MatrixStack matrixStack) {
        if (selectedSkill != null) {
            drawSkillDesc(matrixStack);
        }
        else {
            super.drawDesc(matrixStack);
        }
    }
    
    protected void drawSkillDesc(MatrixStack matrixStack) {
        drawString(matrixStack, minecraft.font, selectedSkill.getName(), intScrollX + 22, intScrollY + 5, 0xFFFFFF);
        ClientUtil.drawRightAlignedString(matrixStack, minecraft.font, 
                selectedSkill.getHamonSkill().getRewardType().getName(), 
                intScrollX + 205, intScrollY + 5, 0xFFFFFF);
        for (int i = 0; i < selectedSkill.getDescription().size(); i++) {
            minecraft.font.draw(matrixStack, selectedSkill.getDescription().get(i), intScrollX + 6, intScrollY + 22 + i * 9, 0xFFFFFF);
        }
        if (learnButton.visible && !learnButton.active) {
            for (int i = 0; i < skillClosedReason.size(); i++) {
                ClientUtil.drawRightAlignedString(matrixStack, minecraft.font, skillClosedReason.get(i), intScrollX + 135, intScrollY + 59 + i * 9, 0xFF0000);
            }
        }
    }
    
    @Override
    void drawToolTips(MatrixStack matrixStack, int mouseX, int mouseY, int windowPosX, int windowPosY) {
        for (HamonSkillGuiElement skill : skills.values()) {
            if (skill.isMouseOver(intScrollX, intScrollY, mouseX, mouseY)) {
                skill.drawTooltip(screen, matrixStack, mouseX, mouseY);
            }
        }
        for (HamonSkillGuiElement skill : skillRequirements) {
            if (skill.isMouseOver(intScrollX, intScrollY, mouseX, mouseY)) {
                skill.drawTooltip(screen, matrixStack, mouseX, mouseY);
            }
        }
        if (creativeResetButton.visible && creativeResetButton.isMouseOver(
                mouseX + screen.windowPosX() + HamonScreen.WINDOW_THIN_BORDER, 
                mouseY + screen.windowPosY() + HamonScreen.WINDOW_UPPER_BORDER)) {
            screen.renderTooltip(matrixStack, creativeResetButtonTooltip, mouseX, mouseY);
        }
    }

    @Override
    boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            for (HamonSkillElementLearnable skill : skills.values()) {
                if (skill.isMouseOver(intScrollX, intScrollY, (int) mouseX, (int) mouseY)) {
                    selectSkill(skill);
                    screen.clickedOnSkill = true;
                    
                    if (this.lastClickDelay < 7 && this.lastClickedSkill == skill && learnButton.active && learnButton.visible) {
                        learnButton.onPress();
                    }
                    this.lastClickDelay = 0;
                    this.lastClickedSkill = skill;
                    
                    return true;
                }
            }
            for (HamonSkillGuiElement requirement : skillRequirements) {
                if (requirement.isMouseOver(intScrollX, intScrollY, (int) mouseX, (int) mouseY)) {
                    AbstractHamonSkill skill = requirement.getHamonSkill();
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
            }
        }
        return false;
    }
    
    @Override
    void tick() {
        lastClickDelay++;
    }

    @Override
    boolean mouseReleased(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 1 || !screen.clickedOnSkill && !screen.isDragging() && mouseButton == 0 && 
                !learnButton.isMouseOver(mouseX + screen.windowPosX() + HamonScreen.WINDOW_THIN_BORDER, mouseY + screen.windowPosY() + HamonScreen.WINDOW_UPPER_BORDER)) {
            selectSkill(null);
            return true;
        }
        return false;
    }
    
    protected void selectSkill(@Nullable HamonSkillElementLearnable guiElement) {
        selectedSkill = guiElement;
        if (guiElement != null) {
            scrollX = 0;
            scrollY = 0;
        }
        updateButton();
    }
    
    @Nullable protected HamonSkillElementLearnable getSelectedSkill() {
        return selectedSkill;
    }
    
    @Override
    protected void updateButton() {
        learnButton.visible = false;
        learnButton.active = false;
        if (selectedSkill != null) {
            learnButton.visible = !screen.hamon.isSkillLearned(selectedSkill.getHamonSkill());
            ActionConditionResult canLearnSkill = screen.hamon.canLearnSkill(minecraft.player, selectedSkill.getHamonSkill(), screen.teacherSkills);
            skillClosedReason = canLearnSkill.getWarning() != null ? minecraft.font.split(canLearnSkill.getWarning(), 100) : Collections.emptyList();
            learnButton.active = canLearnSkill.isPositive();
        }
        creativeResetButton.visible = selectedSkill == null && screen.getMinecraft().player.abilities.instabuild;
        
        skillRequirements = selectedSkill != null ? 
                Streams.mapWithIndex(selectedSkill.skill.getRequiredSkills(), 
                        (skill, i) -> new HamonSkillElementRequirement(skill, (int) i * 20 + 6, 66))
                .collect(Collectors.toList())
                : Collections.emptyList();
    }
    
    @Override
    void updateTab() {
        if (selectedSkill != null) {
            updateButton();
        }
        for (HamonSkillElementLearnable skillElement : skills.values()) {
            skillElement.updateState(screen.hamon, minecraft.player, screen.teacherSkills);
        }
    }
}
