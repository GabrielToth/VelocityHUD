package com.gabrieltoth.velocityhud.forge;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = "velocityhud", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeKeybinds {

    public static final KeyMapping TOGGLE_HUD_KEY = new KeyMapping(
            "key.velocityhud.toggle",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "category.velocityhud.general"
    );

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(TOGGLE_HUD_KEY);
    }
}
