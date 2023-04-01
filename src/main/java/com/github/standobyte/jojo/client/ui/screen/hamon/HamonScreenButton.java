package com.github.standobyte.jojo.client.ui.screen.hamon;

import com.github.standobyte.jojo.client.ui.screen.CustomButton;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.util.text.ITextComponent;

public class HamonScreenButton extends CustomButton {
    private int yStarting;
    private boolean mouseInWindow;

    public HamonScreenButton(int x, int y, int width, int height, 
            ITextComponent message, IPressable onPress) {
        super(x, y, width, height, message, onPress);
        this.yStarting = y;
    }

    public HamonScreenButton(int x, int y, int width, int height, 
            ITextComponent message, IPressable onPress, ITooltip tooltip) {
        super(x, y, width, height, message, onPress, tooltip);
        this.yStarting = y;
    }
    
    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTick) {
        super.renderButton(matrixStack, mouseX, mouseY, partialTick);
        // FIXME ! (hamon) button text being rendered outside of the window
        drawName(matrixStack);
    }

    
    public void setMouseInWindow(boolean mouseInWindow) {
        this.mouseInWindow = mouseInWindow;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTick) {
        if (!mouseInWindow) mouseY = -1;
        super.render(matrixStack, mouseX, mouseY, partialTick);
    }
    
    public void setY(int y) {
        this.y = y;
        this.yStarting = y;
    }
    
    public void updateY(int scrollY) {
        this.y = this.yStarting + scrollY;
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        return mouseInWindow ? super.mouseClicked(mouseX, mouseY, mouseButton) : false;
    }
}
