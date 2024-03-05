/* *****************************************************************************
 *  Name:              Abby Kabalin
 *  Last modified:     February 22, 2024
 **************************************************************************** */

import java.lang.reflect.Array;
import java.util.Arrays;
import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] grid;
    private QuickFindUF quf;

    public Percolation(int N) {
        // create N-by-N grid, with all sites blocked
        grid = new int[N][N];
        quf = new QuickFindUF(N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = 1;
            }
        }
    }

    private boolean checkConnected(int row, int col, int row2, int col2) {
        while (isOpen(row, col) && isOpen(row2, col2) == true) {
            while (((row + 1) < (grid.length) && (row - 1 >= 0) && (col + 1 < (grid[row].length))
                    && (col - 1 >= 0) && (row2 + 1) < (grid.length) && (row2 - 1 >= 0)
                    && (col2 + 1 < (grid[row2].length)) && (col2 - 1 >= 0)) == true) {
                if (row == row2 && col == col2) {
                    quf.union(grid[row][col], grid[row2][col2]);
                    return true;

                }
                if (row == row2 && col == col2 - 1) {
                    quf.union(grid[row][col], grid[row2][col2]);
                    return true;

                }
                if (row == row2 && col == col2 + 1) {
                    quf.union(grid[row][col], grid[row2][col2]);
                    return true;

                }
                if (row == row2 - 1 && col == col2) {
                    quf.union(grid[row][col], grid[row2][col2]);
                    return true;

                }
                if (row == row2 + 1 && col == col2) {
                    quf.union(grid[row][col], grid[row2][col2]);
                    return true;

                }
                return false;
            }
            return false;
        }
        return false;
    }

    public void printPercolation() {
        for (int[] arr : grid) {
            for (int site : arr) {
                System.out.print(site + " ");
            }
            System.out.println();
        }
        System.out.println("Row Length: " + grid.length + " Col Length: " + grid[0].length);
    }

    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        if (grid[row][col] == 1) {
            grid[row][col] = 0;
        }
    }

    public boolean isOpen(int row, int col) {
        // is the site (row, col) open?
        if (grid[row][col] == 0) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (checkConnected(row, col, i, j) == false) {
                        return true;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean isFull(int row, int col) {
        // is the site (row, col) full?
        if (grid[row][col] == 1) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (checkConnected(row, col, i, j) == false) {
                        return false;
                    }
                }
                return false;
            }
        }
        return true;
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    count++;
                }
            }
        }
        return count; // number of open sites
    }

    public boolean percolates() {
        // does the system percolate?
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (quf.connected(grid[i][j], grid[grid.length - 1][grid.length - 1]) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // unit testing (suggested)
        int N = 5;
        Percolation perc = new Percolation(N);
        perc.open(0, 0);
        perc.open(1, 1);
        perc.open(2, 2);
        perc.open(3, 3);
        perc.open(4, 4);
        perc.printPercolation();
        System.out.println(perc.numberOfOpenSites());
        System.out.println(perc.percolates());

        int M = 4;
        Percolation perc2 = new Percolation(M);
        perc2.open(3, 0);
        perc2.open(2, 0);
        perc2.open(1, 0);
        perc2.open(0, 0);
        perc2.open(0, 3);
        perc2.open(1, 3);
        perc2.open(2, 3);
        perc2.open(3, 3);
        perc2.printPercolation();
        System.out.println(perc2.numberOfOpenSites());
        System.out.println(perc2.percolates());
    }
}