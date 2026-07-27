package com.gabrieltoth.velocityhud.velocity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VelocityCalculatorTest {
    
    @Test
    public void testZeroVelocity() {
        double velocity = VelocityCalculator.calculate(0, 0, 0, 1.0, VelocityCalculator.Unit.BLOCKS_PER_SECOND);
        assertEquals(0.0, velocity, 0.001);
    }
    
    @Test
    public void testLinearMovement() {
        // Moved 5 blocks in 1 second = 5.0 b/s
        double velocity = VelocityCalculator.calculate(5, 0, 0, 1.0, VelocityCalculator.Unit.BLOCKS_PER_SECOND);
        assertEquals(5.0, velocity, 0.001);
    }
    
    @Test
    public void testDiagonalMovement() {
        // Moved 3 blocks X, 4 blocks Z in 1 second = sqrt(3^2 + 4^2) = 5.0 b/s
        double velocity = VelocityCalculator.calculateHorizontal(3, 4, 1.0, VelocityCalculator.Unit.BLOCKS_PER_SECOND);
        assertEquals(5.0, velocity, 0.001);
    }
    
    @Test
    public void testUnitConversion() {
        // 1 block/s = 3.6 km/h
        double kmh = VelocityCalculator.convert(1.0, VelocityCalculator.Unit.BLOCKS_PER_SECOND, VelocityCalculator.Unit.KILOMETERS_PER_HOUR);
        assertEquals(3.6, kmh, 0.001);
    }
    
    @Test
    public void testFormat() {
        String formatted = VelocityCalculator.format(5.4321, VelocityCalculator.Unit.BLOCKS_PER_SECOND, 1);
        assertEquals("5.4 b/s", formatted);
        
        String formattedKmh = VelocityCalculator.format(18.0, VelocityCalculator.Unit.KILOMETERS_PER_HOUR, 2);
        assertEquals("18.00 km/h", formattedKmh);
    }
    
    @Test
    public void testZeroDeltaTime() {
        double velocity = VelocityCalculator.calculate(10, 10, 10, 0.0, VelocityCalculator.Unit.BLOCKS_PER_SECOND);
        assertEquals(0.0, velocity, 0.001);
    }
}
