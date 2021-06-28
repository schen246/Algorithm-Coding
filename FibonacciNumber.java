public class FibonacciNumber {
    // M1: recursion + memo - time: O(n) space: O(n)
    public int fib(int n) {
        int[] memo = new int[n + 1];
        return dfs(n, memo);
    }
    
    private int dfs(int n, int[] memo) {
        if (n == 0 || n == 1) {
            return n;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = dfs(n - 1, memo) + dfs(n - 2, memo);
        return memo[n];
    }

    // M2: dp - time: O(n) space: O(n)
    public int fib2(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            if (i == 0 || i == 1) {
                dp[i] = i;
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[n];
    }

    // M3: optimized dp - time: O(n) space: O(1)
    public int fib3(int n) {
        if (n == 0) return 0;
        int num1 = 0, num2 = 1;
        for (int i = 2; i <= n; i++) {
            int temp = num2;
            num2 += num1;
            num1 = temp;
        }
        return num2;
    }
}
