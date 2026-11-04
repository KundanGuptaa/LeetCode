import java.util.Arrays;
import java.util.TreeMap;

class Solution {
    static class MedianFinder {
        private final TreeMap<Long, Integer> left = new TreeMap<>();
        private final TreeMap<Long, Integer> right = new TreeMap<>();
        private int leftSize = 0, rightSize = 0;
        private long leftSum = 0, rightSum = 0;
        private final int targetLeft;

        public MedianFinder(int x) {
            this.targetLeft = (x + 1) / 2;
        }

        private void add(TreeMap<Long, Integer> map, long val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        private void remove(TreeMap<Long, Integer> map, long val) {
            int count = map.get(val);
            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }
        }

        public void insert(long val) {
            if (left.isEmpty() || val <= left.lastKey()) {
                add(left, val);
                leftSum += val;
                leftSize++;
            } else {
                add(right, val);
                rightSum += val;
                rightSize++;
            }
            rebalance();
        }

        public void erase(long val) {
            if (left.containsKey(val)) {
                remove(left, val);
                leftSum -= val;
                leftSize--;
            } else {
                remove(right, val);
                rightSum -= val;
                rightSize--;
            }
            rebalance();
        }

        private void rebalance() {
            while (leftSize > targetLeft) {
                long maxLeft = left.lastKey();
                remove(left, maxLeft);
                leftSum -= maxLeft;
                leftSize--;

                add(right, maxLeft);
                rightSum += maxLeft;
                rightSize++;
            }
            while (leftSize < targetLeft && rightSize > 0) {
                long minRight = right.firstKey();
                remove(right, minRight);
                rightSum -= minRight;
                rightSize--;

                add(left, minRight);
                leftSum += minRight;
                leftSize++;
            }
        }

        public long getCost() {
            long median = left.lastKey();
            return ((long) leftSize * median - leftSum) + (rightSum - (long) rightSize * median);
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int m = n - x + 1;
        long[] windowCost = new long[m];

        MedianFinder mf = new MedianFinder(x);
        for (int i = 0; i < x; i++) {
            mf.insert(nums[i]);
        }
        windowCost[0] = mf.getCost();

        for (int i = x; i < n; i++) {
            mf.erase(nums[i - x]);
            mf.insert(nums[i]);
            windowCost[i - x + 1] = mf.getCost();
        }

        long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(curr, INF);
            for (int i = j * x; i <= n; i++) {
                curr[i] = curr[i - 1];
                int start = i - x;
                long cost = windowCost[start];
                if (prev[start] != INF) {
                    curr[i] = Math.min(curr[i], prev[start] + cost);
                }
            }
            System.arraycopy(curr, 0, prev, 0, n + 1);
        }

        return prev[n];
    }
}