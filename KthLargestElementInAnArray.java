public class KthLargestElementInAnArray {
    // M1: sort O(nlogn) M2: minHeap O(nlogk) M3: quickSelect O(n) average 
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return kthLargest(nums, 0, nums.length - 1, k);
    }

    private int kthLargest(int[] nums, int left, int right, int k) {
        int pivotIndex = partition(nums, left, right);
        if (pivotIndex - left + 1 == k) {
            return nums[pivotIndex];
        }
        if (pivotIndex - left + 1 < k) {
            return kthLargest(nums, pivotIndex + 1, right, k - (pivotIndex - left + 1));
        }
        return kthLargest(nums, left, pivotIndex, k);
    }

    private int partition(int[] nums, int left, int right) {
        int pivotValue = nums[left];
        int i = left + 1, j = right; // [left + 1, i) val > pivotValue
        while (i <= j) {
            while (i <= j && nums[i] > pivotValue) {
                i++;
            }
            while (i <= j && nums[j] <= pivotValue) {
                j--;
            }
            if (i > j) break;
            swap(nums, i++, j--);
        }
        nums[left] = nums[j];
        nums[j] = pivotValue;
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
