package com.gabrieltoth.velocityhud.hud;

public interface HUDRenderer {
    
    void render(double velocity, int screenWidth, int screenHeight);
    
    void renderWithBackground(double velocity, int screenWidth, int screenHeight, int backgroundColor);
    
    int getHUDWidth(String text);
    
    int getHUDHeight();
    
    void setScale(float scale);
    
    void setColor(int color);
}
