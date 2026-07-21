class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int one = 0;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)=='1')
            one++;
        }
        if(one==0)
        return 0;
        if((one==1 && (s.charAt(0)=='1'||s.charAt(s.length()-1)=='1')) || (one==s.length()))
        return one;
        s = "1" + s + "1";
        List<Integer> zeroIdx = new ArrayList<>();
        int cnt = 0;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)=='0')
            {
                cnt++;
            }
            else
            {
                if(cnt>0)
                zeroIdx.add(cnt);
                cnt = 0;
            }
        }
        int max = zeroIdx.get(0);
        if(zeroIdx.size()>1)
        {
            for(int i=1;i<zeroIdx.size();i++)
            {
                int sum = zeroIdx.get(i)+zeroIdx.get(i-1);
                if(max<sum)
                {
                    max = sum;
                }
            }

        }
        else
        {
            return one;
        }
        return max+one;
    }
}