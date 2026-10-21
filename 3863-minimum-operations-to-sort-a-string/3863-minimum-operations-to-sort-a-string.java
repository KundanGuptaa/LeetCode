class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean sorted = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted)
            return 0;
        if (n <= 2)
            return -1;
        char minChar = 'z';
        char maxChar = 'a';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < minChar)
                minChar = c;
            if (c > maxChar)
                maxChar = c;
        }
        char maxPrefix = 'a';
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > maxPrefix)
                maxPrefix = s.charAt(i);
        }
        if (maxPrefix <= s.charAt(n - 1))
            return 1;

        char minSuffix = 'z';
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < minSuffix)
                minSuffix = s.charAt(i);
        }
        if (s.charAt(0) <= minSuffix)
            return 1;
        boolean minInPrefix=false;
        for(int i=0;i<n-1;i++){
            if(s.charAt(i)==minChar){
                minInPrefix=true;
                break;
            }
        }

        boolean maxInSuffix = false;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == maxChar) {
                maxInSuffix = true;
                break;
            }
        }
        if (!minInPrefix && !maxInSuffix) {
            return 3;
        }
        return 2;
    }
}