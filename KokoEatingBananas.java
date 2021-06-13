public class KokoEatingBananas {
    // binary search - time: O(n log (max pile)) space: O(1)
    public int minEatingSpeed(int[] piles, int H) {
        int left = 0, right = Integer.MIN_VALUE;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        int res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canEat(piles, H, mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
    
    private boolean canEat(int[] piles, int H, int speed) {
        int cnt = 0;
        for (int pile : piles) {
            cnt += (int)Math.ceil((double)pile / speed);
        }
        return cnt <= H;
    }
}
