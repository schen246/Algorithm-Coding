import java.util.ArrayDeque;
import java.util.Deque;

public class BinarySearchTreeIterator {
    Deque<TreeNode> stack;// space: O(height)
    public BinarySearchTreeIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    
    public int next() {// n calls n nodes, average O(1) for each call
        TreeNode cur = stack.pop();
        int res = cur.val;
        cur = cur.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return res;
    }
    
    public boolean hasNext() {// O(1)
        return !stack.isEmpty();
    }
}
