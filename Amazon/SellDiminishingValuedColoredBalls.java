package Amazon;
import java.util.Arrays;

public class SellDiminishingValuedColoredBalls {
    private static final int M = 1000000007;

    public int maxProfit(int[] arr, int orders) {
        Arrays.sort(arr);
        long res = 0;
        for (int i = arr.length - 1; i > 0; i--) {
            int cnt = (arr[i] - arr[i - 1]) * (arr.length - i);
            if (cnt >= orders) {
                res += calculate((long)arr[i], orders, arr.length - i);
                return (int)(res % M);
            } else {
                res += calculate((long)arr[i], cnt, arr.length - i);
                orders -= cnt;
            }
        }
        res += calculate((long)arr[0], orders, arr.length);
        return (int)(res % M);
    }

    private int calculate(long n, int cnt, int len) {
        long res = 0;
        int size = cnt / len;
        int rem = cnt % len;
        res += len * (n + (n - size + 1)) * size / 2 + rem * (n - size);
        return (int)(res % M);
    }
    // time: O(nlogn) + O(n) space: O(n) depends on sorting algorithm
}
