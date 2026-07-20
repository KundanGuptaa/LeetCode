class Solution {

    private List<List<Integer>> convertToList(int[][] temp) {
        List<List<Integer>> matrix = new ArrayList<>();

        for (int row = 0; row < temp.length; row++) {
            ArrayList<Integer> list = new ArrayList<>();

            for (int col = 0; col < temp[row].length; col++) {
                list.add(temp[row][col]);
            }

            matrix.add(list);
        }

        return matrix;
    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] temp = new int[n][m];

        for (int col = 0; col < m; col++) {
            int newCol = (col + k) % m;
            int shift = ((col + k) / m) % n;

            int sepIdx = n - 1 - shift;

            for (int row = 0; row < n; row++)
                temp[(row + shift) % n][newCol] = grid[row][col];

        }
        return convertToList(temp);
    }
}