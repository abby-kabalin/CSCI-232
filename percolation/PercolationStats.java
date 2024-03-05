/* *****************************************************************************
 *  Name:              Abby Kabalin
 *  Last modified:     February 22, 2024
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    int trialNum;
    double[] results;
    int N;
    int T;

    public PercolationStats(int N, int T) {
        // perform T independent experiments on an N-by-N grid
        results = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation perc = new Percolation(N);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                perc.open(row, col);
            }

            int count = perc.numberOfOpenSites();
            results[i] = (double) count / (N * N);
            System.out.println("Trial " + i + " took " + results[i]);
        }

    }

    public double mean() {
        // sample mean of percolation threshold
        return StdStats.mean(results);
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        return StdStats.stddev(results);
    }

    public double confidenceLow() {
        // low endpoint of 95% confidence interval
        return mean() - (1.96 * stddev()) / Math.sqrt(T);
    }

    public double confidenceHigh() {
        // high endpoint of 95% confidence interval
        return mean() + (1.96 * stddev()) / Math.sqrt(T);
    }

    public static void main(String[] args) {
        // Not required for the API, but useful to test if
        // your code is doing reasonable things
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats st = new PercolationStats(N, T);
        StdOut.println("mean = " + st.mean());
        StdOut.println("stddev = " + st.stddev());
        StdOut.println("95% confidence interval = [" + st.confidenceLow() + ", " + st.confidenceHigh() + "]");

    }
}