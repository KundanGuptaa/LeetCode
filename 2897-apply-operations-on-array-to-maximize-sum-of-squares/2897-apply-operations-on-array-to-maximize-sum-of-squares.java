class Solution {
    private static final int MOD = 1_000_000_007;

    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[31];

        // Step 1: Count frequency of set bits across all numbers
        for (int x : nums) {
            for (int b = 0; b < 31; b++) {
                if (((x >> b) & 1) == 1) {
                    bitCount[b]++;
                }
            }
        }

        long ans = 0;

        // Step 2: Greedily build the k largest numbers
        for (int i = 0; i < k; i++) {
            long current = 0;
            for (int b = 0; b < 31; b++) {
                if (bitCount[b] > 0) {
                    current |= (1L << b);
                    bitCount[b]--;
                }
            }
            long term = current % MOD;
            ans = (ans + term * term) % MOD;
        }

        return (int) ans;
    }
}