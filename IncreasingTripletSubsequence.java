public class IncreasingTripletSubsequence {
    // M1: binary search - time: O(nlog3) space: O(1)
    public boolean increasingTriplet(int[] nums) {
        int[] arr = new int[3];
        arr[0] = nums[0];
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            int index = search(arr, len, nums[i]);
            arr[index] = nums[i];
            if (index == len) {
                len++;
            }
            if (len == 3) {
                return true;
            }
        }
        return false;
    }
    
    private int search(int[] arr, int len, int val) {
        // binary search - find first index in [0, len - 1] st. arr[index] >= val if no return len
        int left = 0, right = len - 1;
        int res = len;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= val) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    // M2: definition
    public boolean increasingTriplet2(int[] nums) {
        int small = Integer.MAX_VALUE, large = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= small) {
                small = num;
            } else if (num <= large) {
                large = num;
            } else {
                return true;
            }
        }
        return false;
    }
}
