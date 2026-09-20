class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int reversedAlphabetPos = 'z' - c + 1;
            int stringPos = i + 1;
            
            totalDegree += reversedAlphabetPos * stringPos;
        }
        
        return totalDegree;
    }
}