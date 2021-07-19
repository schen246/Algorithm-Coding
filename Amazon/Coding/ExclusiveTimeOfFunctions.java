package Amazon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class ExclusiveTimeOfFunctions {
    // sweep line + stack - time: O(logs.size() * log.length()) space: O(logs.size())
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Deque<int[]> stack = new ArrayDeque<>();// id, ex-time
        int pre = 0;
        for (String log : logs) {
            String[] strs = log.split(":");
            int id = Integer.valueOf(strs[0]);
            int cur = Integer.valueOf(strs[2]);
            if (strs[1].equals("start")) {
                if (!stack.isEmpty()) {
                    stack.peek()[1] += (cur - pre);
                }
                stack.push(new int[]{id, 0});
                pre = cur;
            } else {
                int[] arr = stack.pop();
                res[arr[0]] += arr[1] + (cur - pre + 1);
                pre = cur + 1;
            }
        }
        return res;
    }
}
