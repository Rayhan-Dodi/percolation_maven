package com.eternasky;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PercolationStatsTest {
    @Test
    void testStats() {
        PercolationStats stats = new PercolationStats(5, 10);
        assertTrue(stats.mean() > 0);
    }
}
