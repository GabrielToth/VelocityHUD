# Building VelocityHUD

## Prerequisites

- **Java JDK**: 17 or higher (Eclipse Temurin / OpenJDK 17 recommended)
- **Git**

## Quick Build

To build JARs for all supported loaders (Fabric, Forge, Quilt, NeoForge) in one command:

```bash
# Linux / macOS
./gradlew build

# Windows PowerShell / CMD
.\gradlew.bat build
```

The compiled JAR files will be generated in:
- `fabric/build/libs/velocityhud-fabric-1.0.0.jar`
- `forge/build/libs/velocityhud-forge-1.0.0.jar`
- `quilt/build/libs/velocityhud-quilt-1.0.0.jar`
- `neoforge/build/libs/velocityhud-neoforge-1.0.0.jar`

## Module-Specific Builds

```bash
# Build Fabric only
.\gradlew.bat :fabric:build

# Build Forge only
.\gradlew.bat :forge:build

# Run Unit Tests
.\gradlew.bat :common:test
```
