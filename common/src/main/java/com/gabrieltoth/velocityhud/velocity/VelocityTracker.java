package com.gabrieltoth.velocityhud.velocity;

public class VelocityTracker {
    
    private double lastX;
    private double lastY;
    private double lastZ;
    private long lastTime;
    private boolean initialized = false;
    
    private double currentVelocity = 0.0;
    private double currentHorizontalVelocity = 0.0;
    
    public void update(double x, double y, double z) {
        long currentTime = System.currentTimeMillis();
        
        if (!initialized) {
            lastX = x;
            lastY = y;
            lastZ = z;
            lastTime = currentTime;
            initialized = true;
            return;
        }
        
        double deltaX = x - lastX;
        double deltaY = y - lastY;
        double deltaZ = z - lastZ;
        double deltaTime = (currentTime - lastTime) / 1000.0;
        
        currentVelocity = VelocityCalculator.calculate(deltaX, deltaY, deltaZ, deltaTime, VelocityCalculator.Unit.BLOCKS_PER_SECOND);
        currentHorizontalVelocity = VelocityCalculator.calculateHorizontal(deltaX, deltaZ, deltaTime, VelocityCalculator.Unit.BLOCKS_PER_SECOND);
        
        lastX = x;
        lastY = y;
        lastZ = z;
        lastTime = currentTime;
    }
    
    public double getCurrentVelocity() {
        return currentVelocity;
    }
    
    public double getCurrentHorizontalVelocity() {
        return currentHorizontalVelocity;
    }
    
    public double getCurrentVelocity(VelocityCalculator.Unit unit) {
        return VelocityCalculator.convert(currentVelocity, VelocityCalculator.Unit.BLOCKS_PER_SECOND, unit);
    }
    
    public double getCurrentHorizontalVelocity(VelocityCalculator.Unit unit) {
        return VelocityCalculator.convert(currentHorizontalVelocity, VelocityCalculator.Unit.BLOCKS_PER_SECOND, unit);
    }
    
    public void reset() {
        initialized = false;
        currentVelocity = 0.0;
        currentHorizontalVelocity = 0.0;
    }
    
    public boolean isInitialized() {
        return initialized;
    }
}
