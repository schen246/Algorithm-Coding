import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        pushLeft(root, stack);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            if (stack.peek().right != null && stack.peek().right != prev) {
                pushLeft(stack.peek().right, stack);
            } else {
                TreeNode cur = stack.pop();
                res.add(cur.val);
                prev = cur;
            }
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
