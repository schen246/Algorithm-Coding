import java.util.Arrays;

public class Heaters {
    // time: O(heaters.length * log(heaters.length) + houses.length * log(heaters.length))
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int res = 0;
        for (int house : houses) {
            int index = smallestLargerOrEqualThanTarget(heaters, house);
            int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
            int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;
            res = Math.max(res, Math.min(dist1, dist2));
        }
        return res;
    }

    private int smallestLargerOrEqualThanTarget(int[] arr, int target) {
        int left = 0, right = arr.length - 1, res = arr.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}
