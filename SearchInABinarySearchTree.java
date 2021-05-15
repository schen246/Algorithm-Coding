public class SearchInABinarySearchTree {
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val) {
                return root;
            }
            if (root.val < val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return null;
    }
    // time: O(n) space: O(1)
}
