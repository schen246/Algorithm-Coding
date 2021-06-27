public class CountOfRangeSum {
    // M1: prefix sum + BF - time: O(n^2) space: O(n)
    // M2: prefix sum + merge sort - divide and conquer - time: O(nlogn) space: O(n)
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        long[] help = new long[n + 1];
        int[] res = new int[1];
        mergeSort(0, n, lower, upper, prefixSum, help, res);
        return res[0];
    }
    
    private void mergeSort(int left, int right, int lower, int upper, long[] prefixSum, long[] help, int[] res) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(left, mid, lower, upper, prefixSum, help, res);
        mergeSort(mid + 1, right, lower, upper, prefixSum, help, res);
        merge(left, mid, right, lower, upper, prefixSum, help, res);
    }
    
    private void merge(int left, int mid, int right, int lower, int upper, long[] prefixSum, long[] help, int[] res) {
        // count of range sum
        int i = left, j = mid + 1, k = mid + 1;
        while (i <= mid) {
            while (j <= right && prefixSum[j] - prefixSum[i] < lower) {
                j++;
            }
            while (k <= right && prefixSum[k] - prefixSum[i] <= upper) {
                k++;
            }
            res[0] += k - j;
            i++;
        }
        // merge
        int l = left, r = mid + 1, index = left;
        while (l <= mid && r <= right) {
            if (prefixSum[l] <= prefixSum[r]) {
                help[index++] = prefixSum[l++];
            } else {
                help[index++] = prefixSum[r++];
            }
        }
        while (l <= mid) {
            help[index++] = prefixSum[l++];
        }
        while (r <= right) {
            help[index++] = prefixSum[r++];
        }
        for (int p = left; p <= right; p++) {
            prefixSum[p] = help[p];
        }
    }
}
