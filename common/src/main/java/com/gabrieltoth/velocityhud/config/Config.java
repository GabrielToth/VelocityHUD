package com.gabrieltoth.velocityhud.config;

import com.gabrieltoth.velocityhud.hud.HUDPosition;
import com.gabrieltoth.velocityhud.velocity.VelocityCalculator;

public class Config {
    
    public static class Display {
        public HUDPosition position = HUDPosition.TOP_LEFT;
        public int offsetX = 5;
        public int offsetY = 5;
        public int color = 0xFFFFFF;
        public float scale = 1.0f;
        public boolean showBackground = true;
        public int backgroundColor = 0x80000000;
        public int decimalPlaces = 1;
    }
    
    public static class Velocity {
        public VelocityCalculator.Unit unit = VelocityCalculator.Unit.BLOCKS_PER_SECOND;
        public boolean showHorizontalOnly = false;
        public boolean showVertical = false;
        public int updateInterval = 50;
    }
    
    public static class General {
        public boolean enabled = true;
        public String keybindToggle = "key.keyboard.v";
    }
    
    private Display display = new Display();
    private Velocity velocity = new Velocity();
    private General general = new General();
    
    public Display getDisplay() {
        return display;
    }
    
    public void setDisplay(Display display) {
        this.display = display;
    }
    
    public Velocity getVelocity() {
        return velocity;
    }
    
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }
    
    public General getGeneral() {
        return general;
    }
    
    public void setGeneral(General general) {
        this.general = general;
    }
    
    public static Config getDefault() {
        return new Config();
    }
}
