public class MeetingScheduler {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);
        int i = 0, j = 0;
        while (i < slots1.length && j < slots2.length) {
            int left = Math.max(slots1[i][0], slots2[j][0]);
            int right = Math.min(slots1[i][1], slots2[j][1]);
            if (right - left >= duration) {
                res.add(left);
                res.add(left + duration);
                return res;
            }
            if (slots1[i][1] < slots2[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return res;
    }
    // time: O(mlogm) + O(nlogn) + O(m + n) space: O(m + n) based on sorting algorithm
}
