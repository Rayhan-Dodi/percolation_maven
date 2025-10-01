package com.eternasky;

import java.util.concurrent.ThreadLocalRandom;

public class PercolationStats {
    private final double[] thresholds;
    private final int trials;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n and trials must be > 0");

        this.trials = trials;
        thresholds = new double[trials];

        for (int t = 0; t < trials; t++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = ThreadLocalRandom.current().nextInt(1, n + 1);
                int col = ThreadLocalRandom.current().nextInt(1, n + 1);
                perc.open(row, col);
            }
            thresholds[t] = (double) perc.numberOfOpenSites() / (n * n);
        }
    }

    public double mean() {
        return java.util.Arrays.stream(thresholds).average().orElse(Double.NaN);
    }

    public double stddev() {
        double mean = mean();
        return Math.sqrt(java.util.Arrays.stream(thresholds)
                .map(x -> (x - mean) * (x - mean))
                .average().orElse(Double.NaN));
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(trials));
    }
}
