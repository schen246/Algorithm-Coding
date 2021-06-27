public class FaultySensor {
    // iteration - time: O(n) space: O(1)
    public int badSensor(int[] sensor1, int[] sensor2) {
        int n = sensor1.length;
        int i = 0;
        // find first different index
        while (i < n && sensor1[i] == sensor2[i]) {
            i++;
        }
        if (i >= n - 1) return -1;
        // sensor1 has defect
        int index1 = i;
        while (index1 < n - 1 && sensor1[index1] == sensor2[index1 + 1]) {
            index1++;
        }
        // sensor2 has defect
        int index2 = i;
        while (index2 < n - 1 && sensor1[index2 + 1] == sensor2[index2]) {
            index2++;
        }
        if (index1 == n - 1 && index2 == n - 1) return -1;
        return index1 == n - 1 ? 1 : 2;
    }
}
