package com.gabrieltoth.velocityhud.velocity;

public class VelocityCalculator {
    
    public enum Unit {
        BLOCKS_PER_SECOND,
        KILOMETERS_PER_HOUR,
        MILES_PER_HOUR,
        METERS_PER_SECOND
    }
    
    private static final double BLOCKS_TO_METERS = 1.0;
    private static final double METERS_TO_KM = 0.001;
    private static final double METERS_TO_MILES = 0.000621371;
    private static final double SECONDS_TO_HOURS = 3600.0;
    
    public static double calculate(double deltaX, double deltaY, double deltaZ, double deltaTime, Unit unit) {
        if (deltaTime <= 0) {
            return 0.0;
        }
        
        double distanceBlocks = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
        double blocksPerSecond = distanceBlocks / deltaTime;
        
        return convert(blocksPerSecond, Unit.BLOCKS_PER_SECOND, unit);
    }
    
    public static double calculateHorizontal(double deltaX, double deltaZ, double deltaTime, Unit unit) {
        if (deltaTime <= 0) {
            return 0.0;
        }
        
        double distanceBlocks = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        double blocksPerSecond = distanceBlocks / deltaTime;
        
        return convert(blocksPerSecond, Unit.BLOCKS_PER_SECOND, unit);
    }
    
    public static double convert(double value, Unit from, Unit to) {
        if (from == to) {
            return value;
        }
        
        double metersPerSecond = toMetersPerSecond(value, from);
        return fromMetersPerSecond(metersPerSecond, to);
    }
    
    private static double toMetersPerSecond(double value, Unit unit) {
        return switch (unit) {
            case BLOCKS_PER_SECOND -> value * BLOCKS_TO_METERS;
            case KILOMETERS_PER_HOUR -> value / 3.6;
            case MILES_PER_HOUR -> value / 2.236936;
            case METERS_PER_SECOND -> value;
        };
    }
    
    private static double fromMetersPerSecond(double metersPerSecond, Unit unit) {
        return switch (unit) {
            case BLOCKS_PER_SECOND -> metersPerSecond / BLOCKS_TO_METERS;
            case KILOMETERS_PER_HOUR -> metersPerSecond * 3.6;
            case MILES_PER_HOUR -> metersPerSecond * 2.236936;
            case METERS_PER_SECOND -> metersPerSecond;
        };
    }
    
    public static String format(double value, Unit unit, int decimalPlaces) {
        String formatted = String.format(java.util.Locale.ROOT, "%." + decimalPlaces + "f", value);
        String unitSuffix = switch (unit) {
            case BLOCKS_PER_SECOND -> " b/s";
            case KILOMETERS_PER_HOUR -> " km/h";
            case MILES_PER_HOUR -> " mph";
            case METERS_PER_SECOND -> " m/s";
        };
        return formatted + unitSuffix;
    }
}
