public class CapacityToShipPackagesWithinDDays {
    // assume weights.length >= D
    // binary search - time: O(logn * n) space: O(1)
    public int shipWithinDays(int[] weights, int D) {
        int left = 0, right = 0;
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }
        int res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canShip(weights, D, mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private boolean canShip(int[] weights, int D, int capacity) {
        int sum = 0, cnt = 1;
        for (int weight : weights) {
            sum += weight;
            if (sum > capacity) {
                cnt++;
                sum = weight;
            }
        }
        return cnt <= D;
    }
}
