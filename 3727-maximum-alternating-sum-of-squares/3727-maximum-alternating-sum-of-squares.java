class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n= nums.length;
        long[] squares=new long[n];
        for(int i=0;i<n;i++){
            squares[i]=(long)nums[i]*nums[i];
        }
        Arrays.sort(squares);
        int negCount=n/2;
        long totalScore=0;
        for(int i=0;i<negCount;i++){
            totalScore-=squares[i];
        }
        for(int i=negCount;i<n;i++){
            totalScore+=squares[i];
        }
        return totalScore;
    }
}