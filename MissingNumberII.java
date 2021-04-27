public class MissingNumberII {
    public int missing(int[] array) {
        if (array == null) {
            return -1;
        }
        int left = 0, right = array.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] <= mid + 1) {
                left = mid + 1;
            } else {
                res = array[mid] - 1;
                right = mid - 1;
            }
        }
        return res == -1 ? array.length + 1 : res;
    }
}
