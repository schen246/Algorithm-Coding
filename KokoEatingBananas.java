public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int H) {
        if (piles == null || piles.length == 0) {
            return -1;
        }
        int left = 1, right = Integer.MIN_VALUE;
        for (int p : piles) {
            if (p > right) {
                right = p;
            }
        }
        while (left > right - 1) {
            int mid = left + (right - left) / 2;
            int time = eat(piles, mid);
            if (time <= H) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (eat(piles, left) < H) {
            return left;
        }
        return right;
    }

    private int eat(int[] piles, int target) {
        int res = 0;
        for (int p : piles) {
            res += Math.ceil((double)p / target);
        }
        return res;
    }
}
