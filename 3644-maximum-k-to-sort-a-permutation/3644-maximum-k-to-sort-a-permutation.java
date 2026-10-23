class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int ans = -1;

        boolean isSorted = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                isSorted = false;
                if (ans == -1) {
                    ans = i;
                } else {
                    ans &= i;
                }
            }
        }
        return isSorted ? 0 : ans;
    }
}