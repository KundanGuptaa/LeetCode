class Solution {
    public int minOperations(int[] nums) {
        int n=nums.length;
        if(n<=1) return 0;
        boolean alreadySorted=true;
        for(int i=1;i<n;i++){
            if(nums[i]<nums[i-1]){
                alreadySorted=false;
                break;
            }
        }
        if(alreadySorted) return 0;
        int p=-1;
        for(int i=0;i<n;i++){
            if(nums[i]==0){
                p=i;
                break;
            }
        }

        boolean isIncreasing = true;
        for(int i=0;i<n;i++){
            if((nums[(i+1)%n]-nums[i]+n)%n!=1){
                isIncreasing=false;
                break;
            }
        }
        if(isIncreasing){
            return Math.min(p,2+(n-p)%n);
        }

        boolean isDecreasing=true;
        for(int i=0;i<n;i++){
            if((nums[i]-nums[(i+1)%n]+n)%n!=1){
                isDecreasing=false;
                break;
            }
        }
        if(isDecreasing){
            int cost1=1+(n-1-p);
            int cost2=1+(p+1)%n;
            return Math.min(cost1,cost2);
        }
        return -1;
    }
}