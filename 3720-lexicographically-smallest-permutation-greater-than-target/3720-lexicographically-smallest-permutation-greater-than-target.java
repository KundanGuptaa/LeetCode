class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] totalCount = new int[26];
        for (int i = 0; i < n; i++) {
            totalCount[s.charAt(i) - 'a']++;
        }
        int[] prefixCount = new int[26];
        int validPrefixLen = 0;
        while (validPrefixLen < n) {
            int c = target.charAt(validPrefixLen) - 'a';
            if (prefixCount[c] + 1 <= totalCount[c]) {
                prefixCount[c]++;
                validPrefixLen++;
            } else {
                break;
            }
        }
        
        for (int i = validPrefixLen; i >= 0; i--) {
            int[] available = new int[26];
            for (int k = 0; k < 26; k++) {
                available[k] = totalCount[k] - prefixCount[k];
            }            
            if (i < n) {
                int targetChar = target.charAt(i) - 'a';
                for (int nextChar = targetChar + 1; nextChar < 26; nextChar++) {
                    if (available[nextChar] > 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(target, 0, i);
                        sb.append((char) ('a' + nextChar));
                        available[nextChar]--;
                        for (int k = 0; k < 26; k++) {
                            while (available[k] > 0) {
                                sb.append((char) ('a' + k));
                                available[k]--;
                            }
                        }
                        return sb.toString();
                    }
                }
            }
            if (i > 0) {
                prefixCount[target.charAt(i - 1) - 'a']--;
            }
        }
        return "";
    }
}