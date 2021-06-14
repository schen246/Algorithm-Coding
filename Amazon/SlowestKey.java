public class SlowestKey {
    // linear scan - time: O(n) space: O(1)
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        char res = keysPressed.charAt(0);
        int d = releaseTimes[0];
        for (int i = 1; i < keysPressed.length(); i++) {
            char c = keysPressed.charAt(i);
            int cur = releaseTimes[i] - releaseTimes[i - 1];
            if (cur > d || (cur == d && c > res)) {
                res = c;
                d = cur;
            }
        }
        return res;
    }
}
