import java.util.ArrayDeque;
import java.util.Deque;

public class IncreasingOrderSearchTree {
    // inorder traversal - time: O(n) space: O(height)
    public TreeNode increasingBST(TreeNode root) {
        TreeNode d = new TreeNode(-1);
        TreeNode pre = d;
        Deque<TreeNode> stack = new ArrayDeque();
        pushLeft(root, stack);
        while (stack.size() > 0) {
            TreeNode cur = stack.pop();
            pushLeft(cur.right, stack);
            pre.right = cur;
            cur.left = null;
            pre = cur;
        }
        return d.right;
    }
    
    private void pushLeft(TreeNode root, Deque<TreeNode> stack) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
}
