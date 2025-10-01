package com.eternasky;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PercolationTest {
    @Test
    void testSingleSite() {
        Percolation p = new Percolation(1);
        assertFalse(p.percolates());
        p.open(1,1);
        assertTrue(p.percolates());
    }
}
