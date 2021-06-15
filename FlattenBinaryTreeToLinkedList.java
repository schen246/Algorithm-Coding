public class FlattenBinaryTreeToLinkedList {
    // M1: recursion - time: O(n) space: O(height)
    public void flatten(TreeNode root) {
        helper(root);
    }
    
    private TreeNode[] helper(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return new TreeNode[]{root, root};
        }
        TreeNode[] left = helper(root.left);
        TreeNode[] right = helper(root.right);
        if (left != null && right != null) {
            root.left = null;
            root.right = left[0];
            left[1].left = null;
            left[1].right = right[0];
            return new TreeNode[]{root, right[1]};
        }
        if (left != null) {
            root.left = null;
            root.right = left[0];
            return new TreeNode[]{root, left[1]};
        }
        root.right = right[0];
        return new TreeNode[]{root, right[1]};
    }

    // M2: recursion - time: O(n) space: O(height)
    public void flatten2(TreeNode root) {
        helper2(root);
    }
    
    private TreeNode helper2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftEnd = helper2(root.left);
        TreeNode rightEnd = helper2(root.right);
        if (leftEnd != null && rightEnd != null) {
            leftEnd.right = root.right;
            root.right = root.left;
            root.left = null;
            return rightEnd;
        }
        if (leftEnd != null) {
            root.right = root.left;
            root.left = null;
            return leftEnd;
        }
        if (rightEnd != null) {
            return rightEnd;
        }
        return root;
    }
}
