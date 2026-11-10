import java.util.Arrays;

class Solution {
    public int climbStairs(int n, int[] costs) {
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE / 2);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int stepCost = costs[i - 1];
            for (int jump = 1; jump <= 3; jump++) {
                if (i - jump >= 0) {
                    long jumpCost = (long) jump * jump;
                    dp[i] = Math.min(dp[i], dp[i - jump] + stepCost + jumpCost);
                }
            }
        }
        return (int) dp[n];
    }
}