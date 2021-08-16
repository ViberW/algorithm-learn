package com.welph.leecode.part_341_360;

import com.welph.leecode.algorithm.graph.shortestpath.structure.Vertex;

import java.util.*;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <p>
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * <p>
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * <p>
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 */
public class Solution347 {

    public static void main(String[] args) {
        int[] ints;
        ints = topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        System.out.println(Arrays.toString(ints));
        ints = topKFrequent(new int[]{1}, 1);
        System.out.println(Arrays.toString(ints));
        ints = topKFrequent(new int[]{5, 3, 1, 1, 1, 3, 73, 1}, 2);
        System.out.println(Arrays.toString(ints));
        ints = topKFrequent(new int[]{5, 2, 5, 3, 5, 3, 1, 1, 3}, 2);
        System.out.println(Arrays.toString(ints));
        ints = topKFrequent(new int[]{5, -3, 9, 1, 7, 7, 9, 10, 2, 2, 10, 10, 3, -1, 3, 7, -9, -1, 3, 3}, 3);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * {@link com.welph.leecode.part_201_220.Solution215}
     * //可更新的优先队列(堆) {@link com.welph.leecode.algorithm.graph.shortestpath.DijkstraDemo}
     */
    public static int[] topKFrequent(int[] nums, int k) {
        Node[] nodes = new Node[k + 1]; //小顶堆
        nodes[1] = new Node(0, 0);
        int size = 0;
        Map<Integer, Node> exist = new HashMap<>();
        for (int num : nums) {
            Node node = exist.computeIfAbsent(num, n -> new Node(n, 0));
            node.seq++;
            if (node.index == null) {
                if (size == k) {
                    if (node.seq > nodes[1].seq) {
                        nodes[1].index = null;
                        nodes[1] = node;
                        node.index = 1;
                        //堆化
                        heapify(nodes, size, 1);
                    }
                } else {
                    node.index = size + 1;
                    insert(nodes, node, size);
                    size++;
                }
            } else {
                heapify(nodes, size, node.index);
            }
        }
        //最后进行一次堆化排序;
        int len = size;
        for (int i = len; i > 0; i--) {
            swap(nodes, 1, i);
            len--;
            heapify(nodes, len, 1);
        }
        int[] result = new int[k];
        for (int i = 0; i < size; i++) {
            result[i] = nodes[i + 1].num;
        }
        return result;
    }

    private static void heapify(Node[] nodes, int size, int i) {
        while (true) {
            int minIndex = i;
            if (2 * i <= size && nodes[i].seq > nodes[i * 2].seq) {
                minIndex = 2 * i;
            }
            if (2 * i + 1 <= size && nodes[minIndex].seq > nodes[i * 2 + 1].seq) {
                minIndex = 2 * i + 1;
            }
            if (minIndex == i) {
                break;
            }
            swap(nodes, i, minIndex);
            i = minIndex;
        }
    }

    private static void insert(Node[] nodes, Node node, int size) {
        nodes[++size] = node;
        int i = size;
        while (i / 2 > 0 && nodes[i].seq < nodes[i / 2].seq) {
            swap(nodes, i, i / 2);
            i = i / 2;
        }
    }

    private static void swap(Node[] nodes, int i, int j) {
        Node tmp = nodes[i];
        nodes[i] = nodes[j];
        nodes[i].index = i;
        nodes[j] = tmp;
        nodes[j].index = j;
    }

    static class Node {
        int num;
        int seq;
        Integer index;

        public Node(int num, int seq) {
            this.num = num;
            this.seq = seq;
        }
    }
}
