class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int mid = n / 2;
        
        int sumDiff = 0;
        int qDiff = 0;
        for (int i = 0; i < mid; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                qDiff++;
            } else {
                sumDiff += (c - '0');
            }
        }
        for (int i = mid; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                qDiff--;
            } else {
                sumDiff -= (c - '0');
            }
        }
        return sumDiff * 2 != -qDiff * 9;
    }
}