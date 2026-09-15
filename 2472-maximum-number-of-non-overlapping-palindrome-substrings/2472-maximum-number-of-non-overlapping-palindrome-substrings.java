class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int lastEnd = -1;

        for (int r = 0; r < n; r++) {
            int l1 = r - k + 1;
            if (l1 > lastEnd && isPalindrome(s, l1, r)) {
                count++;
                lastEnd = r;
                continue;
            }
            int l2 = r - k;
            if (l2 > lastEnd && isPalindrome(s, l2, r)) {
                count++;
                lastEnd = r;
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}