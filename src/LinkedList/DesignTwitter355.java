package LinkedList;

import java.util.*;

public class DesignTwitter355 {

    static class Twitter {

        private static int timeStamp=0;

        // Easy to search if an user exist;
        private Map<Integer, User> userMap;

        // Tweet next to the next tweet, so it will connect each other
        // easy for search the whole list;
        private class Tweet{
            public int id;
            public int time;
            public Tweet next;

            public Tweet(int id){
                this.id = id;
                time = timeStamp++;
                next=null;
            }
        }

        // OO design so User can follow, unfollow and post itself
        public class User{
            public int id;
            public Set<Integer> followed;
            public Tweet tweet_head;

            public User(int id){
                this.id=id;
                followed = new HashSet<>();
                follow(id); // first follow itself
                tweet_head = null;
            }

            public void follow(int id){
                followed.add(id);
            }

            public void unfollow(int id){
                followed.remove(id);
            }

            // everytime user post a new tweet, add it to the head of tweet list.
            public void post(int id){
                Tweet t = new Tweet(id);
                t.next=tweet_head;
                tweet_head=t;
            }
        }


        /** Initialize your data structure here. */
        public Twitter() {
            userMap = new HashMap<Integer, User>();
        }

        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {
            if(!userMap.containsKey(userId)) {
                userMap.put(userId, new User(userId));
            }
            userMap.get(userId).post(tweetId);
        }

        /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new LinkedList<>();

            if(!userMap.containsKey(userId))   return res;

            Set<Integer> users = userMap.get(userId).followed;
            PriorityQueue<Tweet> q = new PriorityQueue<Tweet>(users.size(), (a,b)->(b.time-a.time));
            for(int user: users){
                Tweet t = userMap.get(user).tweet_head;
                // very imporant! If we add null to the head we are screwed.
                if(t!=null){
                    q.offer(t);
                }
            }
            int n=0;
            while(!q.isEmpty() && n<10){
                Tweet t = q.poll();
                res.add(t.id);
                n++;
                if(t.next!=null)
                    q.add(t.next);
            }

            return res;

        }

        /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
        public void follow(int followerId, int followeeId) {
            if(!userMap.containsKey(followerId)){
                User u = new User(followerId);
                userMap.put(followerId, u);
            }
            if(!userMap.containsKey(followeeId)){
                User u = new User(followeeId);
                userMap.put(followeeId, u);
            }
            userMap.get(followerId).follow(followeeId);
        }

        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void unfollow(int followerId, int followeeId) {

            if (userMap.containsKey(followeeId) && userMap.containsKey(followerId)) {
                userMap.get(followerId).unfollow(followeeId);
            }
        }
    }
}
