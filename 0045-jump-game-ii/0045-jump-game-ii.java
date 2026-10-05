class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return 0;
        }

        int jumps = 0;
        int curEnd = 0;
        int maxReach = 0;

        for (int i = 0; i < n - 1; i++) {
            maxReach = Math.max(maxReach, i + nums[i]);

            // If we have reached the end of the current jump tier
            if (i == curEnd) {
                jumps++;
                curEnd = maxReach;

                // Optimization: stop early if we can already reach the end
                if (curEnd >= n - 1) {
                    break;
                }
            }
        }

        return jumps;
    }
}