import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DesignSearchAutocompleteSystem {
    Node root;
    Node node;
    StringBuilder sb;
    Map<String, Integer> map;

    public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
        sb = new StringBuilder();
        map = new HashMap<>();
        root = new Node();
        node = root;
        for (int i = 0; i < sentences.length; i++) {
            String sentence = sentences[i];
            int time = times[i];
            map.put(sentence, time);
            addNode(sentence, time);
        }
    }

    private void addNode(String sentence, int time) {
        Node cur = root;
        for (char c : sentence.toCharArray()) {
            cur.children.putIfAbsent(c, new Node());
            cur = cur.children.get(c);
            cur.pairs.add(new Pair(sentence, time));
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            String sentence = sb.toString();
            int time = map.getOrDefault(sentence, 0) + 1;
            map.put(sentence, time);
            updateNode(sentence, time);
            sb.setLength(0);
            node = root;
            return res;
        }
        sb.append(c);
        // prev node is null
        if (node == null) {
            return res;
        }
        // move to cur node
        node = node.children.get(c);
        // cur node is null
        if (node == null) {
            return res;
        }
        node.getTopThree(res);
        return res;
    }

    private void updateNode(String sentence, int time) {
        Node cur = root;
        for (char c : sentence.toCharArray()) {
            cur.children.putIfAbsent(c, new Node());
            cur = cur.children.get(c);
            for (Pair pair : cur.pairs) {
                if (pair.sentence.equals(sentence)) {
                    cur.pairs.remove(pair);
                    break;
                }
            }
            cur.pairs.add(new Pair(sentence, time));
        }
    }
}

class Node {
    Map<Character, Node> children;
    Set<Pair> pairs;

    public Node() {
        children = new HashMap<>();
        pairs = new TreeSet<>((a, b) -> {
            if (a.time == b.time) {
                return a.sentence.compareTo(b.sentence);// small letter first
            }
            return b.time - a.time;// large time first
        });
    }

    public void getTopThree(List<String> res) {
        int cnt = 0;
        for (Pair pair : pairs) {
            res.add(pair.sentence);
            cnt++;
            if (cnt == 3) {
                return;
            }
        }
    }
}

class Pair {
    String sentence;
    int time;

    public Pair(String sentence, int time) {
        this.sentence = sentence;
        this.time = time;
    }
}