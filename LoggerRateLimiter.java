import java.util.HashMap;

public class LoggerRateLimiter {
    Map<String, Integer> map;
    public LoggerRateLimiter() {
        map = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {// O(1)
        if (map.get(message) != null && timestamp - map.get(message) < 10) {
            return false;
        }
        map.put(message, timestamp);
        return true;
    }
}
