public class MaximumBinaryTree {
    // divide and conquer - time: average O(n * log n) space: O(log n)
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }
    
    private TreeNode construct(int[] nums, int i, int j) {
        if (i > j) {
            return null;
        }
        int mid = findMax(nums, i, j);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = construct(nums, i, mid - 1);
        root.right = construct(nums, mid + 1, j);
        return root;
    }
    
    private int findMax(int[] nums, int i, int j) {
        int res = nums[i], ans = i;
        for (int index = i; index <= j; index++) {
            if (nums[index] > res) {
                res = nums[index];
                ans = index;
            }
        }
        return ans;
    }
}
