import java.util.Map;

public class TwoSumIIIDataStructureDesign {
    Map<Integer, Integer> map;

    public TwoSumIIIDataStructureDesign() {
        map = new HashMap<>();
    }

    public void add(int number) {// O(1)
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    public boolean find(int value) {// O(n)
        for (int num : map.keySet()) {
            if (value - num != num && map.containsKey(value - num)) {
                return true;
            }
            if (value - num == num && map.get(value - num) >= 2) {
                return true;
            }
        }
        return false;
    }
}
