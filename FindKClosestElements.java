import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements {
    // M1: binary search + sliding window - time: O(logn) + O(k) space: O(1)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // binary search find closest
        int left = 0, right = arr.length - 1, index = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (Math.abs(arr[mid] - x) < Math.abs(arr[index] - x) || (Math.abs(arr[mid] - x) == Math.abs(arr[index] - x) && mid < index)) {
                index = mid;
            }
            if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // sliding window find k closest
        int i = index - 1, j = index + 1, len = 1;
        while (i >= 0 && j < arr.length && len < k) {
            if (Math.abs(arr[i] - x) <= Math.abs(arr[j] - x)) {
                i--;
            } else {
                j++;
            }
            len++;
        }
        while (i >= 0 && len < k) {
            i--;
            len++;
        }
        while (j < arr.length && len < k) {
            j++;
            len++;
        }
        // add to res
        List<Integer> res = new ArrayList<>();
        for (int q = i + 1; q < j; q++) {
            res.add(arr[q]);
        }
        return res;
    }

    // M2: sliding window - time: O(n) space: O(1)
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        int index = 0;
        for (int i = k; i < arr.length; i++) {
            if (arr[i - k] == arr[i]) {
                index = i - k;
                continue;
            }
            if (Math.abs(arr[i - k] - x) <= Math.abs(arr[i] - x)) {
                index = i - k;
                break;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = index; i < index + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }
}
