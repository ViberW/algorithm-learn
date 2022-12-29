package com.welph.leecode.part_500_1000.part_621_640;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
 * 在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 * <p>
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，
 * 因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * <p>
 * 你需要计算完成所有任务所需要的 最短时间 。
 * <p>
 * 示例 1：
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
 * 在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，
 * 所以中间出现了（待命）状态。
 * <p>
 * 示例 2：
 * 输入：tasks = ["A","A","A","B","B","B"], n = 0
 * 输出：6
 * 解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * 诸如此类
 * <p>
 * 示例 3：
 * 输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * 输出：16
 * 解释：一种可能的解决方案是：
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
 * <p>
 * 提示：
 * 1 <= task.length <= 10^4
 * tasks[i] 是大写英文字母
 * n 的取值范围为 [0, 100]
 */
public class Solution621 {

    public static void main(String[] args) {
//        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));
    }

    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        PriorityQueue<Tuple2> queue = new PriorityQueue<>((o1, o2) -> o2.total - o1.total);
        map.forEach((task, count) -> {
            queue.add(new Tuple2(task, 0, count));
        });
        PriorityQueue<Tuple2> queue2 = new PriorityQueue<>(Comparator.comparingInt(value -> value.n));
        int current = 0;
        while (!queue.isEmpty() || !queue2.isEmpty()) {
            if (!queue.isEmpty() && queue.peek().n <= current) {
                Tuple2 poll = queue.poll();
                if (--poll.total > 0) {
                    poll.n = current + n + 1;
                    queue2.add(poll);
                }
            }
            current++;
            while (!queue2.isEmpty() && queue2.peek().n <= current) {
                queue.add(queue2.poll());
            }
        }
        return current;
    }

    static class Tuple2 {
        char c;
        int n;
        int total;

        public Tuple2(char c, int n, int total) {
            this.c = c;
            this.n = n;
            this.total = total;
        }
    }
}
