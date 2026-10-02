import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        // Count frequencies of each number
        int[] count = new int[maxVal + 1];
        for (int x : nums) {
            count[x]++;
        }

        long totalPairs = 0;

        // GCD table for fast lookup
        int[][] gcdTable = new int[maxVal + 1][maxVal + 1];
        for (int i = 0; i <= maxVal; i++) {
            for (int j = 0; j <= maxVal; j++) {
                gcdTable[i][j] = gcd(i, j);
            }
        }

        // Evaluate for each possible target GCD g
        for (int g = 1; g <= maxVal; g++) {
            int maxQuotient = maxVal / g;
            if (maxQuotient == 0) continue;

            // Collect all elements divisible by g (as quotients x = num / g)
            List<Integer> quotients = new ArrayList<>();
            for (int mult = g; mult <= maxVal; mult += g) {
                for (int c = 0; c < count[mult]; c++) {
                    quotients.add(mult / g);
                }
            }

            if (quotients.size() < 2) continue;

            // dp[g1][g2]: number of ways to form set1 with gcd g1 and set2 with gcd g2
            // 0 represents an empty set
            int[][] dp = new int[maxQuotient + 1][maxQuotient + 1];
            dp[0][0] = 1;

            for (int x : quotients) {
                int[][] nextDp = new int[maxQuotient + 1][maxQuotient + 1];

                for (int g1 = 0; g1 <= maxQuotient; g1++) {
                    for (int g2 = 0; g2 <= maxQuotient; g2++) {
                        if (dp[g1][g2] == 0) continue;

                        long ways = dp[g1][g2];

                        // Choice 1: Do not include x
                        nextDp[g1][g2] = (int) ((nextDp[g1][g2] + ways) % MOD);

                        // Choice 2: Add x to set 1
                        int ng1 = (g1 == 0) ? x : gcdTable[g1][x];
                        nextDp[ng1][g2] = (int) ((nextDp[ng1][g2] + ways) % MOD);

                        // Choice 3: Add x to set 2
                        int ng2 = (g2 == 0) ? x : gcdTable[g2][x];
                        nextDp[g1][ng2] = (int) ((nextDp[g1][ng2] + ways) % MOD);
                    }
                }
                dp = nextDp;
            }

            // Both sets must have gcd == 1 (meaning original gcd was g)
            totalPairs = (totalPairs + dp[1][1]) % MOD;
        }

        return (int) totalPairs;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}