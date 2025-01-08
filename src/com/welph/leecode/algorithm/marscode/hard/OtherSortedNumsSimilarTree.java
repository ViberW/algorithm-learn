package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小C 得到一个整数列表 nums，它表示从 1 到 n 的一种排列方式。
 * 小C 计划根据 nums 中元素的顺序逐一将这些数字插入到一个起初为空的二叉搜索树（BST）中。
 * 现在小C 想知道，经过重新排序的 nums 中，有多少种不同的排列方式可以使得生成的 BST
 * 与按原顺序插入 nums 时得到的树完全一致。
 *
 * 请返回重新排列 nums 后，使得 BST 与原始 nums 插入顺序相同的不同方案数。
 * 因为结果可能非常庞大，请将其结果对 10^9 + 7 进行取模运算。
 */
public class OtherSortedNumsSimilarTree {

    public static int solution(int[] nums) {
        //一个节点, 需要大于和小于的值, 才有其他排序的方案,即只有存在左右树,才有多种方案
        // 一个路径上的顺序不能变更.  一旦分叉, 则统计两者的   意味着某个数的左边
        int mod = 1000000007;
        int n = nums.length;
        int[][] leaves = new int[n][4]; //父id和大于当前值的数量
        for (int i = 1; i < n; i++) {
            int index = 0;
            int val = nums[i];
            while (true) {
                if (val > nums[index]) {
                    leaves[index][2]++;
                    if (leaves[index][1] == 0) {
                        leaves[index][1] = i;
                        break;
                    }
                    index = leaves[index][1];
                } else {
                    leaves[index][3]++;
                    if (leaves[index][0] == 0) {
                        leaves[index][0] = i;
                        break;
                    }
                    index = leaves[index][0];
                }
            }
        }
        long result = 1;
        for (int i = 0; i < n; i++) {
            //大于它的数量
            int largeCount = leaves[i][2];
            //小于它的数量
            int lessCount = leaves[i][3];
            if (largeCount != 0 && lessCount != 0) {
                //相当于是(largeCount+lessCount)中取出largeCount. 相对顺序不能变 =>
                long res = 1;
                long lessRes = 1;
                //(largeCount+lessCount)!/(largeCount!*lessCount!F)
                for (int c = largeCount + lessCount, l = lessCount; c > largeCount; c--, l--) {
                    res *= c;
                    lessRes *= l;
                }
                result = (result * (res / lessRes) % mod) % mod;
            }
        }
        return (int) ((result - 1 + mod) % mod);
    }


    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 2, 1}) == 0);
        System.out.println(solution(new int[]{1, 4, 2, 3}) == 0);
        System.out.println(solution(new int[]{4, 3, 2, 1}) == 0);
        System.out.println(solution(new int[]{2, 1, 3, 4}));
        System.out.println(solution(new int[]{10, 6, 3, 5, 2, 9, 8, 13, 7, 4, 11, 1, 12}));//73919
    }
}
