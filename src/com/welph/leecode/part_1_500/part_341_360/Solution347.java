package com.welph.leecode.part_1_500.part_341_360;

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
     * {@link com.welph.leecode.part_1_500.part_201_220.Solution215}
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

    /* 官方题解 */
    //使用了内部的优先级队列实现堆
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }

    //快排方式
    public int[] topKFrequent3(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }
        // 获取每个数字出现次数
        List<int[]> values = new ArrayList<int[]>();
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            values.add(new int[]{num, count});
        }
        int[] ret = new int[k];
        qsort(values, 0, values.size() - 1, ret, 0, k);
        return ret;
    }

    public void qsort(List<int[]> values, int start, int end, int[] ret, int retIndex, int k) {
        int picked = (int) (Math.random() * (end - start + 1)) + start;
        Collections.swap(values, picked, start);
        
        int pivot = values.get(start)[1];
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            // 使用双指针把不小于基准值的元素放到左边，
            // 小于基准值的元素放到右边
            if (values.get(i)[1] >= pivot) {
                Collections.swap(values, index + 1, i);
                index++;
            }
        }
        Collections.swap(values, start, index);

        if (k <= index - start) {
            // 前 k 大的值在左侧的子数组里
            qsort(values, start, index - 1, ret, retIndex, k);
        } else {
            // 前 k 大的值等于左侧的子数组全部元素
            // 加上右侧子数组中前 k - (index - start + 1) 大的值
            for (int i = start; i <= index; i++) {
                ret[retIndex++] = values.get(i)[0];
            }
            if (k > index - start + 1) {
                qsort(values, index + 1, end, ret, retIndex, k - (index - start + 1));
            }
        }
    }
}
