package Amazon.Design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesignInMemoryFileSystem {
    class Node {
        Map<String, Node> children;
        boolean isFile;
        String content;
        public Node() {
            children = new HashMap<>();
        }
    }
    
    Node root;
    public DesignInMemoryFileSystem() {
        root = new Node();
    }
    
    public List<String> ls(String path) {
        List<String> res = new ArrayList<>();
        String[] strs = path.split("/");
        Node cur = root;
        for (int i = 1; i < strs.length; i++) {
            if (!cur.children.containsKey(strs[i])) {
                return res;
            }
            cur = cur.children.get(strs[i]);
        }
        if (cur.isFile) {
            res.add(strs[strs.length - 1]);
            return res;
        }
        for (String s : cur.children.keySet()) {
            res.add(s);
        }
        Collections.sort(res);
        return res;
    }
    
    public void mkdir(String path) {
        String[] strs = path.split("/");
        Node cur = root;
        for (int i = 1; i < strs.length; i++) {
            if (!cur.children.containsKey(strs[i])) {
                cur.children.put(strs[i], new Node());
            }
            cur = cur.children.get(strs[i]);
        }
    }
    
    public void addContentToFile(String filePath, String content) {
        String[] strs = filePath.split("/");
        Node cur = root;
        for (int i = 1; i < strs.length; i++) {
            if (!cur.children.containsKey(strs[i])) {
                cur.children.put(strs[i], new Node());
            }
            cur = cur.children.get(strs[i]);
        }
        cur.isFile = true;
        if (cur.content != null) {
            cur.content += content;
        } else {
            cur.content = content;
        }
    }
    
    public String readContentFromFile(String filePath) {
        String[] strs = filePath.split("/");
        Node cur = root;
        for (int i = 1; i < strs.length; i++) {
            if (!cur.children.containsKey(strs[i])) {
                return "";
            }
            cur = cur.children.get(strs[i]);
        }
        return cur.content;
    }
}
