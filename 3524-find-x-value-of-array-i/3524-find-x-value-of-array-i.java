class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] nextDp = new long[k];
            int rem = num % k;
            nextDp[rem] += 1;
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int nextRem = (r * rem) % k;
                    nextDp[nextRem] += dp[r];
                }
            }
            for (int r = 0; r < k; r++) {
                ans[r] += nextDp[r];
            }
            dp = nextDp;
        }
        return ans;
    }
}