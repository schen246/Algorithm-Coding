public class VerifyPreorderSerializationOfABinaryTree {
    // time: O(n) space: O(n)
    public boolean isValidSerialization(String preorder) {
        String[] strs = preorder.split(",");
        int cnt = 1;
        for (String str : strs) {
            cnt--;
            if (cnt < 0) {
                return false;
            }
            if (!str.equals("#")) {
                cnt += 2;
            }
        }
        return cnt == 0;
    }
}
