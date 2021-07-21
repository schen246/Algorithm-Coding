package Amazon.Design;

import java.util.HashMap;
import java.util.Map;

public class DesignFileSystem {
    class Node {
        Map<String, Node> children;
        int value;
        public Node() {
            children = new HashMap<>();
        }
    }
    
    Node root;
    public DesignFileSystem() {
        root = new Node();
    }
    
    public boolean createPath(String path, int value) {
        String[] strs = path.split("/");
        Node cur = root;
        for (int i = 1; i < strs.length - 1; i++) {
            if (!cur.children.containsKey(strs[i])) {
                return false;
            }
            cur = cur.children.get(strs[i]);
        }
        if (cur.children.containsKey(strs[strs.length - 1])) {
            return false;
        }
        cur.children.put(strs[strs.length - 1], new Node());
        cur = cur.children.get(strs[strs.length - 1]);
        cur.value = value;
        return true;
    }
    
    public int get(String path) {
        String[] strs = path.split("/");
        Node cur = root;
        for (int i = 1; i < strs.length; i++) {
            if (!cur.children.containsKey(strs[i])) {
                return -1;
            }
            cur = cur.children.get(strs[i]);
        }
        return cur.value;
    }
}
