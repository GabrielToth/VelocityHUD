package com.gabrieltoth.velocityhud.hud;

public enum HUDPosition {
    TOP_LEFT,
    TOP_CENTER,
    TOP_RIGHT,
    MIDDLE_LEFT,
    MIDDLE_CENTER,
    MIDDLE_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_CENTER,
    BOTTOM_RIGHT;
    
    public int getX(int screenWidth, int hudWidth, int offsetX) {
        return switch (this) {
            case TOP_LEFT, MIDDLE_LEFT, BOTTOM_LEFT -> offsetX;
            case TOP_CENTER, MIDDLE_CENTER, BOTTOM_CENTER -> (screenWidth - hudWidth) / 2 + offsetX;
            case TOP_RIGHT, MIDDLE_RIGHT, BOTTOM_RIGHT -> screenWidth - hudWidth - offsetX;
        };
    }
    
    public int getY(int screenHeight, int hudHeight, int offsetY) {
        return switch (this) {
            case TOP_LEFT, TOP_CENTER, TOP_RIGHT -> offsetY;
            case MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT -> (screenHeight - hudHeight) / 2 + offsetY;
            case BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT -> screenHeight - hudHeight - offsetY;
        };
    }
}
