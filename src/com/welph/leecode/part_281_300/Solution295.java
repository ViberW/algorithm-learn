package com.welph.leecode.part_281_300;

import java.util.Arrays;

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

    //["MedianFinder","addNum","findMedian","addNum","findMedian","addNum",
    // "findMedian","addNum","findMedian","addNum","findMedian"]
    //[[],[-1],[],[-2],[],[-3],[],[-4],[],[-5],[]]
    public static void main(String[] args) {
       /* MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        finder.addNum(2);
        System.out.println(finder.findMedian());
        finder.addNum(3);
        System.out.println(finder.findMedian());*/

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
            //从下往上堆化
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
            //从上往下堆化
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
}
