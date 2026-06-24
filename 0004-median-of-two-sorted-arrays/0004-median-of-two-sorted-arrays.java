class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];
        int n=nums1.length;
        int m=nums2.length;
        int[] merged=new int[n+m];
        int k=0;
        for(int i=0;i<n;i++) merged[k++]=nums1[i];
        for(int i=0;i<m;i++) merged[k++]=nums2[i];
        Arrays.sort(merged);
        int total=merged.length;
        if (total % 2 == 1) {
            return merged[total / 2];
        } else {
            int med1 = merged[total / 2];
            int med2 = merged[total / 2 - 1];
            return ((double)med1 + (double)med2)/2.0;
        }
    }
}