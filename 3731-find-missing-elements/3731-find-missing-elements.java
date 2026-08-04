class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        boolean[] seen = new boolean[101];
        int min = 101, max = 0;

        for (int x : nums) {
            seen[x] = true;
            if (x < min)
                min = x;
            if (x > max)
                max = x;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (!seen[i]) {
                res.add(i);
            }
        }

        return res;
    }
}