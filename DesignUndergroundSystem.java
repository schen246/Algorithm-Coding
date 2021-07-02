import java.util.HashMap;
import java.util.Map;

public class DesignUndergroundSystem {
    class Item {
        String stationName;
        int t;
        public Item(String stationName, int t) {
            this.stationName = stationName;
            this.t = t;
        }
    }
    
    Map<Integer, Item> inMap;// <id, Item(start, t)>
    Map<String, Map<String, double[]>> map;// <start, <end, average>>
    public DesignUndergroundSystem() {
        inMap = new HashMap<>();
        map = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {// O(1)
        inMap.put(id, new Item(stationName, t));
        map.putIfAbsent(stationName, new HashMap<>());
    }

    public void checkOut(int id, String stationName, int t) {// O(1)
        Item cur = inMap.get(id);
        double[] val = map.get(cur.stationName).getOrDefault(stationName, new double[]{0.0, 0.0});
        val[0] = (val[0] * val[1] + (t - cur.t)) / (val[1] + 1);
        map.get(cur.stationName).put(stationName, new double[]{val[0], val[1] + 1});
    }

    public double getAverageTime(String startStation, String endStation) {// O(1)
        return map.get(startStation).get(endStation)[0];
    }
}
