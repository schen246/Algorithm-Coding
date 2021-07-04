public class MaximumPointsYouCanObtainFromCards {
    // M1: sliding window - time: O(k) space: O(1)
    public int maxScore(int[] cardPoints, int k) {
        int i = 0, j = k - 1;
        int sum = 0;
        for (int index = i; index <= j; index++) {
            sum += cardPoints[index];
        }
        int res = sum, n = cardPoints.length;
        while (j >= 0) {
            sum -= cardPoints[j];
            i = j - k + n;
            sum += cardPoints[i];
            res = Math.max(res, sum);
            j--;
        }
        return res;
    }

    // M2: dp - time: O(k) space: O(k)
    public int maxScore2(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] front = new int[k + 1];
        int[] back = new int[k + 1];
        for (int i = 0; i < k; i++) {
            front[i + 1] = front[i] + cardPoints[i];
            back[i + 1] = back[i] + cardPoints[n - 1 - i];
        }
        int res = 0;
        for (int i = 0; i <= k; i++) {
            int cur = front[i] + back[k - i];
            res = Math.max(res, cur);
        }
        return res;
    }
}
