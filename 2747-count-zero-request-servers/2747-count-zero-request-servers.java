import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = logs.length;
        int q = queries.length;

        // Sort logs in ascending order by timestamp
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // Store queries with original indices: [queryTime, originalIndex]
        int[][] sortedQueries = new int[q][2];
        for (int i = 0; i < q; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));

        int[] ans = new int[q];
        int[] serverCount = new int[n + 1];
        int activeServers = 0;

        int left = 0;
        int right = 0;

        // Slide window [qTime - x, qTime] over logs
        for (int[] query : sortedQueries) {
            int qTime = query[0];
            int origIdx = query[1];
            int startTime = qTime - x;

            // Expand right boundary: logs with time <= qTime
            while (right < m && logs[right][1] <= qTime) {
                int server = logs[right][0];
                if (serverCount[server] == 0) {
                    activeServers++;
                }
                serverCount[server]++;
                right++;
            }

            // Shrink left boundary: logs with time < startTime
            while (left < m && logs[left][1] < startTime) {
                int server = logs[left][0];
                serverCount[server]--;
                if (serverCount[server] == 0) {
                    activeServers--;
                }
                left++;
            }

            ans[origIdx] = n - activeServers;
        }

        return ans;
    }
}