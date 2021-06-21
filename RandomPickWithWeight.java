import java.util.Random;

public class RandomPickWithWeight {
    int[] sum;
    Random random;
    public RandomPickWithWeight(int[] w) {
        random = new Random();
        sum = new int[w.length];
        sum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            sum[i] = sum[i - 1] + w[i];
        }
    }
    
    public int pickIndex() {
        int target = random.nextInt(sum[sum.length - 1]) + 1;// [1, sum[end]]
        return search(target);
    }
    
    // find first index with element >= target
    private int search(int target) {
        int left = 0, right = sum.length - 1;
        int res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sum[mid] >= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}
