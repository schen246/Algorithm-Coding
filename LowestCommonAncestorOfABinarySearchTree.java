public class LowestCommonAncestorOfABinarySearchTree {
    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null) {
            return null;
        }
        if (root.val < p && root.val < q) {
            return lowestCommonAncestor(root.right, p, q);
        }
        if (root.val > p && root.val > q) {
            return lowestCommonAncestor(root.left, p, q);
        }
        return root;
    }
    // recursion - time: O(n) space: O(n)

    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        while (root != null) {
            if (root.val < p && root.val < q) {
                root = root.right;
            } else if (root.val > p && root.val > q) {
                root = root.left;
            } else {
                return root;
            }
        }
        return null;
    }
    // iteration - time: O(n) space: O(1)
}
