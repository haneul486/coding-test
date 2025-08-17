package leetcode;

import java.util.*;

/**
 * LeetCode 355. Design Twitter
 *
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user,
 * and is able to see the 10 most recent tweets in the user's news feed.
 *
 * Implement the Twitter class:
 *   - Twitter() Initializes your twitter object.
 *   - void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId.
 *     Each call to this function will be made with a unique tweetId.
 *   - List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed.
 *     Each item in the news feed must be posted by users who the user followed or by the user themself.
 *     Tweets must be ordered from most recent to least recent.
 *   - void follow(int followerId, int followeeId) The user with ID followerId follows the user with ID followeeId.
 *   - void unfollow(int followerId, int followeeId) The user with ID followerId unfollows the user with ID followeeId.
 *
 * Example:
 *   Input
 *     ["Twitter","postTweet","getNewsFeed","follow","postTweet","getNewsFeed","unfollow","getNewsFeed"]
 *     [[],[1,5],[1],[1,2],[2,6],[1],[1,2],[1]]
 *   Output
 *     [null,null,[5],null,null,[6,5],null,[5]]
 *
 * Constraints:
 *   - 1 <= userId, followerId, followeeId, tweetId <= 500
 *   - At most 3 * 10^4 calls will be made to postTweet, getNewsFeed, follow, and unfollow.
 */
public class LeetCode355_DesignTwitter {

    public static void main(String[] args) {
        // Example scenario from the description
        {
            Solution355.Twitter twitter = new Solution355.Twitter();
            twitter.postTweet(1, 5);
            printAndCheck("Ex1-Feed#1", Arrays.asList(5), twitter.getNewsFeed(1));

            twitter.follow(1, 2);
            twitter.postTweet(2, 6);
            printAndCheck("Ex1-Feed#2", Arrays.asList(6, 5), twitter.getNewsFeed(1));

            twitter.unfollow(1, 2);
            printAndCheck("Ex1-Feed#3", Arrays.asList(5), twitter.getNewsFeed(1));
        }

        // Multiple tweets by same user; feed limited to 10 most recent
        {
            Solution355.Twitter twitter = new Solution355.Twitter();
            for (int t = 1; t <= 15; t++) twitter.postTweet(42, 100 + t); // 15 tweets
            List<Integer> expected = new ArrayList<>();
            for (int t = 15; t >= 6; t--) expected.add(100 + t); // latest 10: 115..106
            printAndCheck("Limit10", expected, twitter.getNewsFeed(42));
        }

        // Follow graph with 3 users
        {
            Solution355.Twitter twitter = new Solution355.Twitter();
            twitter.postTweet(1, 101);
            twitter.postTweet(2, 201);
            twitter.postTweet(3, 301);

            twitter.follow(1, 2);
            twitter.follow(1, 3);
            // After following, feed should show recent from {1,2,3}
            List<Integer> expectedAnyOrder = Arrays.asList(301, 201, 101);
            printAndCheck("Follow3", expectedAnyOrder, twitter.getNewsFeed(1));

            twitter.unfollow(1, 2);
            // Now {1,3}
            expectedAnyOrder = Arrays.asList(301, 101);
            printAndCheck("Unfollow2", expectedAnyOrder, twitter.getNewsFeed(1));
        }

        // Self-post then follow/unfollow self should not break behavior
        {
            Solution355.Twitter twitter = new Solution355.Twitter();
            twitter.postTweet(7, 700);
            printAndCheck("SelfOnly", Arrays.asList(700), twitter.getNewsFeed(7));
        }

        System.out.println("\nAll tests executed.");
    }

    private static void printAndCheck(String name, List<Integer> expected, List<Integer> actual) {
        System.out.println("[" + name + "] expected=" + expected + " actual=" + actual);
        System.out.println(expected.equals(actual) ? "✅ PASSED\n" : "❌ FAILED\n");
    }
}

class Solution355 {
    public static class Twitter {

        //tweet이 저장될 공간
        List<Integer[]> tweets = new ArrayList();
        //follow가 저장될 공간
        Map<Integer, Set<Integer>> followers = new HashMap<>();

        public Twitter() {
        }

        public void postTweet(int userId, int tweetId) {
            // TODO: implement
            tweets.add(new Integer[]{userId, tweetId});
        }

        public List<Integer> getNewsFeed(int userId) {
            List<Integer> result = new ArrayList();
            Set<Integer> set = followers.get(userId);
            for (int i = tweets.size()-1; i > -1; i--) {
                int user = tweets.get(i)[0];

                if(set != null && set.contains(user) || user == userId){
                    result.add(tweets.get(i)[1]);
                    if(result.size() == 10){
                        break;
                    }
                }
            }
            return result;
        }

        public void follow(int followerId, int followeeId) {
            Set<Integer> set = followers.get(followerId);
            if(set == null){
                set = new HashSet();
            }
            set.add(followeeId);
            followers.put(followerId, set);
        }

        public void unfollow(int followerId, int followeeId) {
            Set<Integer> set = followers.get(followerId);
            if(set != null){
                set.remove(followeeId);
                followers.put(followerId, set);
            }
        }
    }
}