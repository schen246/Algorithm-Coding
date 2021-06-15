import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    // find circle in a series of number
    // 2 -> 4 -> 16 -> 37 -> 58 -> 89 -> 145 -> 42 -> 20 -> 4
    // M1: set - time: O(L) space: O(L)
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            int cur = calculate(n);
            if (!set.add(cur)) {
                return false;
            }
            n = cur;
        }
        return true;
    }
    
    private int calculate(int n) {
        int res = 0;
        while (n != 0) {
            int rem = n % 10;
            n /= 10;
            res += rem * rem;
        }
        return res;
    }

    // M2: two pointers - time: O(L) space: O(1)
    public boolean isHappy2(int n) {
        int s = n;
        int f = calculate(n);
        while (f != 1 && f != s) {
            s = calculate(s);
            f = calculate(calculate(f));
        }
        return f == 1;
    }
}
