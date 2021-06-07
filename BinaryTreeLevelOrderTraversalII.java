public class BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (q.size() > 0) {
            int size = q.size();
            List<Integer> cur = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                cur.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            res.add(cur);
        }
        Collections.reverse(res);
        return res;
    }
    // time: O(n) space: O(n)
}
