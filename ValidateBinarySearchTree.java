import java.util.ArrayDeque;
import java.util.Deque;

public class ValidateBinarySearchTree {
    // M1: recursion
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean helper(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        return helper(root.left, min, root.val) && helper(root.right, root.val, max) && min < root.val && root.val < max;
    }
    // time: O(n) space: O(height)

    // M2: iteration stack
    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        pushLeft(root, stack);
        int pre = Integer.MIN_VALUE;
        while (stack.size() > 0) {
            TreeNode node = stack.pop();
            if (pre > node.val) {
                return false;
            }
            pre = node.val;
            pushLeft(node.right, stack);
        }
        return true;
    }

    private void pushLeft(TreeNode root, Deque<TreeNode> stack) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    // time: O(n) space: O(height)
}
