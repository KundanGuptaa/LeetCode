class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // suffixMin[i] stores min(nums[i..n-1])
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }
        
        int prefixMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);
            long instabilityScore = (long) prefixMax - suffixMin[i];
            
            if (instabilityScore <= k) {
                return i;
            }
        }
        
        return -1;
    }
}