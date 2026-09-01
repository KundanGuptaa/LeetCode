class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();        
        int startX = -1, startY = -1;
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }        
        int litterCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }
        if (litterCount == 0) {
            return 0;
        }        
        int targetMask = (1 << litterCount) - 1;
        int[][] maxEnergy = new int[m * n][1 << litterCount];
        for (int[] row : maxEnergy) {
            Arrays.fill(row, -1);
        }
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startX, startY, energy, 0});
        maxEnergy[startX * n + startY][0] = energy;
        int moves = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            moves++;
            for (int s = 0; s < size; s++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int curEnergy = curr[2];
                int curMask = curr[3];                
                if (curEnergy <= 0) {
                    continue;
                }                
                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];                    
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }
                    char cell = classroom[nr].charAt(nc);
                    if (cell == 'X') {
                        continue;
                    }
                    int nxtEnergy = (cell == 'R') ? energy : curEnergy - 1;
                    int nxtMask = curMask;
                    
                    if (cell == 'L') {
                        nxtMask |= (1 << litterId[nr][nc]);
                    }
                    if (nxtMask == targetMask) {
                        return moves;
                    }                    
                    int cellIdx = nr * n + nc;
                    if (nxtEnergy > maxEnergy[cellIdx][nxtMask]) {
                        maxEnergy[cellIdx][nxtMask] = nxtEnergy;
                        queue.offer(new int[]{nr, nc, nxtEnergy, nxtMask});
                    }
                }
            }
        }
        return -1;
    }
}