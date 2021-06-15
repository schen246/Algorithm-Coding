import java.util.ArrayDeque;
import java.util.Deque;

public class MaximalRectangle {
    // time: O(m * n) space: O(n)
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[] prefixSum = new int[n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefixSum[j] = matrix[i][j] == '0' ? 0 : prefixSum[j] + 1;
            }
            res = Math.max(res, largestArea(prefixSum));
        }
        return res;
    }

    private int largestArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int h = heights[stack.pop()];
                int left = stack.isEmpty() ? 0 : stack.peek() + 1;
                res = Math.max(res, h * (i - left));
            }
            stack.push(i);
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
