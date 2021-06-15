public class InsertIntoABinarySearchTree {
    // M1: recursion time: O(height) space: O(height)
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
    
    // M2: iteration time: O(height) space: O(height)
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode res = root;
        while (root != null) {
            if (val < root.val) {
                if (root.left == null) {
                    root.left = new TreeNode(val);
                    return res;
                }
                root = root.left;
            } else {
                if (root.right == null) {
                    root.right = new TreeNode(val);
                    return res;
                }
                root = root.right;
            }
        }
        return res;
    }
}
