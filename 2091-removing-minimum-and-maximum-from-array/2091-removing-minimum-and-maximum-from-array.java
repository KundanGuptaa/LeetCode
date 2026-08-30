class Solution {
    public int minimumDeletions(int[] nums) {
        int n=nums.length,min=Integer.MAX_VALUE,max=Integer.MIN_VALUE,minIndex=-1,maxIndex=-1;
        for(int i=0;i<n;i++){
            int val=nums[i];
            if(val>max){
                max=val;
                maxIndex=i;
            }
            if(val<min){
                min=val;
                minIndex=i;
            }
        }
        int rMax=(Integer)Math.max(minIndex+1,maxIndex+1);
        int rMin=(Integer)Math.min(minIndex+1,maxIndex+1);
        int[]ans=new int[3];
        ans[0]=rMax;
        ans[1]=n-rMin+1;
        ans[2]=rMin+(n-rMax+1);
        min=Integer.MAX_VALUE;
        for(int val:ans){
            if(val<min)min=val;
        }
        return min;
    }
}