public class MergeSortAnArray {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        return mergeSort(nums, 0, nums.length - 1);
    }
    
    private int[] mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return new int[]{nums[left]};
        }
        int mid = left + (right - left) / 2;
        int[] resLeft = mergeSort(nums, left, mid);
        int[] resRight = mergeSort(nums, mid + 1, right);
        return merge(resLeft, resRight);
    }
    
    private int[] merge(int[] arr1, int[] arr2) {
        int[] res = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                res[k++] = arr1[i++];
            } else {
                res[k++] = arr2[j++];
            }
        }
        while (i < arr1.length) {
            res[k++] = arr1[i++];
        }
        while (j < arr2.length) {
            res[k++] = arr2[j++];
        }
        return res;
    }
}
