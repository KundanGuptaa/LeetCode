class Solution {
    public int maxSubarrayLength(int[] arr, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int maxlen = 0;
        for (int right = 0; right < arr.length; right++) {
            freq.put(arr[right], freq.getOrDefault(arr[right], 0) + 1);

            while (freq.get(arr[right]) > k) {
                freq.put(arr[left], freq.get(arr[left]) - 1);
                left++;
            }
            maxlen = Math.max(maxlen, right - left + 1);
        }
        return maxlen;
    }
}