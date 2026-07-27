package com.gabrieltoth.velocityhud.fabric;

import com.gabrieltoth.velocityhud.VelocityHUD;
import net.fabricmc.api.ModInitializer;

public class VelocityHUDFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        VelocityHUD.init();
    }
}
