package com.welph.leecode.part_1_500.part_41_60;

import java.util.Arrays;

/**
 * 给出集合[1,2,3,…,n]，其所有元素共有n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定n 和k，返回第k个排列。
 * <p>
 * 说明：
 * <p>
 * 给定 n的范围是 [1, 9]。
 * 给定 k的范围是[1, n!]。
 * 示例1:
 * <p>
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例2:
 * <p>
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class Solution60 {

    public static void main(String[] args) {
        System.out.println(getPermutation(4, 9));
    }

    public static String getPermutation(int n, int k) {
        boolean[] flag = new boolean[n];
        int[] total = new int[n + 1];
        total[0] = 1;
        for (int i = 1; i < total.length; i++) {
            total[i] = i * total[i - 1];//标记每一个n值. 可能得总数, 用于下面的剪枝操作
        }
        /**
         * 从小到大排序的第k个
         * 第1个是第1个变化
         * 第n个是第二个变化
         * 第2n个是第2个变化
         * ...
         */
        // 分治法
        // (x)(n-2) +1 属于第k个最接近的
        String build = build(n, k, flag, total, n);
        return build;
    }

    public static String build(int n, int k, boolean[] flag, int[] total, int origin) {
        if (n == 0) {
            return "";
        }
        int spec = total[n - 1];
        int line = k / spec - (k % spec == 0 ? 1 : 0);
        int i1 = line * spec;
        int i = 0;
        for (; i < origin; i++) {
            if (!flag[i]) {
                line--;
                if (line < 0) {
                    flag[i] = true;
                    break;
                }
            }
        }
        return (i + 1) + build(n - 1, k - i1, flag, total, origin);
    }

    /* 官方题解 */
    public String getPermutation2(int n, int k) {
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; ++i) {
            factorial[i] = factorial[i - 1] * i;
        }

        --k;
        StringBuffer ans = new StringBuffer();
        int[] valid = new int[n + 1];
        Arrays.fill(valid, 1);
        for (int i = 1; i <= n; ++i) {//减少递归调用, 上面方法基本上都是层级调用,没有其他路径
            int order = k / factorial[n - i] + 1;
            for (int j = 1; j <= n; ++j) {
                order -= valid[j]; //比较好的一个点, 减少一些代码
                if (order == 0) {
                    ans.append(j);
                    valid[j] = 0;
                    break;
                }
            }
            k %= factorial[n - i];
        }
        return ans.toString();
    }
}
