class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int totalSum = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k != 0)
                continue;

            if (canSortWithK(nums, n, k)) {
                totalSum += k;
            }
        }
        return totalSum;
    }

    private boolean canSortWithK(int[] nums, int n, int k) {
        int prevMax = Integer.MIN_VALUE;
        for (int start = 0; start < n; start += k) {
            int drops = 0;
            int minVal = Integer.MAX_VALUE;
            int maxVal = Integer.MIN_VALUE;

            for (int i = 0; i < k; i ++) {
                int curr = nums[start + i];
                int next = nums[start + (i + 1) % k];

                if (curr > next) {
                    drops++;
                }

                minVal = Math.min(minVal, curr);
                maxVal = Math.max(maxVal, curr);
            }

            if (drops > 1) {
                return false;
            }
            if (minVal < prevMax) {
                return false;
            }
            prevMax = maxVal;
        }
        return true;
    }
}