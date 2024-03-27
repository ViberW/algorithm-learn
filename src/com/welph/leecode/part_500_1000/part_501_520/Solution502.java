package com.welph.leecode.part_500_1000.part_501_520;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，
 * 力扣 希望在 IPO 之前开展一些项目以增加其资本。 由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。
 * 帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
 * 给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
 * 最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
 * 总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
 * 答案保证在 32 位有符号整数范围内。
 * <p>
 * 示例 1：
 * 输入：k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
 * 输出：4
 * 解释：
 * 由于你的初始资本为 0，你仅可以从 0 号项目开始。
 * 在完成后，你将获得 1 的利润，你的总资本将变为 1。
 * 此时你可以选择开始 1 号或 2 号项目。
 * 由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本ans[cow。
 * 因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。
 * <p>
 * 示例 2：
 * 输入：k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
 * 输出：6
 * <p>
 * <p>
 * 提示：
 * 1 <= k <= 10^5
 * 0 <= w <= 10^9
 * n == profits.length
 * n == capital.length
 * 1 <= n <= 10^5
 * 0 <= profits[i] <= 10^4
 * 0 <= capital[i] <= 10^9
 */
public class Solution502 {

    public static void main(String[] args) {
        System.out.println(findMaximizedCapital(11, 11,
                new int[] { 1, 2, 3 },
                new int[] { 11, 12, 13 }));
    }

    /**
     * 在可选的范围中选择一个利润最大的数据信息 666
     */
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // 按照capital的排序 若相同则按照profits倒序, 构建的最大堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        // 对profits和capital 进行排序
        quickSort(profits, capital, 0, profits.length - 1);
        int i = 0;
        while (k > 0) {
            while (i < capital.length && capital[i] <= w) {// 每次将所有可能符合预算的加入进去
                heap.add(profits[i]);
                i++;
            }
            if (heap.isEmpty()) {
                break;
            } else {
                w += heap.poll(); // 从中找到或得到的最大一次的利润
            }
            k--;
        }
        return w;
    }

    private static void quickSort(int[] profits, int[] capital, int l, int r) {
        if (l < r) {
            int pivot = partition(profits, capital, l, r);
            quickSort(profits, capital, l, pivot - 1);
            quickSort(profits, capital, pivot + 1, r);
        }
    }

    // 根据capital
    private static int partition(int[] profits, int[] capital, int l, int r) {
        int pivot = capital[r];
        int target = l;
        for (; l < r; l++) {
            if (capital[l] < pivot) {
                swap(capital, profits, l, target);
                target++;
            }
        }
        swap(capital, profits, target, r);
        return target;
    }

    private static void swap(int[] capital, int[] profits, int i, int j) {
        int temp = capital[i];
        capital[i] = capital[j];
        capital[j] = temp;
        temp = profits[i];
        profits[i] = profits[j];
        profits[j] = temp;
    }

    /* 官方题解 */

    public int findMaximizedCapital2(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int curr = 0;
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; ++i) {
            arr[i][0] = capital[i];
            arr[i][1] = profits[i];
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        for (int i = 0; i < k; ++i) {
            while (curr < n && arr[curr][0] <= w) {
                pq.add(arr[curr][1]);
                curr++;
            }
            if (!pq.isEmpty()) {
                w += pq.poll();
            } else {
                break;
            }
        }

        return w;
    }

}
