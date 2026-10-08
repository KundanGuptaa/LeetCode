class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int openCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                if (openCount > 0) {
                    result.append(ch);
                }
                openCount++;
            } else {
                openCount--;
                if (openCount > 0) {
                    result.append(ch);
                }
            }
        }

        return result.toString();
    }
}