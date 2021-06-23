import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString {
    // M1: recursion - time: O(n * k) space: O(n)
    int index = 0;
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int cur = 0;
        while (index < s.length()) {
            char ch = s.charAt(index);
            if (ch >= '0' && ch <= '9') {
                cur = cur * 10 + (ch - '0');
                index++;
            } else if (ch == '[') {
                index++;
                String sub = decodeString(s);
                for (int j = 0; j < cur; j++) {
                    sb.append(sub);
                }
                cur = 0;
            } else if (ch == ']') {
                index++;
                return sb.toString();
            } else {
                sb.append(ch);
                index++;
            }
        }
        return sb.toString();
    }

    // M2: stack - k[string] - time: O(k^count * n) space: O(k^count * n)
    public String decodeString2(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ']') {
                StringBuilder sb = new StringBuilder();
                while (stack.peek() != '[') {
                    sb.append(stack.pop());// reversed order string
                }
                stack.pop();// pop '['
                int k = 0;
                int base = 1;
                while (!stack.isEmpty() && stack.peek() >= '0' && stack.peek() <= '9') {
                    k += (stack.pop() - '0') * base;
                    base *= 10;
                }
                while (k > 0) {
                    for (int j = sb.length() - 1; j >= 0; j--) {
                        stack.push(sb.charAt(j));
                    }
                    k--;
                }
            } else {
                stack.push(ch);
            }
        }
        int size = stack.size();
        char[] res = new char[size];
        for (int i = size - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return new String(res);
    }

    // M3: two stacks (countStack, stringStack) - time: O(k * n) space: O(n)
    public String decodeString3(String s) {
        Deque<Integer> countStack = new ArrayDeque<>();// k
        Deque<String> stringStack = new ArrayDeque<>();// substring before k
        int k = 0;
        String cur = "";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                countStack.push(k);
                stringStack.push(cur);
                k = 0;
                cur = "";
            } else if (ch == ']') {
                String str = stringStack.pop();
                int num = countStack.pop();
                while (num > 0) {
                    str += cur;
                    num--;
                }
                cur = str;
            } else {
                cur += ch;
            }
        }
        return cur;
    }
}
