package Amazon.Coding;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class FirstUniqueNumber {
    // linked list hashset + set
    Set<Integer> set;// space: O(n)
    Set<Integer> unique;
    public FirstUniqueNumber(int[] nums) {// O(k)
        set = new HashSet<>();
        unique = new LinkedHashSet<>();
        for (int num : nums) {
            if (set.add(num)) {
                unique.add(num);
            } else {
                unique.remove(num);
            }
        }
    }
    
    public int showFirstUnique() {// O(1)
        if (unique.size() > 0) {
            return unique.iterator().next();
        }
        return -1;
    }
    
    public void add(int value) {// O(1)
        if (set.add(value)) {
            unique.add(value);
        } else {
            unique.remove(value);
        }
    }
}
