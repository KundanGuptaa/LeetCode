class Solution {
    public int minimumPushes(String word) {
        
        int cost = 1;
        int remaining = word.length();
        int ans = 0;

        while(remaining > 0)
        {
            int take = Math.min(8, remaining);
            ans += cost*take;
            remaining -= take;
            cost += 1;

        }
        return ans;
    }
}