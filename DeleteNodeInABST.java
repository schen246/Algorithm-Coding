public class DeleteNodeInABST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode cur = root.right, pre = cur;
            while (cur != null) {
                pre = cur; 
                cur = cur.left;
            }
            pre.left = root.left;
            return root.right;
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }
    // time: O(H) = find upper part + delete lower part
    // space: O(H) call stack
}
