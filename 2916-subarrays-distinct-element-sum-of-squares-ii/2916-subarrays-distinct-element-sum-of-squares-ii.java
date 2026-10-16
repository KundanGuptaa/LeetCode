class Solution {
    private static final int MOD = 1_000_000_007;
    static class SegmentTree {
        int n;
        long[] sum1;
        long[] sum2;
        long[] lazy;
        public SegmentTree(int n) {
            this.n = n;
            sum1 = new long[4 * n];
            sum2 = new long[4 * n];
            lazy = new long[4 * n];
        }
        private void apply(int node, int l, int r, long val) {
            long len = r - l + 1;
            long term1 = (2 * val % MOD) * sum1[node] % MOD;
            long term2 = ((val * val % MOD) * len) % MOD;
            sum2[node] = (sum2[node] + term1 + term2) % MOD;
            sum1[node] = (sum1[node] + val * len) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }
        private void pushDown(int node, int l, int r) {
            if (lazy[node] == 0) return;
            int mid = l + (r - l) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
        public void update(int node, int l, int r, int ql, int qr, int val) {
            if (ql <= l && r <= qr) {
                apply(node, l, r, val);
                return;
            }
            pushDown(node, l, r);
            int mid = l + (r - l) / 2;
            if (ql <= mid) {
                update(2 * node, l, mid, ql, qr, val);
            }
            if (qr > mid) {
                update(2 * node + 1, mid + 1, r, ql, qr, val);
            }
            sum1[node] = (sum1[2 * node] + sum1[2 * node + 1]) % MOD;
            sum2[node] = (sum2[2 * node] + sum2[2 * node + 1]) % MOD;
        }
    }
    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        Map<Integer, Integer> lastSeen = new HashMap<>();
        long total = 0;
        for (int j = 0; j < n; j++) {
            int prev = lastSeen.getOrDefault(nums[j], -1);
            tree.update(1, 0, n - 1, prev + 1, j, 1);
            lastSeen.put(nums[j], j);
            total = (total + tree.sum2[1]) % MOD;
        }
        return (int) total;
    }
}