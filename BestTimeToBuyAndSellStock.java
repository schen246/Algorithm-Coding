public class BestTimeToBuyAndSellStock {
    // time: O(n) space: O(1)
    public int maxProfit(int[] prices) {
        int minVal = prices[0], res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minVal) {
                minVal = prices[i];
            } else {
                res = Math.max(res, prices[i] - minVal);
            }
        }
        return res;
    }
}
