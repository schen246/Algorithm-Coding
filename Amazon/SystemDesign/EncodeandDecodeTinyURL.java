package Amazon.SystemDesign;

import java.util.HashMap;
import java.util.Map;

public class EncodeandDecodeTinyURL {
    Map<Integer, String> map = new HashMap<>();
    
    public String encode(String longUrl) {
        int hash = longUrl.hashCode();
        map.put(hash, longUrl);
        return "http://tinyurl.com/" + hash;
    }

    public String decode(String shortUrl) {
        return map.get(Integer.valueOf(shortUrl.replace("http://tinyurl.com/", "")));
    }
}
