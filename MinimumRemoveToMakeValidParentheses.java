import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumRemoveToMakeValidParentheses {
    // M1: stack - time: O(n) space: O(n)
    public String minRemoveToMakeValid(String s) {
        Deque<Integer> stack = new ArrayDeque<>();// invalid index of '(' or ')'
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else if (ch == ')') {
                if (stack.isEmpty() || s.charAt(stack.peek()) == ')') {
                    stack.push(i);
                } else {
                    stack.pop();
                }
            }
        }
        char[] res = s.toCharArray();
        while (!stack.isEmpty()) {
            res[stack.pop()] = '#';
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (res[i] != '#') {
                sb.append(res[i]);
            }
        }
        return sb.toString();
    }
}
