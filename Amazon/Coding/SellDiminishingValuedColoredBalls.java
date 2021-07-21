package Amazon.Coding;
import java.util.Arrays;

public class SellDiminishingValuedColoredBalls {
    int M = 1000000007;
    public int maxProfit(int[] inventory, int orders) {
        Arrays.sort(inventory);
        long res = 0;
        for (int i = inventory.length - 1; i > 0; i--) {
            int area = (inventory[i] - inventory[i - 1]) * (inventory.length - i);
            if (area >= orders) {
                res += helper(orders, inventory.length - i, inventory[i]);
                return (int)(res % M);
            } else {
                res += helper(area, inventory.length - i, inventory[i]);
                orders -= area;
            }
        }
        res += helper(orders, inventory.length, inventory[0]);
        return (int)(res % M);
    }
    
    private int helper(int cnt, int width, int top) {
        long res = 0;
        int n1 = cnt / width;
        int n2 = cnt % width;
        res += width * ((long)top + (top - n1 + 1)) * n1 / 2 + n2 * ((long)top - n1);
        return (int)(res % M);
    }
    // time: O(nlogn) + O(n) space: O(n) depends on sorting algorithm
}
