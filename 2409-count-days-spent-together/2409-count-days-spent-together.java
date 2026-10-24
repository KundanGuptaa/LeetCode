class Solution {
    private static final int[] DAYS_IN_MONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int aliceStart = toDayOfYear(arriveAlice);
        int aliceEnd = toDayOfYear(leaveAlice);
        int bobStart = toDayOfYear(arriveBob);
        int bobEnd = toDayOfYear(leaveBob);

        int overlapStart = Math.max(aliceStart, bobStart);
        int overlapEnd = Math.min(aliceEnd, bobEnd);

        return Math.max(0, overlapEnd - overlapStart + 1);
    }

    private int toDayOfYear(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int dayOfYear = 0;
        for (int m = 0; m < month - 1; m++) {
            dayOfYear += DAYS_IN_MONTH[m];
        }
        dayOfYear += day;
        return dayOfYear;
    }
}