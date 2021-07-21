import java.util.ArrayDeque;
import java.util.Queue;

public class FindNearestRightNodeinBinaryTree {
    // bfs - time: O(n) space: O(n)
    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (q.size() > 0) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (cur == u) {
                    if (i == size - 1) {
                        return null;
                    }
                    return q.poll();
                }
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }
        return null;
    }
}
