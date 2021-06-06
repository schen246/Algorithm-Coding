import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class DesignTwitter {
    Map<Integer, Set<Integer>> followMap;// userId, list of followees
    Map<Integer, List<Post>> postMap;// userId, list of posts
    int time;
    public DesignTwitter() {
        followMap = new HashMap<>();
        postMap = new HashMap<>();
        time = 0;
    }

    public void postTweet(int userId, int tweetId) {
        Post post = new Post(tweetId, time);
        time++;
        List<Post> curPost = postMap.getOrDefault(userId, new ArrayList<>());
        curPost.add(post);
        postMap.put(userId, curPost);
    }

    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> curSet = followMap.getOrDefault(userId, new HashSet<>());
        curSet.add(userId);
        PriorityQueue<Post> pq = new PriorityQueue<>((a, b) -> a.timestamp - b.timestamp);// small time first
        for (int id : curSet) {
            List<Post> cur = postMap.getOrDefault(id, new ArrayList<>());
            for (Post post : cur) {
                pq.offer(post);
                if (pq.size() > 10) {
                    pq.poll();
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        while (pq.size() > 0) {
            res.add(0, pq.poll().tweetId);
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        Set<Integer> curSet = followMap.getOrDefault(followerId, new HashSet<>());
        curSet.add(followeeId);
        followMap.put(followerId, curSet);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId)) {
            Set<Integer> curSet = followMap.get(followerId);
            if (curSet.contains(followeeId)) {
                curSet.remove(followeeId);
            }
        }
    }
}

class Post {
    int tweetId;
    int timestamp;
    public Post(int id, int time) {
        tweetId = id;
        timestamp = time;
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */