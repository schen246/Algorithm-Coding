public class SerializeAndDeserializeBST {
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (q.size() > 0) {
            TreeNode node = q.poll();
            if (node == null) {
                sb.append("null");
            } else {
                sb.append(node.val);
                q.offer(node.left);
                q.offer(node.right);
            }
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
    // time: O(n) space: O(n)

    public TreeNode deserialize(String data) {
        String[] strs = data.split(",");
        if (strs.length == 1 && strs[0].equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(strs[0]));
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int i = 1;
        while (i < strs.length) {
            TreeNode node = q.poll();
            if (!strs[i].equals("null")) {
                node.left = new TreeNode(Integer.valueOf(strs[i]));
                q.offer(node.left);
            }
            i++;
            if (!strs[i].equals("null")) {
                node.right = new TreeNode(Integer.valueOf(strs[i]));
                q.offer(node.right);
            }
            i++;
        }
        return root;
    }
    // time: O(n) space: O(n)
}
