class Solution {
    static class State implements Comparable<State> {
        int dist;
        int u;
        int streak;

        public State(int dist, int u, int streak) {
            this.dist = dist;
            this.u = u;
            this.streak = streak;
        }

        @Override
        public int compareTo(State other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {
        if (k < 1) return -1;
        List<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(new Edge(edge[1], edge[2]));
        }
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<State> pq = new PriorityQueue<>();
        dist[0][1] = 0;
        pq.offer(new State(0, 0, 1));

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int u = curr.u;
            int streak = curr.streak;
            int d = curr.dist;

            if (d > dist[u][streak]) continue;

            if (u == n - 1) {
                return d;
            }

            char currChar = labels.charAt(u);

            for (Edge edge : graph[u]) {
                int v = edge.to;
                char nextChar = labels.charAt(v);
                int nextStreak = (currChar == nextChar) ? streak + 1 : 1;

                if (nextStreak <= k && d + edge.weight < dist[v][nextStreak]) {
                    dist[v][nextStreak] = d + edge.weight;
                    pq.offer(new State(dist[v][nextStreak], v, nextStreak));
                }
            }
        }
        return -1;
    }
}