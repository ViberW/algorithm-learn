package com.welph.leecode.algorithm.marscode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给你一个整数数组 nums 和一个整数 k，请你返回其中出现频率前 k 高的元素。请按升序排列。
 * <p>
 * 1 <= nums.length <= 10^5
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * 你所设计算法的时间复杂度必须优于 O(n log n)，其中 n 是数组大小。
 */
public class HotK {
    public static String solution(int[] nums, int k) {
        // Please write your code here
        Map<Integer, Integer> counts = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(counts::get));
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> e : counts.entrySet()) {
            if (queue.size() < k) {
                queue.offer(e.getKey());
            } else {
                if (counts.get(queue.peek()) < e.getValue()) {
                    queue.poll();
                    queue.offer(e.getKey());
                }
            }
        }
        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = queue.poll();
        }
        Arrays.sort(arr);
        return Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(","));
    }

    public static void main(String[] args) {
        //  You can add more test cases here
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int[] nums2 = {1};

        System.out.println(solution(nums1, 2).equals("1,2"));
        System.out.println(solution(nums2, 1).equals("1"));
    }
}
