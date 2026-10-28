class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        final int MAX_XOR = 1024;
        BitSet[][] dp = new BitSet[m][n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                dp[r][c] = new BitSet(MAX_XOR);
            }
        }
        dp[0][0].set(grid[0][0]);
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                BitSet current = dp[r][c];
                if (r + 1 < m) {
                    int nextVal = grid[r + 1][c];
                    for (int v = current.nextSetBit(0); v >= 0; v = current.nextSetBit(v + 1)) {
                        dp[r + 1][c].set(v ^ nextVal);
                    }
                }
                if (c + 1 < n) {
                    int nextVal = grid[r][c + 1];
                    for (int v = current.nextSetBit(0); v >= 0; v = current.nextSetBit(v + 1)) {
                        dp[r][c + 1].set(v ^ nextVal);
                    }
                }
            }
        }
        return dp[m - 1][n - 1].nextSetBit(0);
    }
}