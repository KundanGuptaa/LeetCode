class Solution {
    public int minAddToMakeValid(String s) {
        int openCount = 0;
        int openNeeded = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                openCount++;
            } else {
                if (openCount > 0) {
                    openCount--;
                } else {
                    openNeeded++;
                }
            }
        }
        return openNeeded + openCount;
    }
}