import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s);
        visited.add(s);
        boolean foundValidAtLevel = false;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                String curr = queue.poll();

                if (isValid(curr)) {
                    result.add(curr);
                    foundValidAtLevel = true;
                }

                // If a valid string is already found at this depth,
                // do not generate deeper states.
                if (foundValidAtLevel) continue;

                // Generate next states by removing one parenthesis
                for (int j = 0; j < curr.length(); j++) {
                    char ch = curr.charAt(j);
                    if (ch != '(' && ch != ')') continue;

                    // Avoid duplicate branches for consecutive identical characters
                    if (j > 0 && curr.charAt(j) == curr.charAt(j - 1)) continue;

                    String next = curr.substring(0, j) + curr.substring(j + 1);
                    if (visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }

            if (foundValidAtLevel) {
                break;
            }
        }

        return result;
    }

    private boolean isValid(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(') {
                count++;
            } else if (ch == ')') {
                count--;
                if (count < 0) return false;
            }
        }
        return count == 0;
    }
}