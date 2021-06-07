public class PowXN {
    // x can be neg/pos, n is integer
    public double myPow(double x, int n) {
        long N = n;
        if (n < 0) {
            x = 1 / x;
            N = -N;
        }
        return helper(x, N);
    }

    // M1: recursion - time: O(logn) space: O(logn)
    private double helper(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double cur = helper(x, n / 2);
        if (n % 2 == 1) {
            return cur * cur * x;
        }
        return cur * cur;
    }

    // M2: bit representation - time: O(logn) space: O(1)
    // x^n = x^(b1*1 + b2*2 + b3*4 + ...) = b1x^1 * b2x^2 * b3x^4 * ... = multiply(bi x^(2^(i - 1)))
    private double helper2(double x, long n) {
        double res = 1.0;
        while (n != 0) {
            if ((n & 1) == 1) {
                res *= x;
            }
            n >>= 1;
            x *= x;
        }
        return res;
    }
}