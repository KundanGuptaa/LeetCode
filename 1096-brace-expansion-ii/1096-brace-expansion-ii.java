import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Deque<Object> stack = new ArrayDeque<>();
        List<Set<String>> groups = new ArrayList<>();
        Set<String> product = new HashSet<>(Collections.singletonList(""));
        int i = 0;
        int n = expression.length();
        while (i < n) {
            char ch = expression.charAt(i);
            if (ch == '{') {
                stack.push(groups);
                stack.push(product);
                groups = new ArrayList<>();
                product = new HashSet<>(Collections.singletonList(""));
                i++;
            } else if (ch == '}') {
                groups.add(product);
                Set<String> unionSet = new HashSet<>();
                for (Set<String> g : groups) {
                    unionSet.addAll(g);
                }
                @SuppressWarnings("unchecked")
                Set<String> prevProduct = (Set<String>) stack.pop();
                @SuppressWarnings("unchecked")
                List<Set<String>> prevGroups = (List<Set<String>>) stack.pop();
                product = cartesianProduct(prevProduct, unionSet);
                groups = prevGroups;
                i++;
            } else if (ch == ',') {
                groups.add(product);
                product = new HashSet<>(Collections.singletonList(""));
                i++;
            } else {
                StringBuilder sb = new StringBuilder();
                while (i < n && Character.isLowerCase(expression.charAt(i))) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                product = cartesianProduct(product, Collections.singleton(sb.toString()));
            }
        }
        groups.add(product);
        Set<String> finalSet = new HashSet<>();
        for (Set<String> g : groups) {
            finalSet.addAll(g);
        }
        List<String> result = new ArrayList<>(finalSet);
        Collections.sort(result);
        return result;
    }
    private Set<String> cartesianProduct(Set<String> set1, Set<String> set2) {
        Set<String> res = new HashSet<>();
        for (String s1 : set1) {
            for (String s2 : set2) {
                res.add(s1 + s2);
            }
        }
        return res;
    }
}