import java.util.Arrays;

public class MaximumUnitsOnATruck {
    // sort + gready - time: O(nlogn) space: O(1)
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);// large unit first
        int res = 0;
        for (int[] box : boxTypes) {
            if (box[0] < truckSize) {
                truckSize -= box[0];
                res += box[0] * box[1];
            } else {
                res += truckSize * box[1];
                return res;
            }
        }
        return res;
    }
}
