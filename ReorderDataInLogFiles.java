import java.util.Arrays;

public class ReorderDataInLogFiles {
    // sort - time: O(m * nlogn) space: O(m * logn) based on quick sort algorithm
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (s1, s2) -> {
            String[] arr1 = s1.split(" ", 2);
            String[] arr2 = s2.split(" ", 2);
            boolean b1 = Character.isDigit(arr1[1].charAt(0));
            boolean b2 = Character.isDigit(arr2[1].charAt(0));
            if (b1 && b2) {
                return 0;
            }
            if (b1) {
                return 1;
            }
            if (b2) {
                return -1;
            }
            if (arr1[1].equals(arr2[1])) {
                return arr1[0].compareTo(arr2[0]);
            }
            return arr1[1].compareTo(arr2[1]);// O(m)
        });
        return logs;
    }
}
