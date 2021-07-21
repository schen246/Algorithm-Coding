package Amazon.Coding;

public class IntegertoRoman {
    private static final int[] VALUE = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] STR = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    
    // time: O(n) n is small so O(1) space: O(1)
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < VALUE.length && num > 0; i++) {
            while (VALUE[i] <= num) {
                num -= VALUE[i];
                sb.append(STR[i]);
            }
        }
        return sb.toString();
    }
}
