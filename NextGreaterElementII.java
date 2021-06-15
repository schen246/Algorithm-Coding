import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class NextGreaterElementII {
    // monoStack - time: O(n) space: O(n)
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(nums, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                res[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        int i = 0;
        while (!stack.isEmpty() && i < nums.length) {
            if (nums[i] > nums[stack.peek()]) {
                res[stack.pop()] = nums[i];
            } else {
                i++;
            }
        }
        return res;
    }
}
