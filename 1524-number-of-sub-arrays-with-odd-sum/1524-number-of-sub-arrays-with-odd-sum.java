class Solution {
    private static final int MOD = 1_000_000_007;

    public int numOfSubarrays(int[] arr) {
        long totalSubarrays = 0;
        int evenCount = 1;
        int oddCount = 0;
        int prefixParity = 0;

        for (int num : arr) {
            prefixParity ^= (num & 1);

            if (prefixParity == 1) {
                totalSubarrays = (totalSubarrays + evenCount) % MOD;
                oddCount++;
            } else {
                totalSubarrays = (totalSubarrays + oddCount) % MOD;
                evenCount++;
            }
        }

        return (int) totalSubarrays;
    }
}