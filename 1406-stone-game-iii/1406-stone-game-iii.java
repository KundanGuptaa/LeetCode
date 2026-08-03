class Solution {
    public String stoneGameIII(int[] stoneValue) {

        Integer[] scores = new Integer[stoneValue.length];

        int aliceScore = score(scores, stoneValue, 0);


        if (aliceScore > 0) return "Alice";
        if (aliceScore < 0) return "Bob";

        return "Tie";
    }

    private static int score(Integer[] scores , int[] stoneValue, int start) {
        if (start > stoneValue.length - 1) return 0;

        if (scores[start] != null) return scores[start];

        int firstStone = stoneValue[start] - score(scores, stoneValue, start + 1);
        if (start > stoneValue.length - 2) return scores[start] = firstStone;

        int firstTwoStone = stoneValue[start] + stoneValue[start + 1] 
            - score(scores, stoneValue, start + 2);

        int maxScore = Math.max(firstStone, firstTwoStone);

        if (start > stoneValue.length - 3) return scores[start] = maxScore;

        int firstThreeStone = stoneValue[start] + stoneValue[start + 1]
             + stoneValue[start + 2] - score(scores, stoneValue, start + 3);
        
        return scores[start] = Math.max(maxScore, firstThreeStone);
    } 
}