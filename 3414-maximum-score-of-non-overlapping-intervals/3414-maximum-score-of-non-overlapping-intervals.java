class Solution {
    static class State {
        long weight;
        List<Integer> indices;
        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
        static State better(State a, State b) {
            if (a == null) return b;
            if (b == null) return a;
            if (a.weight > b.weight) return a;
            if (b.weight > a.weight) return b;
            int len = Math.min(a.indices.size(), b.indices.size());
            for (int i = 0; i < len; i++) {
                int cmp = Integer.compare(a.indices.get(i), b.indices.get(i));
                if (cmp != 0) {
                    return cmp < 0 ? a : b;
                }
            }
            return a.indices.size() <= b.indices.size() ? a : b;
        }
    }
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0); // l
            arr[i][1] = intervals.get(i).get(1); // r
            arr[i][2] = intervals.get(i).get(2); // weight
            arr[i][3] = i;                       // original index
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });
        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(0, new ArrayList<>());
            }
        }
        for (int i = 1; i <= n; i++) {
            int l = arr[i - 1][0];
            int r = arr[i - 1][1];
            int w = arr[i - 1][2];
            int idx = arr[i - 1][3];
            int p = binarySearch(arr, i - 1, l);
            for (int k = 1; k <= 4; k++) {
                State best = dp[i - 1][k];
                State prev = dp[p][k - 1];
                long newWeight = prev.weight + w;

                List<Integer> newIndices = new ArrayList<>(prev.indices);
                newIndices.add(idx);
                Collections.sort(newIndices);

                State candidate = new State(newWeight, newIndices);
                dp[i][k] = State.better(best, candidate);
            }
        }
        List<Integer> ansList = dp[n][4].indices;
        int[] ans = new int[ansList.size()];
        for (int i = 0; i < ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
    private int binarySearch(int[][] arr, int rightBound, int targetL) {
        int low = 0, high = rightBound - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid][1] < targetL) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans + 1;
    }
}