public class FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int left = 1, right = nums.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            int cnt = countNumbers(nums, mid);
            if (cnt <= mid) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (countNumbers(nums, left) > left) {
            return left;
        }
        return right;
    }

    private int countNumbers(int[] nums, int target) {
        int res = 0;
        for (int num : nums) {
            if (num <= target) {
                res++;
            }
        }
        return res;
    }
}
