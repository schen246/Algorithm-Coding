public class DivideChocolate {
    // max min problem
    // binary search + arr - time: O((log sum) * n) space: O(1)
    public int maximizeSweetness(int[] sweetness, int k) {
        int left = 0, right = 0;
        for (int i : sweetness) {
            right += i;
        }
        int res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canCut(sweetness, k, mid)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    private boolean canCut(int[] arr, int k, int target) {
        int sum = 0, cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum >= target) {
                sum = 0;
                cnt++;
            }
        }
        return cnt >= k + 1;
    }
}
