import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponentsCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] stats = new int[2]; // stats[0] = vertexCount, stats[1] = totalDegree
                dfs(i, adj, visited, stats);

                int vertices = stats[0];
                int edgesCount = stats[1] / 2;

                if (edgesCount == vertices * (vertices - 1) / 2) {
                    completeComponentsCount++;
                }
            }
        }

        return completeComponentsCount;
    }

    private void dfs(int node, List<List<Integer>> adj, boolean[] visited, int[] stats) {
        visited[node] = true;
        stats[0]++; // Vertex count
        stats[1] += adj.get(node).size(); // Degree count

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, stats);
            }
        }
    }
}