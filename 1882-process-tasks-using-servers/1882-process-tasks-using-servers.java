class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }
        PriorityQueue<long[]> busyServers = new PriorityQueue<>((a, b) -> 
            Long.compare(a[0], b[0])
        );
        long curTime = 0;
        for (int j = 0; j < m; j++) {
            curTime = Math.max(curTime, (long) j);
            if (freeServers.isEmpty()) {
                curTime = Math.max(curTime, busyServers.peek()[0]);
            }
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= curTime) {
                long[] server = busyServers.poll();
                freeServers.offer(new int[]{(int) server[1], (int) server[2]});
            }
            int[] chosen = freeServers.poll();
            ans[j] = chosen[1];

            busyServers.offer(new long[]{curTime + tasks[j], chosen[0], chosen[1]});
        }
        return ans;
    }
}