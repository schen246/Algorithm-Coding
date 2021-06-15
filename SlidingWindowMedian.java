import java.util.Arrays;

public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || nums.length < k) {
            return new double[0];
        }
        double[] res = new double[nums.length - k + 1];
        int[] win = new int[k];
        for (int i = 0; i < k; i++) {
            win[i] = nums[i];
        }
        Arrays.sort(win);
        for (int i = k; i <= nums.length; i++) {
            res[i - k] = ((double)win[k / 2] + win[(k - 1) / 2]) * 0.5;
            if (i == nums.length) break;
            remove(win, nums[i - k]);//O(k)
            insert(win, nums[i]);//O(k)
        }
        return res;
    }

    private void remove(int[] win, int val) {
        int i = 0;
        while (i < win.length && win[i] != val) i++;
        while (i < win.length - 1) {
            win[i] = win[i + 1];
            i++;
        }
    }

    public void insert(int[] win, int val) {
        int i = 0;
        while (i < win.length - 1 && win[i] <= val) i++;
        int j = win.length - 1;
        while (j > i) {
            win[j] = win[j - 1];
            j--;
        }
        win[j] = val;
    }
    // insertion sort find sliding window median - O(n*k)
}
