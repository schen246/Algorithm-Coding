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

class Logger {

    /** Initialize your data structure here. */
    Map<String, Integer> map;
    public Logger() {
        map = new HashMap<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (timestamp < map.getOrDefault(message, 0)) {
            return false;
        }
        map.put(message, timestamp + 10);
        return true;
    }
}