public class AddStrings {
    public String addStrings(String num1, String num2) {
        int p1 = num1.length() - 1, p2 = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        int rem = 0;
        while (p1 >= 0 || p2 >= 0 || rem != 0) {
            if (p1 >= 0) {
                rem += num1.charAt(p1) - '0';
                p1--;
            }
            if (p2 >= 0) {
                rem += num2.charAt(p2) - '0';
                p2--;
            }
            sb.insert(0, rem % 10);
            rem /= 10;
        }
        return sb.toString();
    }
}
