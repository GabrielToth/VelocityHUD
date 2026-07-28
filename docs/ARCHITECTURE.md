# VelocityHUD Architecture

VelocityHUD is designed as a **multi-loader Minecraft mod** using a clean modular architecture.

## Overview

```
VelocityHUD/
├── common/             # Pure Java core domain & math (Loader-agnostic)
├── fabric/             # Fabric Loader implementation
├── forge/              # Forge Loader implementation
├── quilt/              # Quilt Loader implementation
└── neoforge/           # NeoForge Loader implementation
```

## Layer Separation

### 1. Common Layer (`:common`)
Contains zero loader-specific dependencies.
- **`VelocityCalculator`**: Math logic for calculating 3D and 2D horizontal speed and unit conversion (blocks/s, km/h, mph, m/s).
- **`VelocityTracker`**: High-frequency position tracker with delta time calculation.
- **`Config` / `ConfigManager`**: Data models and JSON configuration persistence using Gson.
- **`HUDRenderer`**: Abstract renderer interface that loader modules implement.

### 2. Loader Adaptors (`:fabric`, `:forge`, `:quilt`, `:neoforge`)
Implement loader-specific events, keybindings, and render calls using Mojang `GuiGraphics` and native Mod API tick callbacks.

- **Fabric**: `ClientModInitializer`, `HudRenderCallback`, `ClientTickEvents`, `KeyBindingHelper`.
- **Forge**: `@Mod`, `@SubscribeEvent`, `RenderGuiEvent.Post`, `TickEvent.ClientTickEvent`.
- **Quilt**: `ClientModInitializer`, Quilt Loom mapping pass-through.
- **NeoForge**: `@Mod`, `NeoForge.EVENT_BUS`, `RenderGuiEvent.Post`.

## Supported Units

| Unit | Suffix | Ratio |
|---|---|---|
| Blocks per Second | `b/s` | `1.0` |
| Kilometers per Hour | `km/h` | `3.6` |
| Miles per Hour | `mph` | `2.236936` |
| Meters per Second | `m/s` | `1.0` |
