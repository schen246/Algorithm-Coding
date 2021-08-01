package Amazon.SystemDesign;

import java.util.HashMap;
import java.util.Map;

public class AmazonLocker {
    public static void main(String[] args) {
        Locker locker = new Locker();
        Item sItem = new Item(1, ItemSize.small);
        Item mItem = new Item(5, ItemSize.medium);
        Item lItem = new Item(10, ItemSize.large);
        locker.set(sItem);
        locker.set(mItem);
        locker.set(lItem);
        System.out.println("locker_id for item_id = 1 is " + locker.get(sItem));
    }
}

class Locker {
    public Map<Integer, Integer> small;// locker id, item id
    public Map<Integer, Integer> medium;
    public Map<Integer, Integer> large;
    int s, m, l;
    int cntS, cntM, cntL;
    public Locker() {
        small = new HashMap<>();
        medium = new HashMap<>();
        large = new HashMap<>();
        s = 50;
        m = 100;
        l = 150;
        cntS = 0;
        cntM = 0;
        cntL = 0;
    }

    public void set(Item item) {
        Integer itemId = item.id;
        ItemSize size = item.size;
        if (size.equals(ItemSize.small) && cntS != s) {
            small.put(cntS, itemId);
            cntS++;
        } else if ((size.equals(ItemSize.medium) || (size.equals(ItemSize.small)) && cntM != m)) {
            medium.put(cntM, itemId);
            cntM++;
        } else if (cntL != l) {
            large.put(cntL, itemId);
            cntL--;
        } else {
            System.out.println("all lockers are full");
        }
    }

    public int get(Item item) {
        Integer itemId = item.id;
        ItemSize size = item.size;
        if (size.equals(ItemSize.small)) {
            if (small.containsValue(itemId)) {
                for (Map.Entry<Integer, Integer> entry : small.entrySet()) {
                    if (itemId.equals(entry.getValue())) {
                        cntS--;
                        return entry.getKey();
                    }
                }
            }
        }
        if (size.equals(ItemSize.medium) || size.equals(ItemSize.small)) {
            if (medium.containsValue(itemId)) {
                for (Map.Entry<Integer, Integer> entry : medium.entrySet()) {
                    if (itemId.equals(entry.getValue())) {
                        cntM--;
                        return entry.getKey();
                    }
                }
            }
        }
        if (size.equals(ItemSize.large) || size.equals(ItemSize.medium) || size.equals(ItemSize.small)) {
            if (large.containsValue(itemId)) {
                for (Map.Entry<Integer, Integer> entry : large.entrySet()) {
                    if (itemId.equals(entry.getValue())) {
                        cntL--;
                        return entry.getKey();
                    }
                }
            }
        }
        return -1;
    }
}

class Item {
    Integer id;
    ItemSize size;
    public Item(int id, ItemSize size) {
        this.id = id;
        this.size = size;
    }
}

enum ItemSize {
    small,
    medium,
    large
}