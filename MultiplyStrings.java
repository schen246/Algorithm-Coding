public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        // assumption:
        // - num1, num2 -> no leading zero
        // - character digit only 0-9
        // - non-negative
        // example:
        // 1 2 3
        // 4 5 6
        // i + j + 1: a[i] * b[j] + res[i + j + 1] -> cur -> cur % 10
        // i + j: res[i + j] + cur / 10
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) {
            return "";
        }
        char[] a1 = num1.toCharArray();
        char[] a2 = num2.toCharArray();
        int[] res = new int[a1.length + a2.length];
        for (int i = a1.length - 1; i >= 0; i--) {
            for (int j = a2.length - 1; j >= 0; j--) {
                int cur = (a1[i] - '0') * (a2[j] - '0') + res[i + j + 1];
                res[i + j + 1] = cur % 10;
                res[i + j] += cur / 10;
            }
        }
        // remove leading zero in res
        StringBuilder sb = new StringBuilder();
        boolean seen = false;
        for (int digit : res) {
            if (digit == 0 && !seen) continue;
            sb.append((char)(digit + '0'));
            seen = true;
        }
        return sb.length() == 0 ? "0" : sb.toString();
        // time: O(m * n) space: O(m + n)
    }
}
