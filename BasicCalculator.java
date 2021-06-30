import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {
    // M1: recursion - time: O(n) space: O(n)
    // 32 + -32
    int index;
    public int calculate(String s) {
        index = 0;
        return helper(s);
    }

    private int helper(String s) {
        int cur = 0, res = 0, sign = 1;
        while (index < s.length()) {
            char ch = s.charAt(index);
            index++;
            if (ch >= '0' && ch <= '9') {
                cur = cur * 10 + (ch - '0');
            } else if (ch == '(') {
                cur = helper(s);
            } else if (ch == ')') {
                break;
            } else if (ch == '+') {
                res += sign * cur;
                sign = 1;
                cur = 0;
            } else if (ch == '-') {
                res += sign * cur;
                sign = -1;
                cur = 0;
            }
        }
        return res + sign * cur;
    }


    // M2: iteration - time: O(n) space: O(n)
    public int calculate2(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int cur = 0, res = 0, sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                cur = cur * 10 + (ch - '0');
            } else if (ch == '(') {
                stack.push(res);
                stack.push(sign);
                sign = 1;
                res = 0;
            } else if (ch == ')') {
                res += sign * cur;
                res = res * stack.pop() + stack.pop();
                sign = 1;
                cur = 0;
            } else if (ch == '+') {
                res += sign * cur;
                sign = 1;
                cur = 0;
            } else if (ch == '-') {
                res += sign * cur;
                sign = -1;
                cur = 0;
            }
        }
        return res + sign * cur;
    }
}
