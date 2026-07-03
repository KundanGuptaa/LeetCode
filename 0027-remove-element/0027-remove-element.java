class Solution {
    public int removeElement(int[] nums, int val) {
        int low=0,high=nums.length-1,temp;
        while(low<=high){
            if(nums[low]==val){
                temp=nums[low];
                nums[low]=nums[high];
                nums[high]=temp;
                high-=1;
            }else{
                low+=1;
            }
        }
        return low;
    }
}