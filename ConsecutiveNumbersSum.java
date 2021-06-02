public class ConsecutiveNumbersSum {
    public int consecutiveNumbersSum(int n) {
        int right = (int)(Math.sqrt(n * 2 + 0.25) - 0.5);
        int res = 0;
        for (int k = 1; k <= right; k++) {
            if ((n * 2 - k * k - k) % (k * 2) == 0) {
                res++;
            }
        }
        return res;
    }
    // time: O(sqrt(n)) space: O(1)
}
