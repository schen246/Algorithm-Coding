public class SlowestKey {
    // time: O(n) space: O(1)
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        char res = keysPressed.charAt(0);
        int longest = releaseTimes[0];
        for (int i = 1; i < keysPressed.length(); i++) {
            char cur = keysPressed.charAt(i);
            int time = releaseTimes[i] - releaseTimes[i - 1];
            if (time > longest) {
                longest = time;
                res = cur;
            } else if (time == longest && cur > res) {
                res = cur;
            }
        }
        return res;
    }
}
