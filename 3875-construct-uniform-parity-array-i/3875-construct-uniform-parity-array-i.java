class Solution {
    public boolean uniformArray(int[] nums1) {
        int oddCount = 0;
        int evenCount = 0;
        for (int num : nums1) {
            if (num % 2 != 0) {
                oddCount++;
            } else {
                evenCount++;
            }
        }
        boolean canBeAllEven = (oddCount == 0) || (oddCount >= 2);
        boolean canBeAllOdd = (evenCount == 0) || (oddCount >= 1);
        return canBeAllEven || canBeAllOdd;
    }
}