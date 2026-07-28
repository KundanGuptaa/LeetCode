class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int[] freq = new int[26];
        for(char ch:s.toCharArray())
        {
            freq[ch - 'a']++;
        }
        char[] ans = new char[n];
        int left = 0;
        int right = n-1;
        for(int i=0; i < 26; i++)
        {
            while(freq[i] >= 2)
            {
                char ch = (char)('a' + i);
                ans[left++] = ch;
                ans[right--] = ch;
                freq[i] -= 2;
            }
        }
        for(int i=0; i < 26; i++)
        {
            if(freq[i] == 1)
            {
                ans[left] = (char)('a' + i);
                break;
            }
        }
        return new String(ans); 
    }
}