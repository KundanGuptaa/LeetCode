class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid=new char[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(grid[i],'#');
        }
        for(int r=0;r<m;r++){
            grid[r][0]='.';
        }
        for(int c=0;c<n;c++){
            grid[m-1][c]='.';
        }

        String[] result=new String[m];
        for(int i=0;i<m;i++){
            result[i]=new String(grid[i]);
        }
        return result;
    }
}