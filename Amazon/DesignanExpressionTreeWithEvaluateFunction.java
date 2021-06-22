package Amazon;

import java.util.ArrayDeque;
import java.util.Deque;

public class DesignanExpressionTreeWithEvaluateFunction {
    abstract class Node {
        public abstract int evaluate();
    }
    
    class TreeNode extends Node {
        String val;
        TreeNode left;
        TreeNode right;
        public TreeNode(String val) {
            this.val = val;
        }
        
        @Override
        public int evaluate() {
            return dfs(this);
        }
        
        private int dfs(TreeNode root) {// time: O(n) space: O(height)
            if (root.left == null && root.right == null) {
                return Integer.valueOf(root.val);
            }
            int left = dfs(root.left);
            int right = dfs(root.right);
            String opr = root.val;
            int res = 0;
            if (opr.equals("+")) {
                res = left + right;
            } else if (opr.equals("-")) {
                res = left - right;
            } else if (opr.equals("*")) {
                res = left * right;
            } else if (opr.equals("/")) {
                res = left / right;
            }
            return res;
        }
    }

    Deque<TreeNode> stack = new ArrayDeque<>();
    Node buildTree(String[] postfix) {
        for (String str : postfix) {
            if ("+-*/".contains(str)) {
                TreeNode cur = new TreeNode(str);
                cur.right = stack.pop();
                cur.left = stack.pop();
                stack.push(cur);
            } else {
                stack.push(new TreeNode(str));
            }
        }
        return stack.pop();
    }
}
