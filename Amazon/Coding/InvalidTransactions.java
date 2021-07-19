package Amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvalidTransactions {
    // map + check valid - time: O(n^2) space: O(n)
    public List<String> invalidTransactions(String[] transactions) {
        Map<String, List<Tran>> map = new HashMap<>();
        for (String transaction : transactions) {// O(n)
            Tran i = new Tran(transaction);
            map.putIfAbsent(i.name, new ArrayList<>());
            map.get(i.name).add(i);
        }
        List<String> res = new ArrayList<>();
        for (String transaction : transactions) {// O(n)
            Tran cur = new Tran(transaction);
            List<Tran> list = map.get(cur.name);
            if (inValid(cur, list)) {// O(n) worst case
                res.add(transaction);
            }
        }
        return res;
    }
    
    private boolean inValid(Tran cur, List<Tran> list) {
        if (cur.amount > 1000) {
            return true;
        }
        for (Tran next : list) {
            if (next == cur) {
                continue;
            }
            if (Math.abs(next.time - cur.time) <= 60 && !next.city.equals(cur.city)) {
                return true;
            }
        }
        return false;
    }
}

class Tran {
    String name;
    int time;
    int amount;
    String city;
    public Tran(String transaction) {
        String[] arr = transaction.split(",");
        name = arr[0];
        time = Integer.valueOf(arr[1]);
        amount = Integer.valueOf(arr[2]);
        city = arr[3];
    }
}