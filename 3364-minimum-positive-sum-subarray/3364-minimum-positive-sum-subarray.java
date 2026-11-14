class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        int minSum = Integer.MAX_VALUE;
        TreeMap<Integer, Integer> window = new TreeMap<>();

        for (int j = l; j <= n; j++) {
            int addVal = prefix[j - l];
            window.put(addVal, window.getOrDefault(addVal, 0) + 1);
            if (j - r - 1 >= 0) {
                int remVal = prefix[j - r - 1];
                int count = window.get(remVal);
                if (count == 1) {
                    window.remove(remVal);
                } else {
                    window.put(remVal, count - 1);
                }
            }
            Integer bestPrev = window.lowerKey(prefix[j]);
            if (bestPrev != null) {
                minSum = Math.min(minSum, prefix[j] - bestPrev);
            }
        }

        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}