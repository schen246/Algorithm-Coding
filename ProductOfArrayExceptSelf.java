public class ProductOfArrayExceptSelf {
    // no extra space - time: O(n) space: O(1)
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = nums[0];
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i];
        }
        res[n - 1] = nums[n - 1];
        nums[n - 1] = res[n - 2];
        for (int i = n - 2; i > 0; i--) {
            res[i] = res[i + 1] * nums[i];
            nums[i] = res[i - 1] * res[i + 1];
        }
        res[0] = res[1] * nums[0];
        nums[0] = res[1];
        return nums;
    }

    // time: O(n) space: O(n)
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        left[0] = nums[0];
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i];
        }
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i];
        }
        nums[0] = right[1];
        nums[n - 1] = left[n - 2];
        for (int i = 1; i < n - 1; i++) {
            nums[i] = left[i - 1] * right[i + 1];
        }
        return nums;
    }
}
