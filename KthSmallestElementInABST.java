public class KthSmallestElementInABST {
    public int kthSmallest(TreeNode root, int k) {
        // inorder traversal iteratively, time: O(height + k) space: O(height)
        Deque<TreeNode> stack = new ArrayDeque<>();
        pushLeft(root, stack);
        while (stack.size() > 0) {
            TreeNode node = stack.pop();
            k--;
            if (k == 0) {
                return node.val;
            }
            pushLeft(node.right, stack);
        }
        return -1;
    }

    private void pushLeft(TreeNode root, Deque<TreeNode> stack) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
}
