public class DecodeString {
    // M1: recursion - time: O(n * k) space: O(n)
    int index = 0;
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int cur = 0;
        while (index < s.length()) {
            char ch = s.charAt(index);
            if (ch >= '0' && ch <= '9') {
                cur = cur * 10 + (ch - '0');
                index++;
            } else if (ch == '[') {
                index++;
                String sub = decodeString(s);
                for (int j = 0; j < cur; j++) {
                    sb.append(sub);
                }
                cur = 0;
            } else if (ch == ']') {
                index++;
                return sb.toString();
            } else {
                sb.append(ch);
                index++;
            }
        }
        return sb.toString();
    }
}
