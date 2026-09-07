class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int[] endsWith = new int[26];
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int charIdx = s.charAt(i) - 'a';
            int newSubseqCount = (1 + total) % MOD;
            total = (int) (((long) total + newSubseqCount - endsWith[charIdx] + MOD) % MOD);
            endsWith[charIdx] = newSubseqCount;
        }
        return total;
    }
}