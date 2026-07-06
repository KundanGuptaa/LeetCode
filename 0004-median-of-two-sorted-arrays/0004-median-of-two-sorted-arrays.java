class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[] nums3 = new int[n+m];
        int i=0,j=0,k=0;
        while(i<n || j<m){
            if(i==n){
                nums3[k++]=nums2[j++];
                continue;
            }
            if(j==m){
                nums3[k++]=nums1[i++];
                continue;
            }
            if(nums1[i]<=nums2[j]){
                nums3[k++] = nums1[i++];
            }
            else{
                nums3[k++] = nums2[j++];
            }
        }
        int mid = (n+m)/2;
        if((n+m) %2 ==1){
            return nums3[mid];
        }
        else {
            return (float)(nums3[mid-1] + nums3[mid])/2;
        }
    }
}