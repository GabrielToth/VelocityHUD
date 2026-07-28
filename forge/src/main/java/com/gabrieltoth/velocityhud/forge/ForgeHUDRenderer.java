package com.gabrieltoth.velocityhud.forge;

import com.gabrieltoth.velocityhud.config.Config;
import com.gabrieltoth.velocityhud.config.ConfigManager;
import com.gabrieltoth.velocityhud.hud.HUDPosition;
import com.gabrieltoth.velocityhud.hud.HUDRenderer;
import com.gabrieltoth.velocityhud.velocity.VelocityCalculator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public class ForgeHUDRenderer implements HUDRenderer {

    private final ConfigManager configManager;
    private float scale = 1.0f;
    private int color = 0xFFFFFF;

    public ForgeHUDRenderer(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void renderHUD(GuiGraphics guiGraphics, double velocity, int screenWidth, int screenHeight) {
        Config config = configManager.getConfig();
        String text = VelocityCalculator.format(
                velocity,
                config.getVelocity().unit,
                config.getDisplay().decimalPlaces
        );

        Font font = Minecraft.getInstance().font;
        int textWidth = font.width(text);
        int textHeight = font.lineHeight;

        HUDPosition position = config.getDisplay().position;
        int x = position.getX(screenWidth, textWidth, config.getDisplay().offsetX);
        int y = position.getY(screenHeight, textHeight, config.getDisplay().offsetY);

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(config.getDisplay().scale, config.getDisplay().scale, 1.0f);

        int scaledX = (int) (x / config.getDisplay().scale);
        int scaledY = (int) (y / config.getDisplay().scale);

        if (config.getDisplay().showBackground) {
            int padding = 2;
            guiGraphics.fill(
                    scaledX - padding,
                    scaledY - padding,
                    scaledX + textWidth + padding,
                    scaledY + textHeight + padding,
                    config.getDisplay().backgroundColor
            );
        }

        guiGraphics.drawString(font, text, scaledX, scaledY, config.getDisplay().color, true);

        guiGraphics.pose().popPose();
    }

    @Override
    public void render(double velocity, int screenWidth, int screenHeight) {
    }

    @Override
    public void renderWithBackground(double velocity, int screenWidth, int screenHeight, int backgroundColor) {
    }

    @Override
    public int getHUDWidth(String text) {
        return Minecraft.getInstance().font.width(text);
    }

    @Override
    public int getHUDHeight() {
        return Minecraft.getInstance().font.lineHeight;
    }

    @Override
    public void setScale(float scale) {
        this.scale = scale;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }
}
