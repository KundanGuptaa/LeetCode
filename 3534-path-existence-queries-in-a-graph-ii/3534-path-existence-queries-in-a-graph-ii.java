import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Find sorted unique values
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        
        int uniqueCount = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                uniqueCount++;
            }
        }

        int[] vals = new int[uniqueCount];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                vals[idx++] = sorted[i];
            }
        }

        // Map each value to its index in unique sorted array
        Map<Integer, Integer> valToIdx = new HashMap<>();
        for (int i = 0; i < uniqueCount; i++) {
            valToIdx.put(vals[i], i);
        }

        // Identify connected components
        int[] comp = new int[uniqueCount];
        int compId = 0;
        comp[0] = 0;
        for (int i = 1; i < uniqueCount; i++) {
            if (vals[i] - vals[i - 1] > maxDiff) {
                compId++;
            }
            comp[i] = compId;
        }

        // Binary lifting table for jumping right
        // LOGN = 18 is enough for N <= 10^5
        int LOG = 18;
        int[][] rightJump = new int[LOG][uniqueCount];
        int[][] leftJump = new int[LOG][uniqueCount];

        // Base jumps (2^0 = 1 step) using two pointers
        int r = 0;
        for (int i = 0; i < uniqueCount; i++) {
            while (r + 1 < uniqueCount && vals[r + 1] - vals[i] <= maxDiff) {
                r++;
            }
            rightJump[0][i] = r;
        }

        int l = 0;
        for (int i = 0; i < uniqueCount; i++) {
            while (l < i && vals[i] - vals[l] > maxDiff) {
                l++;
            }
            leftJump[0][i] = l;
        }

        // Compute 2^k jumps
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < uniqueCount; i++) {
                rightJump[k][i] = rightJump[k - 1][rightJump[k - 1][i]];
                leftJump[k][i] = leftJump[k - 1][leftJump[k - 1][i]];
            }
        }

        int q = queries.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            if (u == v) {
                ans[i] = 0;
                continue;
            }

            int valU = nums[u];
            int valV = nums[v];

            if (valU == valV) {
                ans[i] = 1;
                continue;
            }

            int idxU = valToIdx.get(valU);
            int idxV = valToIdx.get(valV);

            if (comp[idxU] != comp[idxV]) {
                ans[i] = -1;
                continue;
            }

            int steps = 0;
            if (idxU < idxV) {
                int curr = idxU;
                for (int k = LOG - 1; k >= 0; k--) {
                    if (rightJump[k][curr] < idxV) {
                        steps += (1 << k);
                        curr = rightJump[k][curr];
                    }
                }
                steps += 1; // Final step reaches or exceeds idxV
            } else {
                int curr = idxU;
                for (int k = LOG - 1; k >= 0; k--) {
                    if (leftJump[k][curr] > idxV) {
                        steps += (1 << k);
                        curr = leftJump[k][curr];
                    }
                }
                steps += 1; // Final step reaches or drops below idxV
            }

            ans[i] = steps;
        }

        return ans;
    }
}