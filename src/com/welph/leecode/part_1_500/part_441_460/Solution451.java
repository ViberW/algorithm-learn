package com.welph.leecode.part_1_500.part_441_460;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * <p>
 * 示例 1:
 * 输入:
 * "tree"
 * 输出:
 * "eert"
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * <p>
 * 示例 2:
 * 输入:
 * "cccaaa"
 * 输出:
 * "cccaaa"
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * <p>
 * 示例 3:
 * 输入:
 * "Aabb"
 * 输出:
 * "bbAa"
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符
 */
public class Solution451 {

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));
    }

    /**
     * 优先级队列, 计数, 桶
     * {@link com.welph.leecode.part_1_500.part_341_360.Solution347}
     */
    public static String frequencySort(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> count = new HashMap<>();
        for (char c : chars) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Entity> queue = new PriorityQueue<>(new Comparator<Entity>() {
            public int compare(Entity m, Entity n) {
                return n.i - m.i;
            }
        });
        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            queue.offer(new Entity(entry.getKey(), entry.getValue()));
        }
        char[] ret = new char[chars.length];
        for (int i = 0; i < chars.length;) {
            Entity poll = queue.poll();
            int cn = poll.i;
            while (cn-- > 0) {
                ret[i++] = poll.c;
            }
        }
        return new String(ret);
    }

    static class Entity {
        char c;
        int i;

        public Entity(char c, int i) {
            this.c = c;
            this.i = i;
        }
    }

    /* 官方题解 */
    public String frequencySort2(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            int frequency = map.getOrDefault(c, 0) + 1;
            map.put(c, frequency);
        }
        // 上面我用了优先队列, 感觉没什么用, 直接用数组就行
        List<Character> list = new ArrayList<Character>(map.keySet());
        Collections.sort(list, (a, b) -> map.get(b) - map.get(a));
        StringBuffer sb = new StringBuffer();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            char c = list.get(i);
            int frequency = map.get(c);
            for (int j = 0; j < frequency; j++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // 桶排序, 思路都差不多, 只是用空间换时间, 取消了排序的操作
    public String frequencySort3(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int maxFreq = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            int frequency = map.getOrDefault(c, 0) + 1;
            map.put(c, frequency);
            maxFreq = Math.max(maxFreq, frequency);
        }
        StringBuffer[] buckets = new StringBuffer[maxFreq + 1];
        for (int i = 0; i <= maxFreq; i++) {
            buckets[i] = new StringBuffer();
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char c = entry.getKey();
            int frequency = entry.getValue();
            buckets[frequency].append(c);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = maxFreq; i > 0; i--) {
            StringBuffer bucket = buckets[i];
            int size = bucket.length();
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < i; k++) {
                    sb.append(bucket.charAt(j));
                }
            }
        }
        return sb.toString();
    }

}
