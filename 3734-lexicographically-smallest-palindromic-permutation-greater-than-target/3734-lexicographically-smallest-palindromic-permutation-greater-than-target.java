class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        // Validate if a palindrome can be formed
        int oddCount = 0;
        int oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                oddChar = i;
            }
        }

        if ((n % 2 == 0 && oddCount > 0) || (n % 2 != 0 && oddCount != 1)) {
            return "";
        }

        int m = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }
        char midChar = (oddChar != -1) ? (char) ('a' + oddChar) : 0;

        String best = "";

        // Case 1: First half matches target[0...m-1] exactly
        int[] currentHalf = halfCount.clone();
        boolean canMatchExact = true;
        for (int i = 0; i < m; i++) {
            int c = target.charAt(i) - 'a';
            if (currentHalf[c] > 0) {
                currentHalf[c]--;
            } else {
                canMatchExact = false;
                break;
            }
        }

        if (canMatchExact) {
            String candidate = buildPalindrome(target.substring(0, m), midChar, n % 2 != 0);
            if (candidate.compareTo(target) > 0) {
                best = candidate;
            }
        }

        // Case 2: Mismatch at index i (0 <= i < m) with a strictly larger character
        int[] prefixHalf = halfCount.clone();
        int maxPrefix = 0;
        while (maxPrefix < m) {
            int c = target.charAt(maxPrefix) - 'a';
            if (prefixHalf[c] > 0) {
                prefixHalf[c]--;
                maxPrefix++;
            } else {
                break;
            }
        }

        for (int i = maxPrefix; i >= 0; i--) {
            // Recalculate available characters for prefix length i
            int[] available = halfCount.clone();
            for (int j = 0; j < i; j++) {
                available[target.charAt(j) - 'a']--;
            }

            if (i < m) {
                int targetChar = target.charAt(i) - 'a';
                for (int nextChar = targetChar + 1; nextChar < 26; nextChar++) {
                    if (available[nextChar] > 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(target, 0, i);
                        sb.append((char) ('a' + nextChar));
                        available[nextChar]--;

                        // Fill the rest of the first half with smallest available characters
                        for (int k = 0; k < 26; k++) {
                            while (available[k] > 0) {
                                sb.append((char) ('a' + k));
                                available[k]--;
                            }
                        }

                        String candidate = buildPalindrome(sb.toString(), midChar, n % 2 != 0);
                        if (best.isEmpty() || candidate.compareTo(best) < 0) {
                            best = candidate;
                        }
                        break;
                    }
                }
            }
        }

        return best;
    }

    private String buildPalindrome(String firstHalf, char midChar, boolean isOdd) {
        StringBuilder sb = new StringBuilder(firstHalf);
        if (isOdd) {
            sb.append(midChar);
        }
        for (int i = firstHalf.length() - 1; i >= 0; i--) {
            sb.append(firstHalf.charAt(i));
        }
        return sb.toString();
    }
}