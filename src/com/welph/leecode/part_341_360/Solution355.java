package com.welph.leecode.part_341_360;

import java.util.*;

/**
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：
 * <p>
 * postTweet(userId, tweetId): 创建一条新的推文
 * getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
 * follow(followerId, followeeId): 关注一个用户
 * unfollow(followerId, followeeId): 取消关注一个用户
 * 示例:
 * <p>
 * Twitter twitter = new Twitter();
 * <p>
 * // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
 * twitter.postTweet(1, 5);
 * <p>
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * twitter.getNewsFeed(1);
 * <p>
 * // 用户1关注了用户2.
 * twitter.follow(1, 2);
 * <p>
 * // 用户2发送了一个新推文 (推文id = 6).
 * twitter.postTweet(2, 6);
 * <p>
 * // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
 * // 推文id6应当在推文id5之前，因为它是在5之后发送的.
 * twitter.getNewsFeed(1);
 * <p>
 * // 用户1取消关注了用户2.
 * twitter.unfollow(1, 2);
 * <p>
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * // 因为用户1已经不再关注用户2.
 * twitter.getNewsFeed(1);
 */
public class Solution355 {

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        /*// 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
        twitter.postTweet(1, 5);
        // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        System.out.println(twitter.getNewsFeed(1));
        // 用户1关注了用户2.
        twitter.follow(1, 2);
        // 用户2发送了一个新推文 (推文id = 6).
        twitter.postTweet(2, 6);
        // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
        // 推文id6应当在推文id5之前，因为它是在5之后发送的.
        System.out.println(twitter.getNewsFeed(1));
        // 用户1取消关注了用户2.
        twitter.unfollow(1, 2);
        // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        // 因为用户1已经不再关注用户2.
        System.out.println(twitter.getNewsFeed(1));*/
       /* twitter.postTweet(1, 5);
        twitter.postTweet(1, 3);
        System.out.println(twitter.getNewsFeed(1));*/
        //["Twitter","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","getNewsFeed","follow","getNewsFeed"]
        //[[],[2,5],[1,3],[1,101],[2,13],[2,10],[1,2],[2,94],[2,505],[1,333],[1,22],[2],[2,1],[2]]
        twitter.postTweet(2, 5);
        twitter.postTweet(1, 3);
        twitter.postTweet(1, 101);
        twitter.postTweet(2, 13);
        twitter.postTweet(2, 10);
        twitter.postTweet(1, 2);
        twitter.postTweet(2, 94);
        twitter.postTweet(2, 505);
        twitter.postTweet(1, 333);
        twitter.postTweet(1, 22);
        twitter.follow(2, 1);
        System.out.println(twitter.getNewsFeed(2));
    }


    static class Twitter {
        int counter = 0;
        Entry HEAD = new Entry(0, getCount());

        Map<Integer, Set<Integer>> follows;
        Map<Integer, Entry> entrys;

        public Twitter() {
            follows = new HashMap<>();
            entrys = new HashMap<>();
        }

        /**
         * 创建一条新的推文
         */
        public void postTweet(int userId, int tweetId) {
            Entry lastEntry = entrys.computeIfAbsent(userId, id -> HEAD);
            entrys.put(userId, new Entry(tweetId, getCount(), lastEntry));
        }

        /**
         * 检索最近的十条推文。
         * 每个推文都必须是由此用户关注的人或者是用户自己发出的。
         * 推文必须按照时间顺序由最近的开始排序。
         */
        public List<Integer> getNewsFeed(int userId) {
            Set<Integer> follows = follows(userId);
            List<Integer> res = new ArrayList<>();
            //找出follows中的最大值的一个
            //大顶堆?.
            Entry[] heap = new Entry[follows.size() + 1];
            int size = 0;
            Entry entry;
            for (Integer follow : follows) {
                //入队列
                entry = entrys.get(follow);
                if (entry == null) {
                    continue;
                }
                heap[++size] = entry;
            }
            //排序
            int len = size;
            for (int i = len; i > 1; i--) {
                swap(heap, 1, len);
                heapify(heap, 1, len);
                len--;
            }
            //开始获取
            while (size != 0) {
                entry = heap[1];
                res.add(entry.tweetId);
                if (res.size() == 10) {
                    break;
                }
                //将新值替代;
                if (entry.last == HEAD) {
                    heap[1] = heap[size];
                    size--;
                    heapify(heap, 1, size);
                    continue;
                }
                heap[1] = entry.last;
                heapify(heap, 1, size);
            }
            return res;
        }

        private int getCount() {
            return counter++;
        }

        private void heapify(Entry[] heap, int i, int len) {
            while (true) {
                int maxIndex = i;
                if (2 * i <= len && heap[2 * i].count > heap[i].count) {
                    maxIndex = 2 * i;
                }
                if (2 * i + 1 <= len && heap[2 * i + 1].count > heap[maxIndex].count) {
                    maxIndex = 2 * i + 1;
                }
                if (maxIndex == i) {
                    break;
                }
                swap(heap, i, maxIndex);
                i = maxIndex;
            }
        }

        private static void swap(Entry[] heap, int i, int j) {
            Entry tmp = heap[i];
            heap[i] = heap[j];
            heap[j] = tmp;
        }

        /**
         * 关注一个用户
         */
        public void follow(int followerId, int followeeId) {
            follows(followerId).add(followeeId);
        }

        /**
         * 取消关注一个用户
         */
        public void unfollow(int followerId, int followeeId) {
            follows(followerId).remove(followeeId);
        }

        private Set<Integer> follows(int followerId) {
            return follows.computeIfAbsent(followerId, f -> {
                Set<Integer> objects = new HashSet<>();
                objects.add(f);
                return objects;
            });
        }

        private static class Entry {
            private int tweetId;
            private int count;
            private Entry last;

            public Entry(int tweetId, int count) {
                this.tweetId = tweetId;
                this.count = count;
            }

            public Entry(int tweetId, int count, Entry last) {
                this.tweetId = tweetId;
                this.count = count;
                this.last = last;
            }
        }
    }
}
