class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freeCount = new int[n];
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            minPrice = Math.min(minPrice, items[i][1]);
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    freeCount[i]++;
                }
            }
        }
        int[] dp = new int[budget + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int cost = items[i][1];
            int gain = 1 + freeCount[i];
            if (cost > budget) {
                continue;
            }
            for (int c = budget; c >= cost; c--) {
                if (dp[c - cost] != -1) {
                    dp[c] = Math.max(dp[c], dp[c - cost] + gain);
                }
            }
        }
        int maxTotal = 0;
        for (int c = 00; c <= budget; c++) {
            if (dp[c] != -1) {
                int extraCopies = (budget - c) / minPrice;
                maxTotal = Math.max(maxTotal, dp[c] + extraCopies);
            }
        }
        return maxTotal;
    }
}
