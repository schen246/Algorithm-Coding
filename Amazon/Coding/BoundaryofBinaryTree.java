package Amazon.Coding;

import java.util.ArrayList;
import java.util.List;

public class BoundaryofBinaryTree {
    // time: O(n) space: O(n)
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        res.add(root.val);
        if (root.left == null && root.right == null) {
            return res;
        }
        findLeft(root.left, res);
        findLeaf(root, res);
        List<Integer> list = new ArrayList<>();
        findRight(root.right, list);
        for (int i = list.size() - 1; i >= 0; i--) {
            res.add(list.get(i));
        }
        return res;
    }
    
    private void findLeaf(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return;
        }
        findLeaf(root.left, res);
        findLeaf(root.right, res);
    }
    
    private void findLeft(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            return;
        }
        res.add(node.val);
        if (node.left != null) {
            findLeft(node.left, res);
        } else if (node.right != null) {
            findLeft(node.right, res);
        }
    }
    
    private void findRight(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            return;
        }
        res.add(node.val);
        if (node.right != null) {
            findRight(node.right, res);
        } else if (node.left != null) {
            findRight(node.left, res);
        }
    }
}
