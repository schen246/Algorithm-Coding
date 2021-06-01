import java.util.Deque;

public class LargestRectangleInHistogram {
    // monoStack: increasing time: O(n) space: O(n)
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int h = heights[stack.pop()];
                int left = stack.isEmpty() ? 0 : stack.peek() + 1;
                res = Math.max(res, h * (i - left));
            }
        }
        int right = heights.length;
        while (!stack.isEmpty()) {
            int h = heights[stack.pop()];
            int left = stack.isEmpty() ? 0 : stack.peek() + 1;
            res = Math.max(res, h * (right - left));
        }
        return res;
    }
}
