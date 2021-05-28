public class UglyNumber {
    public boolean isUgly(int num) {
        if (num <= 0) {
            return false;
        }
        while (num % 5 == 0 || num % 3 == 0 || num % 2 == 0) {
            if (num % 5 == 0) {
                num /= 5;
            }
            if (num % 3 == 0) {
                num /= 3;
            }
            if (num % 2 == 0) {
                num /= 2;
            }
        }
        return num == 1;
    }
    // time: O(log_5 (n)) space: O(1)
}
