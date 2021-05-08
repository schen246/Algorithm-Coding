public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (q.size() > 0) {
            int n = q.size();
            List<Integer> cur = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = q.poll();
                cur.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            res.add(cur);
        }
        return res;
    }
    // time: O(n) space: O(n)
}
