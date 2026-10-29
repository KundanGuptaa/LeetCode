import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[][] up;
    private int[] depth;
    private int[] tin;
    private int[] tout;
    private int timer;
    private int LOG;
    private int[] bit;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.LOG = 32 - Integer.numberOfLeadingZeros(n);
        this.adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        up = new int[n][LOG];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        timer = 0;

        dfs(0, 0, 0);

        // Fenwick tree for subtree range XOR updates and point queries
        bit = new int[n + 2];
        char[] currChar = s.toCharArray();

        // Apply initial characters to subtree paths
        for (int i = 0; i < n; i++) {
            int mask = 1 << (currChar[i] - 'a');
            rangeUpdate(tin[i], tout[i], mask);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);
                if (currChar[u] != newChar) {
                    int delta = (1 << (newChar - 'a')) ^ (1 << (currChar[u] - 'a'));
                    rangeUpdate(tin[u], tout[u], delta);
                    currChar[u] = newChar;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int lcaNode = getLca(u, v);

                int pu = pointQuery(tin[u]);
                int pv = pointQuery(tin[v]);
                int lcaMask = 1 << (currChar[lcaNode] - 'a');

                int pathXor = pu ^ pv ^ lcaMask;
                ans.add(Integer.bitCount(pathXor) <= 1);
            }
        }

        return ans;
    }

    private void dfs(int u, int p, int d) {
        tin[u] = ++timer;
        depth[u] = d;
        up[u][0] = p;
        for (int i = 1; i < LOG; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }

        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u, d + 1);
            }
        }
        tout[u] = timer;
    }

    private int getLca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        for (int i = LOG - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }

        if (u == v) return u;

        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }

        return up[u][0];
    }

    private void bitXor(int idx, int val) {
        for (; idx < bit.length; idx += idx & -idx) {
            bit[idx] ^= val;
        }
    }

    private void rangeUpdate(int l, int r, int val) {
        bitXor(l, val);
        bitXor(r + 1, val);
    }

    private int pointQuery(int idx) {
        int sum = 0;
        for (; idx > 0; idx -= idx & -idx) {
            sum ^= bit[idx];
        }
        return sum;
    }
}