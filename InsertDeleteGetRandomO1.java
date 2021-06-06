import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class InsertDeleteGetRandomO1 {
    Map<Integer, Integer> map;// val, index in list
    List<Integer> list;// val
    Random random;
    public InsertDeleteGetRandomO1() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {// O(1)
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {// O(1)
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val);
        map.remove(val);
        int lastValue = list.get(list.size() - 1);
        map.put(lastValue, index);
        list.set(index, lastValue);
        list.remove(list.size() - 1);
        return true;
    }

    public int getRandom() {// O(1)
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
