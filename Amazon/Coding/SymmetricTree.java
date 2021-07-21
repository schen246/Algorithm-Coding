package Amazon.Coding;
import java.util.ArrayDeque;
import java.util.Queue;

public class SymmetricTree {
    // M1: recursion - time: O(n) space: O(height)
    public boolean isSymmetric(TreeNode root) {
        return helper(root, root);
    }
    
    private boolean helper(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return helper(p.left, q.right) && helper(p.right, q.left);
    }

    // M2: iteration - queue - time: O(n) space: O(n)
    public boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode[]> q = new ArrayDeque<>();
        q.offer(new TreeNode[]{root, root});
        while (q.size() > 0) {
            TreeNode[] cur = q.poll();
            if (cur[0] == null && cur[1] == null) {
                continue;
            }
            if (cur[0] == null || cur[1] == null) {
                return false;
            }
            if (cur[0].val != cur[1].val) {
                return false;
            }
            q.offer(new TreeNode[]{cur[0].left, cur[1].right});
            q.offer(new TreeNode[]{cur[0].right, cur[1].left});
        }
        return true;
    }
}
