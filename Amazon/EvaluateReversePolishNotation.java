package Amazon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class EvaluateReversePolishNotation {
    // time: O(n) space: O(n)
    Set<String> set = new HashSet<>();
    {
        set.add("+");
        set.add("-");
        set.add("*");
        set.add("/");
    }
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            if (set.contains(token)) {
                int n2 = stack.pop();
                int n1 = stack.pop();
                if (token.equals("+")) {
                    stack.push(n1 + n2);
                } else if (token.equals("-")) {
                    stack.push(n1 - n2);
                } else if (token.equals("*")) {
                    stack.push(n1 * n2);
                } else if (token.equals("/")) {
                    stack.push(n1 / n2);
                }
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }
}
