class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] graph = new ArrayList[n];
        for(int i=0;i<n;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<invocations.length;i++){
            int src = invocations[i][0];
            int dest = invocations[i][1];
            graph[src].add(dest);
        }
        boolean vis[] = new boolean[n];
        getfaulty(graph,k,vis);
        List<Integer> ans = new ArrayList<>();
        for(int arr[]:invocations){
            int u = arr[0];
            int v = arr[1];
            if(!vis[u] && vis[v]){
                for(int i=0;i<n;i++){
                    ans.add(i);
                }
                return ans;
            }
        }
        for(int i=0;i<n;i++){
            if(!vis[i]){
                ans.add(i);
            }
        }
        return ans;
    }
    private void getfaulty(List<Integer>[] graph,int k, boolean vis[]){
        vis[k] = true;
        for(int i=0;i<graph[k].size();i++){
            int src = graph[k].get(i);
            if(!vis[src]){
                getfaulty(graph,src,vis);
            }
        }
    }
}