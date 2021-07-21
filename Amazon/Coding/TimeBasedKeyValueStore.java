package Amazon.Coding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasedKeyValueStore {
    class Item {
        int timestamp;
        String value;
        public Item(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }
    Map<String, List<Item>> map;
    public TimeBasedKeyValueStore() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {// O(1)
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(new Item(timestamp, value));
    }
    
    public String get(String key, int timestamp) {// O(logn)
        if (map.get(key) == null) {
            return "";
        }
        List<Item> list = map.get(key);
        // binary search to find the largest val <= timestamp
        int left = 0, right = list.size() - 1;
        String res = "";
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Item cur = list.get(mid);
            if (cur.timestamp <= timestamp) {
                res = cur.value;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }
}