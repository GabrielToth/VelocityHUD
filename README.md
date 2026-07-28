# VelocityHUD

[![Build & Test](https://github.com/GabrielToth/VelocityHUD/actions/workflows/build.yml/badge.svg)](https://github.com/GabrielToth/VelocityHUD/actions/workflows/build.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Minecraft Support](https://img.shields.io/badge/Minecraft-1.20.1-brightgreen.svg)](https://minecraft.net)
[![Loaders](https://img.shields.io/badge/Loaders-Fabric%20%7C%20Forge%20%7C%20Quilt%20%7C%20NeoForge-blue.svg)](#multi-loader-support)

A clean, lightweight, highly customizable Minecraft mod that displays real-time movement velocity on your HUD.

---

## Features

- ⚡ **Real-Time Velocity Display**: Accurate speed tracking in blocks per second, km/h, mph, or m/s.
- 📐 **3D & 2D Modes**: Choose between total 3D speed or horizontal-only (2D) speed.
- 🎨 **Fully Customizable HUD**: Adjust position (9 screen anchors), text color, scale, background opacity, and padding.
- ⌨️ **In-Game Toggle Keybind**: Press **`V`** to toggle HUD visibility anytime.
- 💾 **Auto-Saving Config**: Automatically saves and loads settings from `config/velocityhud.json`.
- 🔌 **Universal Multi-Loader**: Native support for **Fabric**, **Forge**, **Quilt**, and **NeoForge**.

---

## Multi-Loader Support

| Loader | Status | Build Output |
|---|---|---|
| **Fabric** | ✅ Supported | `velocityhud-fabric-1.0.0.jar` |
| **Forge** | ✅ Supported | `velocityhud-forge-1.0.0.jar` |
| **Quilt** | ✅ Supported | `velocityhud-quilt-1.0.0.jar` |
| **NeoForge** | ✅ Supported | `velocityhud-neoforge-1.0.0.jar` |

---

## Building from Source

See [docs/BUILDING.md](docs/BUILDING.md) for detailed instructions.

```bash
# Build all loader JARs at once
.\gradlew.bat build
```

---

## Architecture

See [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) for an overview of the clean multi-loader architecture.

---

## License

Distributed under the [MIT License](LICENSE). Copyright © 2026 GabrielToth.
