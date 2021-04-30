public class QuickSortAnArray {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = partition(nums, left, right);
        quickSort(nums, left, mid - 1);
        quickSort(nums, mid + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int i = left + 1, j = right;
        while (i <= j) {
            while (i <= j && nums[i] <= pivot) {
                i++;
            }
            while (i <= j && nums[j] > pivot) {
                j--;
            }
            if (i > j) break;
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
        nums[left] = nums[j];
        nums[j] = pivot;
        return j;
    }
}
