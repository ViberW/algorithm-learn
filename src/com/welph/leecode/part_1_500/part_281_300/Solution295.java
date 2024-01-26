package com.welph.leecode.part_1_500.part_281_300;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * <p>
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * <p>
 * 示例：
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * <p>
 * 进阶:
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 */
public class Solution295 {

    // ["MedianFinder","addNum","findMedian","addNum","findMedian","addNum",
    // "findMedian","addNum","findMedian","addNum","findMedian"]
    // [[],[-1],[],[-2],[],[-3],[],[-4],[],[-5],[]]
    public static void main(String[] args) {
        /*
         * MedianFinder finder = new MedianFinder();
         * finder.addNum(1);
         * finder.addNum(2);
         * System.out.println(finder.findMedian());
         * finder.addNum(3);
         * System.out.println(finder.findMedian());
         */

        MedianFinder finder = new MedianFinder();
        finder.addNum(-1);
        System.out.println(finder.findMedian());
        finder.addNum(-2);
        System.out.println(finder.findMedian());
        finder.addNum(-3);
        System.out.println(finder.findMedian());
        finder.addNum(-4);
        System.out.println(finder.findMedian());
        finder.addNum(-5);
        System.out.println(finder.findMedian());

        System.out.println(Arrays.toString(finder.small.arr));
        System.out.println(Arrays.toString(finder.big.arr));
    }

    /**
     * 使用两个堆, 小顶堆+大顶堆(限定大顶堆.size>小顶堆.size)
     * 所以中间值 = size小 ==size大 ? (小顶堆.top+大顶堆.top)/2 : 大顶堆.top
     */
    static class MedianFinder {
        Heap small;
        Heap big;

        public MedianFinder() {
            small = new Heap(4, false);
            big = new Heap(4, true);
        }

        public void addNum(int num) {
            if (big.size() == 0) {
                big.add(num);
            } else {
                if (small.size() < big.size()) {
                    if (big.peek() > num) {
                        small.add(big.replace(num));
                    } else {
                        small.add(num);
                    }
                } else {
                    if (small.peek() < num) {
                        big.add(small.replace(num));
                    } else {
                        big.add(num);
                    }
                }
            }
        }

        public double findMedian() {
            if (small.size() < big.size()) {
                return big.peek();
            } else {
                Integer peek = small.peek();
                Integer peek1 = big.peek();
                return ((peek == null ? 0 : peek) + (peek1 == null ? 0 : peek1)) / 2.0d;
            }
        }
    }

    static class Heap {
        boolean big;
        int size = 0;
        int capacity;
        int[] arr;

        public Heap(int capacity, boolean big) {
            this.capacity = capacity;
            this.big = big;
            arr = new int[capacity + 1];
        }

        public void add(int num) {
            if (size == capacity) {
                capacity *= 2;
                int[] newArr = new int[capacity + 1];
                System.arraycopy(arr, 1, newArr, 1, size);
                arr = newArr;
            }
            // 从下往上堆化
            arr[++size] = num;
            int i = size;
            while (i / 2 > 0 && compare(i / 2, i)) {
                swap(arr, i, i / 2);
                i = i / 2;
            }
        }

        public int size() {
            return size;
        }

        public Integer replace(int num) {
            Integer peek = peek();
            arr[1] = num;
            int i = 1;
            // 从上往下堆化
            while (true) {
                int minIndex = i;
                if (i * 2 <= size && compare(i, i * 2)) {
                    minIndex = i * 2;
                }
                if (i * 2 + 1 <= size && compare(minIndex, i * 2 + 1)) {
                    minIndex = i * 2 + 1;
                }
                if (minIndex == i) {
                    break;
                }
                swap(arr, i, minIndex);
                i = minIndex;
            }
            return peek;
        }

        public Integer peek() {
            return arr[1];
        }

        private boolean compare(int i, int j) {
            return big ? arr[i] < arr[j] : arr[i] > arr[j];
        }

        private void swap(int[] heap, int i, int j) {
            int tmp = heap[i];
            heap[i] = heap[j];
            heap[j] = tmp;
        }
    }

    /* 官方题解 */
    class MedianFinder2 {
        PriorityQueue<Integer> queMin;
        PriorityQueue<Integer> queMax;

        public MedianFinder2() {
            queMin = new PriorityQueue<Integer>((a, b) -> (b - a));
            queMax = new PriorityQueue<Integer>((a, b) -> (a - b));
        }

        public void addNum(int num) {
            if (queMin.isEmpty() || num <= queMin.peek()) {
                queMin.offer(num);
                if (queMax.size() + 1 < queMin.size()) {
                    queMax.offer(queMin.poll());
                }
            } else {
                queMax.offer(num);
                if (queMax.size() > queMin.size()) {
                    queMin.offer(queMax.poll());
                }
            }
        }

        public double findMedian() {
            if (queMin.size() > queMax.size()) {
                return queMin.peek();
            }
            return (queMin.peek() + queMax.peek()) / 2.0;
        }
    }

    // 有序数组 + 双指针
    class MedianFinder3 {
        TreeMap<Integer, Integer> nums;
        int n;
        int[] left;
        int[] right;

        public MedianFinder3() {
            nums = new TreeMap<Integer, Integer>();
            n = 0;
            left = new int[2]; // 0 代表数值 1-代表个数
            right = new int[2];
            // | left | right |
        }

        public void addNum(int num) {
            nums.put(num, nums.getOrDefault(num, 0) + 1);
            if (n == 0) {
                left[0] = right[0] = num;
                left[1] = right[1] = 1;
            } else if ((n & 1) != 0) { // 原本奇数个元素
                if (num < left[0]) { // 说明放到左边
                    //因为奇数个, 说明此时left[0]=right[0], 仅仅需要根据左右移动即可
                    decrease(left); // 既然放到左边 就需要将left减少, 整体向左移动
                } else {
                    increase(right); // 说明放到右边, 就需要将right增加, 整体向右移动
                }
            } else { // 原本偶数个元素
                if (num > left[0] && num < right[0]) { //若num正好在左右中间, 则向左移动
                    increase(left);
                    decrease(right);
                } else if (num >= right[0]) {//则左边移动, 因为偶数个 要想办法让left[0]=right[0]
                    increase(left);
                } else {
                    decrease(right); //向右移动, 并覆盖left 保证left[0]=right[0]
                    System.arraycopy(right, 0, left, 0, 2);
                }
            }
            n++;
        }

        public double findMedian() {
            return (left[0] + right[0]) / 2.0;
        }

        private void increase(int[] iterator) {
            iterator[1]++;
            if (iterator[1] > nums.get(iterator[0])) {
                iterator[0] = nums.ceilingKey(iterator[0] + 1); // 找到比当前值最近大的值
                iterator[1] = 1;
            }
        }

        private void decrease(int[] iterator) {
            iterator[1]--;
            if (iterator[1] == 0) {
                iterator[0] = nums.floorKey(iterator[0] - 1);// 找到比当前值最近小的值
                iterator[1] = nums.get(iterator[0]);
            }
        }
    }

}
