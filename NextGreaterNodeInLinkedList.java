import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class NextGreaterNodeInLinkedList {
    // monoStack - time: O(n) space: O(n)
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[list.size()];
        int i = 0;
        while (i < list.size()) {
            while (!stack.isEmpty() && list.get(stack.peek()) < list.get(i)) {
                res[stack.pop()] = list.get(i);
            }
            stack.push(i);
            i++;
        }
        return res;
    }
}
