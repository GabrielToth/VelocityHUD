package com.gabrieltoth.velocityhud.neoforge;

import com.gabrieltoth.velocityhud.config.ConfigManager;
import com.gabrieltoth.velocityhud.velocity.VelocityTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.nio.file.Path;

@Mod("velocityhud")
public class VelocityHUDNeoForge {

    private static VelocityHUDNeoForge instance;
    private ConfigManager configManager;
    private final VelocityTracker velocityTracker = new VelocityTracker();
    private NeoForgeHUDRenderer hudRenderer;

    public VelocityHUDNeoForge() {
        instance = this;
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        Path configDir = Minecraft.getInstance().gameDirectory.toPath().resolve("config");
        this.configManager = new ConfigManager(configDir);
        this.hudRenderer = new NeoForgeHUDRenderer(configManager);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        Minecraft client = Minecraft.getInstance();
        LocalPlayer player = client.player;

        if (player != null && !client.isPaused()) {
            if (NeoForgeKeybinds.TOGGLE_HUD_KEY.consumeClick()) {
                boolean current = configManager.getConfig().getGeneral().enabled;
                configManager.getConfig().getGeneral().enabled = !current;
                configManager.save();
            }

            velocityTracker.update(player.getX(), player.getY(), player.getZ());
        } else if (player == null) {
            velocityTracker.reset();
        }
    }

    @SubscribeEvent
    public void onRenderGui(RenderGuiEvent.Post event) {
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

        hudRenderer.renderHUD(event.getGuiGraphics(), velocity, screenWidth, screenHeight);
    }

    public static VelocityHUDNeoForge getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public VelocityTracker getVelocityTracker() {
        return velocityTracker;
    }
}
