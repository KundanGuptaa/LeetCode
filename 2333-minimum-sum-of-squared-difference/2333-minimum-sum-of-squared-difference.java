class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        int maxDiff = 0;
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diffs[i]);
        }
        int[] count = new int[maxDiff + 1];
        for (int d : diffs) {
            count[d]++;
        }
        for (int v = maxDiff; v > 0 && k > 0; v--) {
            if (count[v] == 0) continue;
            long needed = count[v];
            if (k >= needed) {
                count[v - 1] += count[v];
                k -= needed;
                count[v] = 0;
            } else {
                count[v - 1] += (int) k;
                count[v] -= (int) k;
                k = 0;
            }
        }
        long result = 0;
        for (int v = 1; v <= maxDiff; v++) {
            if (count[v] > 0) {
                result += (long) count[v] * v * v;
            }
        }
        return result;
    }
}