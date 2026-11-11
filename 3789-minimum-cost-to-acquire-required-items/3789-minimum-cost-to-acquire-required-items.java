class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1;
        long c2 = cost2;
        long cb = costBoth;
        long n1 = need1;
        long n2 = need2;
        long effC1 = Math.min(c1, cb);
        long effC2 = Math.min(c2, cb);
        long effBoth = Math.min(cb, effC1 + effC2);

        long common = Math.min(n1, n2);
        long rem1 = n1 - common;
        long rem2 = n2 - common;

        return common * effBoth + rem1 * effC1 + rem2 * effC2;
    }
}