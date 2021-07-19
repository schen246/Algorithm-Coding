package Amazon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class BasicCalculatorII {
    // stack<Integer> - time: O(n) space: O(n)
    Set<Character> set = new HashSet<>();
    {
        set.add('+');
        set.add('-');
        set.add('*');
        set.add('/');
    }
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int cur = 0;
        char opr = '+';
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                cur = cur * 10 + (ch - '0');
            }
            if (set.contains(ch) || i == s.length() - 1) {
                if (opr == '+') {
                    stack.push(cur);
                } else if (opr == '-') {
                    stack.push(-cur);
                } else if (opr == '*') {
                    stack.push(stack.pop() * cur);
                } else if (opr == '/') {
                    stack.push(stack.pop() / cur);
                }
                cur = 0;
                opr = ch;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
