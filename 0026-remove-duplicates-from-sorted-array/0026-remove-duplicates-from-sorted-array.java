class Solution {
    public int removeDuplicates(int[] nums) {
        int newIndex = 1;
        for (int i = 0; i < nums.length - 1; i++) {

            if (nums[i] < nums[i+1]) {
                nums[newIndex] = nums[i+1];
                newIndex++;
            }
        }
    return newIndex;
    }
}