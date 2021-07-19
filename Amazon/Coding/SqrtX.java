package Amazon;
public class SqrtX {
    //M1: binary search - time: O(logn) space: O(1)
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        int left = 2, right = x / 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long cur = (long)mid * mid;
            if (cur == x) {
                return mid;
            }
            if (cur < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    // M2: newton's method - time: O(logn) space: O(1)
    // x_i+1 = (x_i + x / x_i) / 2; -> converge with difference < 1
    public int mySqrt2(int x) {
        if (x < 2) {
            return x;
        }
        double x0 = x;
        double x1 = (x0 + x / x0) / 2.0;
        while (Math.abs(x0 - x1) >= 1) {
            x0 = x1;
            x1 = (x0 + x / x0) / 2.0;
        }
        return (int)x1;
    }
}
