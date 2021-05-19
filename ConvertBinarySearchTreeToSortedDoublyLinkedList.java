public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
    // M1: iteration time: O(n) space: O(H)
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return root;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        pushLeft(root, stack);
        TreeNode head = stack.peek();
        TreeNode cur = null;
        while (stack.size() > 0) {
            cur = stack.pop();
            pushLeft(cur.right, stack);
            if (stack.size() > 0) {
                cur.right = stack.peek();
                stack.peek().left = cur;
            }
        }
        if (cur != null) {
            cur.right = head;
            head.left = cur;
        }
        return head;
    }

    private void pushLeft(TreeNode root, Deque<TreeNode> stack) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    
    // M2: recursion time: O(n) space: O(H)
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode[] res = helper(root);
        res[1].right = res[0];
        res[0].left = res[1];
        return res[0];
    }

    private TreeNode[] helper(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode[] left = helper(root.left);
        TreeNode[] right = helper(root.right);
        TreeNode start = root, end = root;
        if (left != null) {
            root.left = left[1];
            left[1].right = root;
            start = left[0];
        }
        if (right != null) {
            root.right = right[0];
            right[0].left = root;
            end = right[1];
        }
        return new TreeNode[]{start, end};
    }
}
