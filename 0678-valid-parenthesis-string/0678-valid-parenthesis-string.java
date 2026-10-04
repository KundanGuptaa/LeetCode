class Solution {
    public boolean checkValidString(String s) {
        int cmin = 0;
        int cmax = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                cmin++;
                cmax++;
            } else if (ch == ')') {
                cmin--;
                cmax--;
            } else if (ch == '*') {
                cmin--;
                cmax++;
            }
            if (cmax < 0) {
                return false;
            }
            cmin = Math.max(cmin, 0);
        }
        return cmin == 0;
    }
}