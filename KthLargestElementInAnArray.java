public class KthLargestElementInAnArray {
    // M1: sort O(nlogn) M2: minHeap O(nlogk) M3: quickSelect O(n) average 
    public int findKthLargest(int[] nums, int k) {
	    return quickSelect(nums, 0, nums.length - 1, k - 1);
	}
	
	private int quickSelect(int[] nums, int left, int right, int k) {
	    int mid = partition(nums, left, right);
	    if (mid == k) {
	        return nums[mid];
	    }
	    if (mid < k) {
	        return quickSelect(nums, mid + 1, right, k);
	    }
	    return quickSelect(nums, left, mid - 1, k);
	}
	
	private int partition(int[] nums, int left, int right) {
	    int val = nums[left];
	    int i = left + 1, j = right;
	    while (i <= j) {
	        while (i <= j && nums[i] > val) {
	            i++;
	        }
	        while (i <= j && nums[j] <= val) {
	            j--;
	        }
	        if (i > j) {
	            break;
	        }
	        swap(nums, i, j);
	    }
	    swap(nums, j, left);
	    return j;
	}
	
	private void swap(int[] nums, int i, int j) {
	    int tmp = nums[i];
	    nums[i] = nums[j];
	    nums[j] = tmp;
	}
    // recursion time: O(n) average space: O(logn) average

    private int quickSelect2(int[] nums, int left, int right, int k) {
        while (left <= right) {
            int mid = partition(nums, left, right);
            if (mid == k) {
                return nums[mid];
            }
            if (mid < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    // iteration time: O(n) average space: O(1)
}
