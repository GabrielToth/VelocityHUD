package com.gabrieltoth.velocityhud.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {
    
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    
    private final Path configPath;
    private Config config;
    
    public ConfigManager(Path configDir) {
        this.configPath = configDir.resolve("velocityhud.json");
        this.config = load();
    }
    
    public Config getConfig() {
        return config;
    }
    
    public void save() {
        try {
            Files.createDirectories(configPath.getParent());
            String json = GSON.toJson(config);
            Files.writeString(configPath, json);
        } catch (IOException e) {
            System.err.println("Failed to save VelocityHUD config: " + e.getMessage());
        }
    }
    
    public Config load() {
        if (!Files.exists(configPath)) {
            Config defaultConfig = Config.getDefault();
            this.config = defaultConfig;
            save();
            return defaultConfig;
        }
        
        try {
            String json = Files.readString(configPath);
            Config loaded = GSON.fromJson(json, Config.class);
            if (loaded == null) {
                loaded = Config.getDefault();
            }
            this.config = loaded;
            return loaded;
        } catch (IOException e) {
            System.err.println("Failed to load VelocityHUD config, using defaults: " + e.getMessage());
            Config defaultConfig = Config.getDefault();
            this.config = defaultConfig;
            return defaultConfig;
        }
    }
    
    public void reload() {
        this.config = load();
    }
}
