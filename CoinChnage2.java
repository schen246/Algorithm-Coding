import java.util.Arrays;

public class CoinChnage2 {
    // M1: dfs - time: O((amount/min(coins))^coins.length) space: O(amount/min(coins))
    public int change(int amount, int[] coins) {
        Arrays.sort(coins);
        int[] res = new int[1];
        dfs(amount, 0, coins, res);
        return res[0];
    }

    private void dfs(int target, int index, int[] coins, int[] res) {
        if (target == 0) {
            res[0]++;
            return;
        }
        for (int i = index; i < coins.length; i++) {
            if (coins[i] > target) {
                return;
            }
            dfs(target - coins[i], i, coins, res);
        }
    }

    // M2: recursion + memo - time: O(amount * n) space: O(amount * n)
    public int change2(int amount, int[] coins) {
        Arrays.sort(coins);
        int[][] memo = new int[amount + 1][coins.length];// memo[i][j] -> combinations of amount i using coins[j,...,end]
        for (int[] i : memo) {
            Arrays.fill(i, -1);
        }
        return dfs(amount, 0, coins, memo);
    }
    
    private int dfs(int amount, int index, int[] coins, int[][] memo) {
        if (memo[amount][index] != -1) {
            return memo[amount][index];
        }
        if (amount == 0) {
            memo[0][index] = 1;
            return 1;
        }
        int res = 0;
        for (int i = index; i < coins.length; i++) {
            if (coins[i] > amount) {
                memo[amount][i] = 0;
                break;
            }
            res += dfs(amount - coins[i], i, coins, memo);
        }
        memo[amount][index] = res;
        return res;
    }

    // M3: dp - time: O(amount * n) space: O(amount * n)
    public int change3(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= coins[i - 1]) {
                     dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[coins.length][amount];
    }

    // M4: dp - time: O(amount * n) space: O(amount)
    public int change4(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = coins[i - 1]; j <= amount; j++) {
                dp[j] += dp[j - coins[i - 1]];
            }
        }
        return dp[amount];
    }
}
