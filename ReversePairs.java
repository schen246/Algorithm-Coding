public class ReversePairs {
    // M1: bf - time: O(n^2) space: O(1)
    // M2: divide and conquer - merge sort - time: O(nlogn) space: O(n)
    public int reversePairs(int[] nums) {
        int[] help = new int[nums.length];
        int[] res = new int[1];
        mergeSort(0, nums.length - 1, nums, help, res);
        return res[0];
    }
    
    private void mergeSort(int left, int right, int[] nums, int[] help, int[] res) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(left, mid, nums, help, res);
        mergeSort(mid + 1, right, nums, help, res);
        merge(left, mid, right, nums, help, res);
    }
    
    private void merge(int left, int mid, int right, int[] nums, int[] help, int[] res) {
        int i = left, j = mid + 1;
        while (j <= right) {
            while (i <= mid && (long)nums[i] <= 2 * (long)nums[j]) {
                i++;
            }
            res[0] += mid + 1 - i;
            j++;
        }
        // merge
        int l = left, r = mid + 1, index = left;
        while (l <= mid && r <= right) {
            if (nums[l] <= nums[r]) {
                help[index++] = nums[l++];
            } else {
                help[index++] = nums[r++];
            }
        }
        while (l <= mid) {
            help[index++] = nums[l++];
        }
        while (r <= right) {
            help[index++] = nums[r++];
        }
        for (int p = left; p <= right; p++) {
            nums[p] = help[p];
        }
    }
}
