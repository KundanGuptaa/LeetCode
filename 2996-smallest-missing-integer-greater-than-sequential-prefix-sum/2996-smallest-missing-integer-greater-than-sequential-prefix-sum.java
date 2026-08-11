class Solution {
    public int missingInteger(int[] nums) {

        int len = 51;
        boolean[] seen = new boolean[len];

        int sum = nums[0];
        int idx = 1;

        while (idx < nums.length && nums[idx] == nums[idx - 1] + 1) {
            sum += nums[idx];
            idx++;
        }

        for (int num : nums) {
            seen[num] = true;
        }

        for (int num = sum; num < len; num++) {
            if (!seen[num]) {
                return num;
            }
        }

        return sum < len ? len : sum;
    }
}