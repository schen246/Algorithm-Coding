import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ClosestBinarySearchTreeValueII {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // M1: sliding window time: O(n) space: O(k + height)
        // M2: two iterators time: O(logn + k) space: O(height)
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Integer> dq = new ArrayDeque<>();
        pushLeft(root, stack);
        while (stack.size() > 0) {
            TreeNode node = stack.pop();
            pushLeft(node.right, stack);
            dq.offerFirst(node.val);
            if (dq.size() > k) {
                if (Math.abs(dq.peekLast() - target) <= Math.abs(dq.peekFirst() - target)) {
                    dq.pollFirst();
                    break;
                }
                dq.pollLast();
            }
        }
        while (dq.size() > 0) {
            res.add(dq.pollLast());
        }
        return res;
    }
    
    private void pushLeft(TreeNode root, Deque<TreeNode> stack) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
}
