class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] requestCounts = new int[k];
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        int n = arrival.length;
        int maxHandled = 0;
        for (int i = 0; i < n; i++) {
            int curTime = arrival[i];
            int duration = load[i];
            while (!busy.isEmpty() && busy.peek()[0] <= curTime) {
                available.add(busy.poll()[1]);
            }
            if (available.isEmpty()) {
                continue;
            }
            int target = i % k;
            Integer serverId = available.ceiling(target);
            if (serverId == null) {
                serverId = available.first();
            }
            available.remove(serverId);
            busy.offer(new int[]{curTime + duration, serverId});
            requestCounts[serverId]++;
            maxHandled = Math.max(maxHandled, requestCounts[serverId]);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCounts[i] == maxHandled) {
                result.add(i);
            }
        }
        return result;
    }
}