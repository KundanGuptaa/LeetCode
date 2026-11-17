class Solution {
    private static final int INF = 1_000_000_000;

    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int len = 2; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d < len; d++) {
                if (len % d == 0) {
                    divisors[len].add(d);
                }
            }
        }
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], INF);
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int minChanges = INF;

                for (int d : divisors[len]) {
                    int changes = 0;
                    for (int offset = 0; offset < d; offset++) {
                        int left = i + offset;
                        int right = j - (d - 1 - offset);
                        while (left < right) {
                            if (chars[left] != chars[right]) {
                                changes++;
                            }
                            left += d;
                            right -= d;
                        }
                    }
                    minChanges = Math.min(minChanges, changes);
                }
                cost[i][j] = minChanges;
            }
        }
        int[][] dp = new int[k + 1][n];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        for (int j = 1; j < n; j++) {
            dp[1][j] = cost[0][j];
        }
        for (int p = 2; p <= k; p++) {
            for (int j = 2 * p - 1; j < n; j++) {
                for (int i = 2 * (p - 1) - 1; i <= j - 2; i++) {
                    if (dp[p - 1][i] != INF && cost[i + 1][j] != INF) {
                        dp[p][j] = Math.min(dp[p][j], dp[p - 1][i] + cost[i + 1][j]);
                    }
                }
            }
        }

        return dp[k][n - 1];
    }
}