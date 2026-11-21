class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] origTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            origTree[parent[i]].add(i);
        }
        int[] newParent = Arrays.copyOf(parent, n);
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        findNewParents(0, origTree, s, lastSeen, newParent);
        List<Integer>[] finalTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            finalTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            finalTree[newParent[i]].add(i);
        }
        int[] answer = new int[n];
        computeSizes(0, finalTree, answer);

        return answer;
    }

    private void findNewParents(int u, List<Integer>[] tree, String s, int[] lastSeen, int[] newParent) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }

        lastSeen[charIdx] = u;
        for (int v : tree[u]) {
            findNewParents(v, tree, s, lastSeen, newParent);
        }
        lastSeen[charIdx] = prevAncestor;
    }
    private int computeSizes(int u, List<Integer>[] tree, int[] answer) {
        int size = 1;
        for (int v : tree[u]) {
            size += computeSizes(v, tree, answer);
        }
        answer[u] = size;
        return size;
    }
}