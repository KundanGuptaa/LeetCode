class Solution {
    public int countCommas(int n) {
        int commas = 0;
        for (long threshold = 1000; threshold <= n; threshold *= 1000) {
            commas += (n - threshold + 1);
        }
        return commas;
    }
}