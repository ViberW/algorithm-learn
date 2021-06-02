package com.welph.leecode.algorithm.z7z8;

/**
 * 有N堆纸牌编号为1~N，每堆有若干张，但纸牌总数必为N的倍数。可在任一堆上取若干张移动。
 * 移牌规则：
 * 编号为1的堆上取的纸牌只能移到编号为2的堆上
 * 编号为N的堆上取的纸牌只能移到编号为N-1的堆上
 * 其他堆上取的纸牌可向左右相邻堆移动
 * 问最少要移动几次可使每堆上纸牌一样多？
 */
public class DivideCards {

    public static void main(String[] args) {
        int n = 9;
        int[] arr = {1, 3, 5};
        System.out.println(divideCard(n, arr));
    }

    /**
     * 1~n可以拆分为多个子问题:
     * --- 当然也要保证子问题也满足 相同的倍数(M)
     * 1. 从左开始扫描每一堆纸牌，如果大于或等于平均数，把多余的移到右边相邻的堆上。左右两边都是刚好够分的情况;
     * 2. 从左开始扫描每一堆纸牌，如果小于于平均数,则继续, 知道满足<1>的条件
     * ------------------------------
     * 不需要真正去移动，只需要求出最少的次数。所以如果初始区间可以分割为Y个子区间，那么整个区间最少移动就是N-Y次
     */
    public static int divideCard(int n, int[] arr) {
        int avg = n / arr.length;
        int last = -1;
        int sum = 0;
        int y = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == avg) { //相同的可以当做已经成熟的了,过滤掉
                y++;
                last++;
                continue;
            }
            sum += arr[i];
            if (sum == (i - last) * avg) {
                y++;
                sum = 0;
                last = i;
            }
        }
        return arr.length - y;
    }
}
