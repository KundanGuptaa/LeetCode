class Solution {
    public int totalNumbers(int[] digits) {
        int[] available = new int[10];
        for (int d : digits) {
            available[d]++;
        }

        int count = 0;

        // Check all 3-digit even numbers from 100 to 998
        for (int num = 100; num <= 998; num += 2) {
            int d1 = num / 100;         // Hundreds digit
            int d2 = (num / 10) % 10;   // Tens digit
            int d3 = num % 10;          // Units digit

            int[] needed = new int[10];
            needed[d1]++;
            needed[d2]++;
            needed[d3]++;

            if (needed[d1] <= available[d1] && 
                needed[d2] <= available[d2] && 
                needed[d3] <= available[d3]) {
                count++;
            }
        }

        return count;
    }
}