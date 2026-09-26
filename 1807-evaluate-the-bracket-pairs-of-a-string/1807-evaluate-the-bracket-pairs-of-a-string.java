class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        StringBuilder result = new StringBuilder();
        int n = s.length();
        int i = 0;
        while (i < n) {
            char ch = s.charAt(i);
            if (ch == '(') {
                int j = i + 1;
                while (j < n && s.charAt(j) != ')') {
                    j++;
                }
                String key = s.substring(i + 1, j);
                result.append(map.getOrDefault(key, "?"));
                i = j + 1;
            } else {
                result.append(ch);
                i++;
            }
        }
        return result.toString();
    }
}