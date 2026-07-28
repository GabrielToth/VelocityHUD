package com.gabrieltoth.velocityhud.quilt;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class QuiltKeybinds {

    private static KeyMapping toggleHudKey;

    public static void register() {
        toggleHudKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.velocityhud.toggle",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                "category.velocityhud.general"
        ));
    }

    public static boolean isTogglePressed() {
        if (toggleHudKey == null) {
            return false;
        }
        return toggleHudKey.consumeClick();
    }
}
