public class TwoSumIVInputIsABST {
    public boolean findTarget(TreeNode root, int k) {
        // M1: pre/in/post/level order traversal + set, time: O(n) space: O(n)
        // M2: two pointers + two stacks, time: O(n) space: O(n)
        if (root == null) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        set.add(root.val);
        while (q.size() > 0) {
            TreeNode node = q.poll();
            if (node.left != null) {
                if (set.contains(k - node.left.val)) {
                    return true;
                }
                q.offer(node.left);
                set.add(node.left.val);
            }
            if (node.right != null) {
                if (set.contains(k - node.right.val)) {
                    return true;
                }
                q.offer(node.right);
                set.add(node.right.val);
            }
        }
        return false;
    }
}
