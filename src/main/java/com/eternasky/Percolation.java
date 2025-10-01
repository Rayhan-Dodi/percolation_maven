package com.eternasky;

public class Percolation {
    private final int n;
    private final boolean[] openSites;
    private final WeightedQuickUnionUF uf;
    private final int virtualTop;
    private final int virtualBottom;
    private int openCount = 0;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Grid size must be > 0");
        this.n = n;
        openSites = new boolean[n * n];
        uf = new WeightedQuickUnionUF(n * n + 2);
        virtualTop = n * n;
        virtualBottom = n * n + 1;
    }

    private int index(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("Row and column must be between 1 and " + n);
        return (row - 1) * n + (col - 1);
    }

    public void open(int row, int col) {
        int idx = index(row, col);
        if (openSites[idx]) return;
        openSites[idx] = true;
        openCount++;

        if (row == 1) uf.union(idx, virtualTop);
        if (row == n) uf.union(idx, virtualBottom);
        if (row > 1 && isOpen(row - 1, col)) uf.union(idx, index(row - 1, col));
        if (row < n && isOpen(row + 1, col)) uf.union(idx, index(row + 1, col));
        if (col > 1 && isOpen(row, col - 1)) uf.union(idx, index(row, col - 1));
        if (col < n && isOpen(row, col + 1)) uf.union(idx, index(row, col + 1));
    }

    public boolean isOpen(int row, int col) {
        return openSites[index(row, col)];
    }

    public boolean isFull(int row, int col) {
        return uf.connected(index(row, col), virtualTop);
    }

    public boolean percolates() {
        return uf.connected(virtualTop, virtualBottom);
    }

    public int numberOfOpenSites() {
        return openCount;
    }
}
