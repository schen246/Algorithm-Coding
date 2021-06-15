
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class InsertDeleteGetRandomO1DuplicatesAllowed {
    Map<Integer, Set<Integer>> map;// val, set of index of val in list
    List<Integer> list;// val
    Random random;

    public InsertDeleteGetRandomO1DuplicatesAllowed() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {
        Set<Integer> cur = map.getOrDefault(val, new HashSet<>());
        cur.add(list.size());
        map.put(val, cur);
        list.add(val);
        return map.get(val).size() == 1;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val) || map.get(val).size() == 0) {
            return false;
        }
        int index = map.get(val).iterator().next();
        map.get(val).remove(index);
        int lastValue = list.get(list.size() - 1);
        list.set(index, lastValue);
        map.get(lastValue).add(index);
        map.get(lastValue).remove(list.size() - 1);
        list.remove(list.size() - 1);
        return true;
    }

    public int getRandom() {
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
