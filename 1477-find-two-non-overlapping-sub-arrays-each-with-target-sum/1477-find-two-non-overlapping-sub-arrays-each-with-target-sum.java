class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        Arrays.fill(minLen, Integer.MAX_VALUE);
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        sumToIndex.put(0, -1);
        int currSum = 0;
        int minTotalLen = Integer.MAX_VALUE;
        int shortestSoFar = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            currSum += arr[i];
            if (sumToIndex.containsKey(currSum - target)) {
                int leftIndex = sumToIndex.get(currSum - target);
                int currentLen = i - leftIndex;
                if (leftIndex >= 0 && minLen[leftIndex] != Integer.MAX_VALUE) {
                    minTotalLen = Math.min(minTotalLen, currentLen + minLen[leftIndex]);
                }
                shortestSoFar = Math.min(shortestSoFar, currentLen);
            }
            minLen[i] = shortestSoFar;
            sumToIndex.put(currSum, i);
        }
        return minTotalLen == Integer.MAX_VALUE ? -1 : minTotalLen;
    }
}