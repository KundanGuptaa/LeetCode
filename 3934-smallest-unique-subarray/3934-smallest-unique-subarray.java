class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 911382323L;
    private static final long BASE2 = 972663749L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int low = 1;
        int high = n;
        int ans = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueSubarray(nums, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean hasUniqueSubarray(int[] nums, int len) {
        int n = nums.length;
        long hash1 = 0, hash2 = 0;
        long pow1 = 1, pow2 = 1;
        for (int i = 0; i < len - 1; i++) {
            pow1 = (pow1 * BASE1) % MOD1;
            pow2 = (pow2 * BASE2) % MOD2;
        }
        Map<Long, Integer> freq=new HashMap<>(n-len+1);
        for (int i = 0; i < n; i++) {
            long val = (long) nums[i] + 1_000_000_007L;
            if (i >= len) {
                long oldVal = (long) nums[i - len] + 1_000_000_007L;
                hash1 = (hash1 - (oldVal % MOD1) * pow1 % MOD1 + MOD1) % MOD1;
                hash2 = (hash2 - (oldVal % MOD2) * pow2 % MOD2 + MOD2) % MOD2;
            }
            hash1 = (hash1 * BASE1 + val % MOD1) % MOD1;
            hash2 = (hash2 * BASE2 + val % MOD2) % MOD2;
            if (i >= len - 1) {
                long combinedHash = (hash1 << 32) | hash2;
                freq.put(combinedHash, freq.getOrDefault(combinedHash, 0) + 1);
            }
        }
        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}