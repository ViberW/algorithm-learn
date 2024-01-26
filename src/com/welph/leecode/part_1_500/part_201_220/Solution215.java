package com.welph.leecode.part_1_500.part_201_220;

/**
 * 在未排序的数组中找到第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，
 * 而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * <p>
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * <p>
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class Solution215 {

    public static void main(String[] args) {
        int[] nums = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
        System.out.println(findKthLargest(nums, 4));
    }

    /**
     * 大顶堆保存数据信息. 第k的值
     */
    public static int findKthLargest(int[] nums, int k) {
        int[] heap = new int[k + 1];// 减少一次判断过程
        int heapSize = 0;

        for (int num : nums) {

            if (heapSize == k) {
                if (heap[1] < num) {
                    heap[1] = num;
                    heapify(heap, heapSize, 1);
                }
            } else {
                heapSize = insert(heap, heapSize, num);
            }
        }

        return heap[1];
    }

    // 从下往上堆化
    private static int insert(int[] heap, int size, int num) {
        heap[++size] = num;
        int i = size;
        while (i / 2 > 0 && heap[i] < heap[i / 2]) {
            swap(heap, i, i / 2);
            i = i / 2;
        }
        return size;
    }

    // 从上往下堆化
    private static void heapify(int[] heap, int size, int i) {
        while (true) {
            int minIndex = i;
            if (i * 2 <= size && heap[i] > heap[i * 2]) {
                minIndex = i * 2;
            }
            if (i * 2 + 1 <= size && heap[minIndex] > heap[i * 2 + 1]) {
                minIndex = i * 2 + 1;
            }
            if (minIndex == i) {
                break;
            }
            swap(heap, i, minIndex);
            i = minIndex;
        }
    }

    private static void swap(int[] heap, int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    /* 官方题解 */
    public int findKthLargest2(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

}
