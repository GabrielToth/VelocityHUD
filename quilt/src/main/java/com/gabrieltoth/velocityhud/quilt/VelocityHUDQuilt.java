package com.gabrieltoth.velocityhud.quilt;

import com.gabrieltoth.velocityhud.config.ConfigManager;
import com.gabrieltoth.velocityhud.velocity.VelocityTracker;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

import java.nio.file.Path;

public class VelocityHUDQuilt implements ClientModInitializer {

    private static VelocityHUDQuilt instance;
    private ConfigManager configManager;
    private final VelocityTracker velocityTracker = new VelocityTracker();
    private QuiltHUDRenderer hudRenderer;

    @Override
    public void onInitializeClient() {
        instance = this;

        Path configDir = Minecraft.getInstance().gameDirectory.toPath().resolve("config");
        this.configManager = new ConfigManager(configDir);
        this.hudRenderer = new QuiltHUDRenderer(configManager);

        QuiltKeybinds.register();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (QuiltKeybinds.isTogglePressed()) {
                boolean current = configManager.getConfig().getGeneral().enabled;
                configManager.getConfig().getGeneral().enabled = !current;
                configManager.save();
            }

            LocalPlayer player = client.player;
            if (player != null && !client.isPaused()) {
                velocityTracker.update(player.getX(), player.getY(), player.getZ());
            } else if (player == null) {
                velocityTracker.reset();
            }
        });

        HudRenderCallback.EVENT.register((guiGraphics, tickCounter) -> {
            Minecraft client = Minecraft.getInstance();
            if (client.options.hideGui || client.player == null) {
                return;
            }

            if (!configManager.getConfig().getGeneral().enabled) {
                return;
            }

            double velocity = configManager.getConfig().getVelocity().showHorizontalOnly
                    ? velocityTracker.getCurrentHorizontalVelocity(configManager.getConfig().getVelocity().unit)
                    : velocityTracker.getCurrentVelocity(configManager.getConfig().getVelocity().unit);

            int screenWidth = client.getWindow().getGuiScaledWidth();
            int screenHeight = client.getWindow().getGuiScaledHeight();

            hudRenderer.renderHUD(guiGraphics, velocity, screenWidth, screenHeight);
        });
    }

    public static VelocityHUDQuilt getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public VelocityTracker getVelocityTracker() {
        return velocityTracker;
    }
}
