public class LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null || root.val == p || root.val == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
    // time: O(n) space: O(height)
}

// follow up: if not guarantee p,q in the tree
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        Node res = helper(root, p, q);
        return res.cnt == 2 ? res.node : null;
    }
    
    private Node helper(TreeNode root, int p, int q) {
        if (root == null) {
            return new Node(null, 0);
        }
        Node left = helper(root.left, p, q);
        Node right = helper(root.right, p, q);
        if (root.val == p || root.val == q) {
            return new Node(root, left.cnt + right.cnt + 1);
        }
        if (left.node != null && right.node != null) {
            return new Node(root, 2);
        }
        return left.node == null ? right : left;
    }
}

class Node {
    TreeNode node;
    int cnt;
    public Node(TreeNode node, int cnt) {
        this.node = node;
        this.cnt = cnt;
    }
}
