import java.util.Arrays;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (grid[0][0] == 1) return 0;
        if (m == 1 && n == 1) return 1;

        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (r == m - 1 && c == n - 1) continue;
                if (grid[r][c] == 1) continue;
                long ways = 0;
                int[] nextRight = simulate(grid, r, c + 1, 0, m, n);
                if (nextRight != null) {
                    ways = (ways + dp[nextRight[0]][nextRight[1]]) % MOD;
                }
                int[] nextDown = simulate(grid, r + 1, c, 1, m, n);
                if (nextDown != null) {
                    ways = (ways + dp[nextDown[0]][nextDown[1]]) % MOD;
                }
                dp[r][c] = (int) ways;
            }
        }
        return dp[0][0];
    }
    private int[] simulate(int[][] grid, int r, int c, int dir, int m, int n) {
        int currR = r;
        int currC = c;
        int currDir = dir;

        while (true) {
            if (currR < 0 || currR >= m || currC < 0 || currC >= n) {
                return null;
            }
            if (currR == m - 1 && currC == n - 1) {
                return new int[]{currR, currC};
            }
            if (grid[currR][currC] == 0) {
                return new int[]{currR, currC};
            }
            if (currDir == 0) {
                currR = currR + 1;
                currDir = 1;
            } else {
                currC = currC + 1;
                currDir = 0;
            }
        }
    }
}