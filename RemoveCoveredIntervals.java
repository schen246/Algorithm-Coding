import java.util.Arrays;

public class RemoveCoveredIntervals {
    public int removeCoveredIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];// end first
            }
            return a[0] - b[0];// small first
        });
        int start = intervals[0][0], end = intervals[0][1];
        int cnt = intervals.length;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][1] <= end) {
                cnt--;
            } else {
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        return cnt;
    }
    // time: O(nlogn) space: O(n) depends on sort algorithm
}
