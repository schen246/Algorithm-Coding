package Amazon.Coding;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {
    // stack - time: O(n) space: O(n)
    public String simplifyPath(String path) {
        String[] strs = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for (String str : strs) {
            if (str.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else if (!str.equals("") && !str.equals(".") && !str.equals("..")) {
                stack.push(str);
            }
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }
        return res.equals("") ? "/" : res;
    }
}
