public class ClosestBinarySearchTreeValue {
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return -1;
        }
        int res = root.val;
        while (root != null) {
            if (root.val == target) {
                return root.val;
            }
            if (Math.abs(root.val - target) < Math.abs(res - target)) {
                res = root.val;
            }
            if (root.val < target) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return res;
    }
    // time: O(n) space: O(1)
}
