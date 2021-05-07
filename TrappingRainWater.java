public class TrappingRainWater {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int i = 0, j = height.length - 1, leftMax = 0, rightMax = 0, res = 0;
        while (i < j) {
            leftMax = Math.max(leftMax, height[i]);
            rightMax = Math.max(rightMax, height[j]);
            if (leftMax < rightMax) {
                res += (leftMax - height[i++]);
            } else {
                res += (rightMax - height[j--]);
            }
        }
        return res;
    }
    // M1: BF O(n^2) time, O(1) space
    // M2: DP O(n) time, O(n) space
    // M3: Two Pointers O(n) time, O(1) space
}
