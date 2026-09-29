class Solution {
    private Boolean[][][] memo;
    private int m, n;
    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        if ((m + n - 1) % 2 != 0) {
            return false;
        }
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        int maxBalance = (m + n) / 2;
        memo = new Boolean[m][n][maxBalance + 1];
        return dfs(grid, 0, 0, 0);
    }
    private boolean dfs(char[][] grid, int r, int c, int balance) {
        balance += (grid[r][c] == '(' ? 1 : -1);
        if (balance < 0) {
            return false;
        }
        int remainingSteps = (m - 1 - r) + (n - 1 - c);
        if (balance > remainingSteps) {
            return false;
        }
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }
        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }
        boolean canReach = false;
        if (r + 1 < m) {
            canReach = dfs(grid, r + 1, c, balance);
        }
        if (!canReach && c + 1 < n) {
            canReach = dfs(grid, r, c + 1, balance);
        }
        return memo[r][c][balance] = canReach;
    }
}