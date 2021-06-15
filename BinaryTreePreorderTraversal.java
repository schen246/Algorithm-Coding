import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        pushLeft(root, stack, res);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            pushLeft(cur.right, stack, res);
        }
        return res;
    }
    
    private void pushLeft(TreeNode root, Deque<TreeNode> stack, List<Integer> res) {
        while (root != null) {
            res.add(root.val);
            stack.push(root);
            root = root.left;
        }
    }
}
