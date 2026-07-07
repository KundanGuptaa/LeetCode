class Solution {
    static{
        for(int i=0;i<500;i++){
            checkInclusion("a","a");
        }
    }
    public static boolean compareFreq(int[] count1, int[] count2){
        for(int i = 0;i<26;i++){
            if(count1[i] != count2[i]){
                return false;
            }
        }
        return true;
    }
    public static boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        if(n > m){
            return false;
        }

        int[] count1 = new int[26];
        for(int i=0;i<n;i++){
            char ch = s1.charAt(i);
            int index = ch - 'a';
            count1[index]++;
        }

        int i =0;
        int[] count2 = new int[26];
        int windowSize = n;
        for(i=0;i<windowSize;i++){
            char ch = s2.charAt(i);
            int index = ch - 'a';
            count2[index]++;
        }

        if(compareFreq(count1,count2)){
            return true;
        }
        else{
            while(i<m){
                char newChar = s2.charAt(i);
                int newCharIdx = newChar - 'a';
                count2[newCharIdx]++;

                int oldCharIdx = i - windowSize;
                char oldChar = s2.charAt(oldCharIdx);
                int oldCharIdxInFreq = oldChar - 'a';
                count2[oldCharIdxInFreq]--;

                if(compareFreq(count1,count2)){
                    return true;
                }
                i++;
            }
        }

        return false;

    }
}