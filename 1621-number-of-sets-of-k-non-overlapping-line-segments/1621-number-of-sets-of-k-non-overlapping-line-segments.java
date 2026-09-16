class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int totalN = n + k - 1;
        int totalK = 2 * k;

        if (totalK > totalN) {
            return 0;
        }
        long numerator = 1;
        long denominator = 1;

        for (int i = 1; i <= totalK; i++) {
            numerator = (numerator * (totalN - i + 1)) % MOD;
            denominator = (denominator * i) % MOD;
        }
        long invDenominator = power(denominator, MOD - 2, MOD);
        return (int) ((numerator * invDenominator) % MOD);
    }
    private long power(long base, long exp, int mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}