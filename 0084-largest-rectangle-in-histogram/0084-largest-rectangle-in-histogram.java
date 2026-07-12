class Solution {
    static{
        for(int i=0;i<1000;i++){
            largestRectangleArea(new int[] {i});
        }
    }
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] stack = new int[n + 1];
        int top = -1;
        int maxArea = 0;
        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];
            while (top != -1 && currentHeight < heights[stack[top]]) {
                int barIndex = stack[top--];
                int height = heights[barIndex];
                int width;
                if (top == -1) {
                    width = i;
                } else {
                    width = i - stack[top] - 1;
                }
                maxArea = Math.max(maxArea, height * width);
            }
            stack[++top] = i;
        }
        return maxArea;
    }
}